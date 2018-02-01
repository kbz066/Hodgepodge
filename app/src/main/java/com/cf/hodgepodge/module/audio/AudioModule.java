package com.cf.hodgepodge.module.audio;


import com.cf.hodgepodge.R;
import com.cf.hodgepodge.presenter.audio.AudioPresenter;
import com.cf.hodgepodge.ui.iview.IAudioView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cf on 2018/1/20.
 */

@Module
public class AudioModule {

    IAudioView mAudioView;

    public AudioModule(IAudioView audioView) {
        mAudioView = audioView;
    }

    @Provides
    int[] provideImageID(){

        return new int[]{
                R.drawable.ysq_icon_m_w_1, R.drawable.ysq_icon_m_w_2, R.drawable.ysq_icon_m_w_3, R.drawable.ysq_icon_m_w_4,
                R.drawable.ysq_icon_m_w_5, R.drawable.ysq_icon_m_w_6, R.drawable.ysq_icon_m_w_7, R.drawable.ysq_icon_m_w_8
        };

    }

    @Provides
    AudioPresenter provideAudioPresenter(){

        return new AudioPresenter(mAudioView);
    }
}
