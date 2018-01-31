package com.cf.hodgepodge.ui.base;



import javax.inject.Inject;

/**
 * Created by cf on 2018/1/11.
 * Presenter 统一接口
 */

public abstract class IBasePresenter<T extends IBaseView> {


    protected T mvpView;

    public IBasePresenter(T mvpView) {
        this.mvpView = mvpView;
    }

    public abstract void attach();
    public abstract void detach();
    public abstract void loadData();
}



