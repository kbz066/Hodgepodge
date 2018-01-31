package com.cf.hodgepodge.component.audio;

import com.cf.hodgepodge.annotation.FragmentScope;
import com.cf.hodgepodge.component.application.AppComponent;
import com.cf.hodgepodge.module.audio.AudioModule;
import com.cf.hodgepodge.presenter.audio.AudioPresenter;
import com.cf.hodgepodge.ui.fragment.AudioFragment;

import dagger.Component;

/**
 * Created by cf on 2018/1/20.
 */

@FragmentScope
@Component(modules = AudioModule.class,dependencies = AppComponent.class)
public interface AudioComponent {

    void inject(AudioFragment audioFragment);
    void inject(AudioPresenter audioPresenter);
}
