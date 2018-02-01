package com.cf.hodgepodge.ui.fragment;


import android.Manifest;
import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.cf.hodgepodge.R;
import com.cf.hodgepodge.bean.ToHomeEvent;
import com.cf.hodgepodge.component.audio.AudioComponent;
import com.cf.hodgepodge.component.audio.DaggerAudioComponent;
import com.cf.hodgepodge.module.audio.AudioModule;
import com.cf.hodgepodge.presenter.audio.AudioPresenter;
import com.cf.hodgepodge.ui.base.BaseFragment;
import com.cf.hodgepodge.ui.iview.IAudioView;
import com.cf.hodgepodge.utils.EventBusUtils;
import com.cf.hodgepodge.utils.VoiceUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;


public class AudioFragment extends BaseFragment<AudioPresenter> implements IAudioView, View.OnTouchListener {


    @BindView(R.id.iv_startRecording)
    ImageView mIvStartRecording;

    @BindView(R.id.iv_mic_img)
    ImageView mIvMicImg;

    @Inject
    int[] mAnimationDrawableID;

    private AnimationDrawable mDrawable;

    @OnCheckedChanged({R.id.rb_normal,R.id.rb_man,R.id.rb_wman,R.id.rb_boby})
    public void onCheckedChanged(CompoundButton group, boolean ischecked) {

        switch (group.getId()){
            case R.id.rb_normal:
                if (ischecked)
                VoiceUtils.getInstance().getNdkManager().setParmeter(0,0,0);
                break;
            case R.id.rb_man:
                if (ischecked)
                VoiceUtils.getInstance().getNdkManager().setParmeter(0,-5,-5);
                break;
            case R.id.rb_wman:
                if (ischecked)
                VoiceUtils.getInstance().getNdkManager().setParmeter(0,5,5);
                break;
            case R.id.rb_boby:
                if (ischecked)
                VoiceUtils.getInstance().getNdkManager().setParmeter(-15,5,10);
                break;
        }



    }



    @Override
    protected void loadData() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_audio;
    }

    @Override
    public void initView() {
        checkPermission(new String[]{Manifest.permission.RECORD_AUDIO}, new permissionResult() {
            @Override
            public void onSucceed() {
                Toast.makeText(AudioFragment.this.getActivity(), "权限申请成功", Toast.LENGTH_SHORT).show();
                VoiceUtils.getInstance().init();
                mIvStartRecording.setOnTouchListener(AudioFragment.this);

            }

            @Override
            public void onFailed() {

                EventBusUtils.post(new ToHomeEvent());

            }
        });

    }


    @Override
    public void initInject() {
        AudioComponent component = DaggerAudioComponent.builder()
                .appComponent(mAppComponent)
                .audioModule(new AudioModule(this))
                .build();
        component.inject(this);
        component.inject(mPresenter);
    }

    /**
     * 简单的采用逐帧动画 模拟麦克风
     */
    public void setStartUI() {
        mIvStartRecording.setImageResource(R.drawable.ysq_btn_dian_k);
        mDrawable = new AnimationDrawable();
        if (!mDrawable.isRunning()) {


            for (int i = 0; i < mAnimationDrawableID.length; i++) {
                mDrawable.addFrame(getResources().getDrawable(mAnimationDrawableID[(int) (Math.random() * 8)]), 500);
            }

            mIvMicImg.setBackground(mDrawable);
            mDrawable.start();


        }
    }

    public void setStopUI(){
        mIvStartRecording.setImageResource(R.drawable.ysq_btn_dian_n);
        if (mDrawable!=null){
            mDrawable.stop();
            mIvMicImg.setBackground(null);
            mIvMicImg.setImageResource(R.drawable.ysq_icon_m_d);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下
            setStartUI();
            VoiceUtils.getInstance().startRecording();

        } else if (event.getAction() == MotionEvent.ACTION_UP) {//抬起
            setStopUI();
            VoiceUtils.getInstance().stopRecording();
        }
        return true;
    }

    @Override
    public void onDestroy() {
        VoiceUtils.getInstance().release();
        super.onDestroy();
    }
}
