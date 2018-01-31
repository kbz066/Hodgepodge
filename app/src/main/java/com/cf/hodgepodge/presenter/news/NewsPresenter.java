package com.cf.hodgepodge.presenter.news;

import android.view.View;

import com.cf.hodgepodge.bean.NewsBean;
import com.cf.hodgepodge.bean.ZhiHuDailyBean;
import com.cf.hodgepodge.bean.ZhiHuDailyDetailsBean;
import com.cf.hodgepodge.bean.ZhiHuThemesBean;
import com.cf.hodgepodge.http.ServerApi;
import com.cf.hodgepodge.ui.base.IBasePresenter;
import com.cf.hodgepodge.ui.iview.INewsView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by cf on 2018/1/17.
 */

public class NewsPresenter extends IBasePresenter<INewsView> {


    @Inject
    ServerApi serverApi;

    private List<NewsBean> mNewsBeanList=new ArrayList<>();


    public NewsPresenter(INewsView mvpView) {
        super(mvpView);
    }

    @Override
    public void attach() {

    }

    @Override
    public void detach() {

    }

    @Override
    public void loadData() {
        mvpView.showLoading();
        loadZhiHuDaily();

    }

    public void loadZhiHuDaily(){

        serverApi.getZhiHuDaily()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuDailyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiHuDailyBean zhiHuBean) {

                        setZHiHuTitle("知乎日报");
                        for (int i=0;i<3;i++){
                            NewsBean newsBean=new NewsBean();
                            newsBean.setType(1);

                            newsBean.setStoriesBean(zhiHuBean.getStories().get(i));
                            mNewsBeanList.add(newsBean);
                        }
                        loadZhiHuThemes();
                        mvpView.showBanner(getPathList(zhiHuBean.getTop_stories()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.showError();
                        Logger.e("e------>"+e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void loadZhiHuDailyDetails(int id, final View view){
        serverApi.getZhiHuDailyDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuDailyDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiHuDailyDetailsBean zhiHuDailyDetailsBean) {
                        mvpView.openDetails(zhiHuDailyDetailsBean,view);
                        Logger.e("zhiHuDailyDetailsBean--------->"+zhiHuDailyDetailsBean.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.e("22----------->"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void loadZhiHuThemes(){
        serverApi.getZhiHuThemes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuThemesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiHuThemesBean zhiHuBean) {

                        setZHiHuTitle("知乎主题");
                        for (int i=0;i<2;i++){
                            NewsBean newsBean=new NewsBean();
                            newsBean.setType(2);
                            List<ZhiHuThemesBean.OthersBean> OthersList=new ArrayList<>();
                            int index=i;
                            OthersList.add(zhiHuBean.getOthers().get(index++));
                            OthersList.add(zhiHuBean.getOthers().get(index++));
                            newsBean.setOthersBeanList(OthersList);
                            mNewsBeanList.add(newsBean);
                        }

                        //刷新UI
                        mvpView.showContent(mNewsBeanList);

                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**
     * 初始化标题
     * @param title
     */
    private void setZHiHuTitle(String title) {
        NewsBean newsBean=new NewsBean();
        newsBean.setType(0);
        newsBean.setTitle(title);

        mNewsBeanList.add(newsBean);
    }
    public List<String> getPathList(List<ZhiHuDailyBean.TopStoriesBean> top_stories){
        List<String> path=new ArrayList<>();
        for (ZhiHuDailyBean.TopStoriesBean bean:top_stories) {
            path.add(bean.getImage());
        }
        return path;

    }
}
