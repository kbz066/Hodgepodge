package com.cf.hodgepodge.component.application;


import com.cf.hodgepodge.http.ServerApi;
import com.cf.hodgepodge.module.application.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cf on 2018/1/12.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    ServerApi getServerApi();

}
