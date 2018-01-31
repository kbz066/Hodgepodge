package com.cf.hodgepodge.utils;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;

import com.cf.hodgepodge.ndk.NdkManager;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cf on 2018/1/24.
 */

public class VoiceUtils {

    private static VoiceUtils sVoiceUtils;
    // 音频获取源
    private int audioSource = MediaRecorder.AudioSource.MIC;
    // 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050，16000，11025
    private  int sampleRateInHz = 44100;// 44100;
    // 设置音频的录制和播放的声道 CHANNEL_IN_STEREO为双声道，CHANNEL_IN_MONO
    private  int in_channelConfig = AudioFormat.CHANNEL_IN_MONO;// AudioFormat.CHANNEL_IN_STEREO;

    private  int out_channelConfig = AudioFormat.CHANNEL_OUT_MONO;// AudioFormat.CHANNEL_OUT_STEREO;
    // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。

    private  int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    // 音频大小
    private int recBufSize, playBufSize;



    private boolean isRecording;
    private LinkedBlockingQueue<byte[]> mQueue;
    private AudioRecord mAudioRecord;
    private AudioTrack mAudioTrack;
    private AcousticEchoCanceler mEchoCanceler;
    private NoiseSuppressor mNoiseSuppressor;
    private NdkManager mNdkManager;


    public static VoiceUtils getInstance(){
        if (sVoiceUtils==null){
            sVoiceUtils=new VoiceUtils();
        }
        return sVoiceUtils;
    }


    public void init(){
        mNdkManager=new NdkManager();
        mNdkManager.init(44100,1,16);
        mQueue=new LinkedBlockingQueue<>();

        recBufSize = AudioRecord.getMinBufferSize(sampleRateInHz, in_channelConfig, audioFormat)*4;

        playBufSize = AudioTrack.getMinBufferSize(sampleRateInHz, out_channelConfig, audioFormat)*4;



        if (AcousticEchoCanceler.isAvailable()&&NoiseSuppressor.isAvailable()) {//支持回音消除和降噪

            mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, in_channelConfig, audioFormat, recBufSize);
            mEchoCanceler = AcousticEchoCanceler.create(mAudioRecord.getAudioSessionId());
            if (mEchoCanceler!=null)
                mEchoCanceler.setEnabled(true);
            mNoiseSuppressor = NoiseSuppressor.create(mAudioRecord.getAudioSessionId());
            if (mNoiseSuppressor!=null)
                mNoiseSuppressor.setEnabled(true);
        }else {
            mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, in_channelConfig, audioFormat, recBufSize);
        }

        if (mNoiseSuppressor!=null&&mEchoCanceler!=null){

            mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz, out_channelConfig, audioFormat, playBufSize, AudioTrack.MODE_STREAM, mAudioRecord.getAudioSessionId());
        }else {
            mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz, out_channelConfig, audioFormat, playBufSize, AudioTrack.MODE_STREAM);

        }
//版本大于4.3
        if (Build.VERSION.SDK_INT >= 0x12)
            mAudioTrack.setVolume(0.9f);//设置当前音量大小
        else
            mAudioTrack.setStereoVolume(0.9f, 0.9f);
    }

    /**
     * 开始录音
     */

    private Disposable mStartDisposable;
    public void startRecording(){
        isRecording=true;
        mQueue.clear();
        final NdkManager ndkManager=new NdkManager();

        mStartDisposable=Schedulers.io().createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                byte[] buffer = new byte[recBufSize];
                mAudioRecord.startRecording();//开始录制


                while (isRecording) {


                    //从MIC保存数据到缓冲区
                    int bufferReadResult = mAudioRecord.read(buffer, 0, recBufSize);


                    if (bufferReadResult>0){
                        mQueue.add(Arrays.copyOfRange(buffer,0,bufferReadResult));
                    }


                }
            }
        });
    }

    /**
     * 停止录音
     */
    public void stopRecording(){
        if (mStartDisposable!=null){

            mStartDisposable.dispose();
            mAudioRecord.stop();
        }
        isRecording=false;
        Schedulers.io().createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                if (mQueue != null&&mQueue.peek()!=null) {
                    byte[] playData=new byte[0];


                    while (mQueue.peek()!=null){
                        byte[] dataQueue=mQueue.poll();
                        if (dataQueue!=null&&dataQueue.length>0){

                            playData=ArrayUtils.copyArray(playData,mNdkManager.putBytes(dataQueue,dataQueue.length));
                        }

                    }


                    try {

                        mAudioTrack.play();//开始播放
                        mAudioTrack.write(playData, 0, playData.length);

                    }catch (Exception e){

                        e.getMessage();
                    }


                }
                if (mAudioTrack!=null&&mAudioTrack.getPlayState()==AudioTrack.PLAYSTATE_PLAYING){
                    mAudioTrack.stop();
                }
            }
        });
    }

    public NdkManager getNdkManager() {
        return mNdkManager;
    }

    public void release() {


        if (mAudioTrack.getPlayState()==AudioTrack.PLAYSTATE_PLAYING){
            mAudioTrack.stop();
            mAudioTrack.release();
            mAudioTrack = null;
        }


        if (mAudioTrack!=null){
            mAudioTrack.stop();
            mAudioTrack.release();
            mAudioTrack = null;
        }


        if (mQueue!= null) {
            mQueue.clear();
            mQueue=null;
        }

        mNdkManager=null;
    }

}
