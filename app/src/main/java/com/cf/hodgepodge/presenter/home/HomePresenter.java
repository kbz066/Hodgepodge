package com.cf.hodgepodge.presenter.home;


import com.cf.hodgepodge.http.ServerApi;
import com.cf.hodgepodge.ui.base.IBasePresenter;
import com.cf.hodgepodge.ui.iview.IHomeView;

import javax.inject.Inject;

/**
 * Created by cf on 2018/1/18.
 */

public class HomePresenter extends IBasePresenter<IHomeView> {
    @Inject
    ServerApi mServerApi;

    public HomePresenter(IHomeView mvpView) {
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

}
