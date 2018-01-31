package com.cf.hodgepodge.component.home;


import com.cf.hodgepodge.annotation.FragmentScope;
import com.cf.hodgepodge.component.application.AppComponent;
import com.cf.hodgepodge.module.home.HomeModule;
import com.cf.hodgepodge.presenter.home.HomePresenter;
import com.cf.hodgepodge.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by cf on 2018/1/18.
 */
@FragmentScope
@Component(modules = HomeModule.class,dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
    void inject(HomePresenter homePresenter);
}
