package com.cf.hodgepodge;

import android.app.Application;

import com.cf.hodgepodge.component.application.AppComponent;
import com.cf.hodgepodge.component.application.DaggerAppComponent;
import com.cf.hodgepodge.module.application.AppModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by cf on 2018/1/11.
 */

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        init();

    }

    private void init() {
        mAppComponent= DaggerAppComponent
                .builder()
                .appModule(new AppModule())
                .build();
        ZXingLibrary.initDisplayOpinion(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
