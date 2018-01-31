package com.cf.hodgepodge.module.main;


import com.cf.hodgepodge.presenter.main.MainPresenter;
import com.cf.hodgepodge.ui.iview.IMainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cf on 2018/1/11.
 */

@Module
public class MianActivityModule {

    IMainView mMainView;

    public MianActivityModule(IMainView mainView) {
        mMainView = mainView;
    }

//    @Provides
//    IMainView provideMainView(){
//
//        return mMainView;
//    }
    @Provides
    MainPresenter provideMainPresenter(){

        return new MainPresenter(mMainView);
    }



}
