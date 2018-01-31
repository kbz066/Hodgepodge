package com.cf.hodgepodge.module.home;

import com.cf.hodgepodge.presenter.home.GankITPresenter;
import com.cf.hodgepodge.ui.iview.IGankITView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cf on 2018/1/19.
 */

@Module
public class GankITFragmentModule {

    IGankITView mITView;

    public GankITFragmentModule(IGankITView ITView) {
        mITView = ITView;
    }


    @Provides
    GankITPresenter provideWeiXinITPresenter(){

        return new GankITPresenter(mITView);
    }
}
