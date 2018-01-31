package com.cf.hodgepodge.module.home;

import com.cf.hodgepodge.presenter.home.HomePresenter;
import com.cf.hodgepodge.ui.iview.IHomeView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cf on 2018/1/18.
 */
@Module
public class HomeModule {

    IHomeView mHomeView;

    public HomeModule(IHomeView homeView) {
        mHomeView = homeView;
    }

    @Provides
    HomePresenter provideHomePresenter(){

        return new HomePresenter(mHomeView);
    }

}
