package com.cf.hodgepodge.module.home;


import com.cf.hodgepodge.presenter.home.WeiXinHotPresenter;
import com.cf.hodgepodge.ui.iview.IWeiXinHotView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cf on 2018/1/19.
 */

@Module
public class WeiXinHotFragmentModule {

    IWeiXinHotView mHotView;

    public WeiXinHotFragmentModule(IWeiXinHotView hotView) {
        mHotView = hotView;
    }


    @Provides
    WeiXinHotPresenter provideWeiXinHotPresenter(){

        return new WeiXinHotPresenter(mHotView);
    }
}
