package com.cf.hodgepodge.component.home;


import com.cf.hodgepodge.annotation.FragmentScope;
import com.cf.hodgepodge.component.application.AppComponent;
import com.cf.hodgepodge.module.home.GankITFragmentModule;
import com.cf.hodgepodge.presenter.home.GankITPresenter;
import com.cf.hodgepodge.ui.fragment.GankITFragment;

import dagger.Component;

/**
 * Created by cf on 2018/1/11.
 * 注入器
 */
@FragmentScope
@Component(modules = GankITFragmentModule.class ,dependencies = AppComponent.class)
public interface GankITComponent {
    void inject(GankITFragment itFragment);
    void inject(GankITPresenter itPresenter);
}
