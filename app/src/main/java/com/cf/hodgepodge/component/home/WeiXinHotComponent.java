package com.cf.hodgepodge.component.home;


import com.cf.hodgepodge.annotation.ActivityScope;
import com.cf.hodgepodge.component.application.AppComponent;
import com.cf.hodgepodge.module.home.WeiXinHotFragmentModule;
import com.cf.hodgepodge.presenter.home.WeiXinHotPresenter;
import com.cf.hodgepodge.ui.fragment.WeiXinHotFragment;

import dagger.Component;

/**
 * Created by cf on 2018/1/11.
 * 注入器
 */
@ActivityScope
@Component(modules = WeiXinHotFragmentModule.class ,dependencies = AppComponent.class)
public interface WeiXinHotComponent {
    void inject(WeiXinHotFragment hotFragment);
    void inject(WeiXinHotPresenter hotPresenter);
}
