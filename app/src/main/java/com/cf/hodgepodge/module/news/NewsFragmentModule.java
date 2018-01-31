package com.cf.hodgepodge.module.news;

import com.cf.hodgepodge.presenter.news.NewsPresenter;
import com.cf.hodgepodge.ui.iview.INewsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cf on 2018/1/17.
 */

@Module
public class NewsFragmentModule {
    INewsView mMainView;

    public NewsFragmentModule(INewsView mainView) {
        mMainView = mainView;
    }


    @Provides
    NewsPresenter provideNewsPresenter(){

        return new NewsPresenter(mMainView);
    }
}
