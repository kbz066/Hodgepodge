package com.cf.hodgepodge.presenter.home;


import com.cf.hodgepodge.APPConstant;
import com.cf.hodgepodge.bean.WeiXinHotBean;
import com.cf.hodgepodge.http.ServerApi;
import com.cf.hodgepodge.ui.base.IBasePresenter;
import com.cf.hodgepodge.ui.iview.IWeiXinHotView;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cf on 2018/1/19.
 */

public class WeiXinHotPresenter extends IBasePresenter<IWeiXinHotView> {


    @Inject
    ServerApi mServerApi;

    public WeiXinHotPresenter(IWeiXinHotView mvpView) {
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
    public void loadWeiXinHot(int num, int page, final boolean isRefresh){
        mServerApi.getWeiXinHot(APPConstant.WEIXIN_HOST+"keji/",APPConstant.WEIXIN_KEY,num,page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeiXinHotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeiXinHotBean weiXinHotBean) {
                        if (isRefresh){

                            mvpView.refreshData(weiXinHotBean.getNewslist());
                        }else {

                            mvpView.loadMoreData(weiXinHotBean.getNewslist());
                        }

                        Logger.d("打印---------->"+weiXinHotBean.getNewslist().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.showError();
                        //Logger.e(e,"加载失败---------》");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
