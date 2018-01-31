package com.cf.hodgepodge.presenter.home;

import com.cf.hodgepodge.http.ServerApi;
import com.cf.hodgepodge.ui.base.IBasePresenter;
import com.cf.hodgepodge.ui.iview.IMovieView;

import javax.inject.Inject;

/**
 * Created by cf on 2018/1/19.
 */

public class MoviePresenter extends IBasePresenter<IMovieView> {


    @Inject
    ServerApi mServerApi;

    public MoviePresenter(IMovieView mvpView) {
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
