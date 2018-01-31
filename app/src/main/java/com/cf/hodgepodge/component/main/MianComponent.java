package com.cf.hodgepodge.component.main;


import com.cf.hodgepodge.annotation.ActivityScope;
import com.cf.hodgepodge.component.application.AppComponent;
import com.cf.hodgepodge.module.main.MianActivityModule;
import com.cf.hodgepodge.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by cf on 2018/1/11.
 * 注入器
 */
@ActivityScope
@Component(modules = MianActivityModule.class ,dependencies = AppComponent.class)
public interface MianComponent {
    void inject(MainActivity activity);
}
