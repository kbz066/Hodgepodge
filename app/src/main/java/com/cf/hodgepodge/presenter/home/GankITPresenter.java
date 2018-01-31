package com.cf.hodgepodge.presenter.home;


import com.cf.hodgepodge.APPConstant;
import com.cf.hodgepodge.bean.GankITBean;
import com.cf.hodgepodge.http.ServerApi;
import com.cf.hodgepodge.ui.base.IBasePresenter;
import com.cf.hodgepodge.ui.iview.IGankITView;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cf on 2018/1/19.
 */

public class GankITPresenter extends IBasePresenter<IGankITView> {


    @Inject
    ServerApi mServerApi;

    public GankITPresenter(IGankITView mvpView) {
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

    }

    /**
     * 热门
     * @param num
     * @param page
     */
    public void loadGankIT(int num, int page, final boolean isRefresh){
        mServerApi.getGankIT(APPConstant.GANK_HOST+"Android/"+num+"/"+page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankITBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankITBean gankITBean) {


                        if (isRefresh){

                            mvpView.refreshData(gankITBean.getResults());
                        }else {

                            mvpView.loadMoreData(gankITBean.getResults());
                        }


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
}
