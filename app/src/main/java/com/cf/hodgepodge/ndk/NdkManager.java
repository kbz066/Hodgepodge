package com.cf.hodgepodge.ndk;

/**
 * Created by cf on 2018/1/24.
 */

public class NdkManager {
    static {
        System.loadLibrary("native-lib");
    }

    public native byte[]  putBytes(byte []buffer, int buff_len);
    public native void init(int sampleRatete,int channels,int pcm_bit);
    public native void setParmeter(int tempo,int pitch,int rate);
}

