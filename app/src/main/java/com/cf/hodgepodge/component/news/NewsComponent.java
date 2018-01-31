package com.cf.hodgepodge.component.news;


import com.cf.hodgepodge.annotation.FragmentScope;
import com.cf.hodgepodge.component.application.AppComponent;
import com.cf.hodgepodge.module.news.NewsFragmentModule;
import com.cf.hodgepodge.presenter.news.NewsPresenter;
import com.cf.hodgepodge.ui.fragment.NewsFragment;

import dagger.Component;

/**
 * Created by cf on 2018/1/17.
 */

@FragmentScope
@Component(modules = NewsFragmentModule.class ,dependencies = AppComponent.class)
public interface NewsComponent {

    void inject(NewsFragment newsFragment);
    void inject(NewsPresenter newsPresenter);
}
