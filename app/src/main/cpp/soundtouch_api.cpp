//
// Created by ASUS-06 on 2018/1/30.
//

#include "soundtouch_api.h"
#include <android/log.h>
#include "SoundTouch.h"
#include <jni.h>
#include <vector>
#include <assert.h>
#include "STTypes.h"

#define  TAG    "这里填写日志的TAG"
#define LOGD(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)
soundtouch_api api;
std::vector<jbyte > out_vector;

using namespace soundtouch;


 void* getConvBuffer(int sizeBytes) {
    int convBuffSize = (sizeBytes + 15) & -8;
    // round up to following 8-byte bounday
    char *convBuff = new char[convBuffSize];
    return convBuff;
}


 void convertInput(jbyte* input, float* output, const int BUFF_SIZE,
                         int bytesPerSample) {
    switch (bytesPerSample) {
        case 1: {
            unsigned char *temp2 = (unsigned char*) input;
            double conv = 1.0 / 128.0;
            for (int i = 0; i < BUFF_SIZE; i++) {
                output[i] = (float) (temp2[i] * conv - 1.0);
            }
            break;
        }
        case 2: {
            short *temp2 = (short*) input;
            double conv = 1.0 / 32768.0;

            for (int i = 0; i < BUFF_SIZE; i++) {
                short value = temp2[i];
                output[i] = (float) (value * conv);
            }
            break;
        }
        case 3: {
            char *temp2 = (char *) input;
            double conv = 1.0 / 8388608.0;
            for (int i = 0; i < BUFF_SIZE; i++) {
                int value = *((int*) temp2);
                value = value & 0x00ffffff;             // take 24 bits
                value |= (value & 0x00800000) ? 0xff000000 : 0; // extend minus sign bits
                output[i] = (float) (value * conv);
                temp2 += 3;
            }
            break;
        }
        case 4: {
            int *temp2 = (int *) input;
            double conv = 1.0 / 2147483648.0;
            assert(sizeof(int) == 4);
            for (int i = 0; i < BUFF_SIZE; i++) {
                int value = temp2[i];
                output[i] = (float) (value * conv);
            }
            break;
        }
    }
}
 inline int saturate(float fvalue, float minval, float maxval) {
    if (fvalue > maxval) {
        fvalue = maxval;
    } else if (fvalue < minval) {
        fvalue = minval;
    }
    return (int) fvalue;
}

int write(const float *bufferIn, int numElems, int bytesPerSample) {
    int numBytes;


    if (numElems == 0)
        return 0;

    numBytes = numElems * bytesPerSample;
    short *temp = (short*) getConvBuffer(numBytes);

    switch (bytesPerSample) {
        case 1: {
            unsigned char *temp2 = (unsigned char *) temp;
            for (int i = 0; i < numElems; i++) {
                temp2[i] = (unsigned char) saturate(bufferIn[i] * 128.0f + 128.0f,
                                                    0.0f, 255.0f);
            }
            break;
        }

        case 2: {
            short *temp2 = (short *) temp;
            for (int i = 0; i < numElems; i++) {
                short value = (short) saturate(bufferIn[i] * 32768.0f, -32768.0f,
                                               32767.0f);
                temp2[i] = value;
            }
            break;
        }

        case 3: {
            char *temp2 = (char *) temp;
            for (int i = 0; i < numElems; i++) {
                int value = saturate(bufferIn[i] * 8388608.0f, -8388608.0f,
                                     8388607.0f);
                *((int*) temp2) = value;
                temp2 += 3;
            }
            break;
        }

        case 4: {
            int *temp2 = (int *) temp;
            for (int i = 0; i < numElems; i++) {
                int value = saturate(bufferIn[i] * 2147483648.0f, -2147483648.0f,
                                     2147483647.0f);
                temp2[i] = value;
            }
            break;
        }
        default:
            //should throw
            break;
    }

    for (int i = 0; i < numBytes / 2; ++i) {
        out_vector.push_back(temp[i] & 0xff);
        out_vector.push_back((temp[i] >> 8) & 0xff);
    }

    delete[] temp;
    temp = NULL;
    return numElems;

}

void process(SAMPLETYPE* fBufferIn, const int BUFF_SIZE, bool finishing) {


    const int channels = api.numChannels();
    const int buffSizeSamples = BUFF_SIZE / channels;
    const int bytesPerSample = api.getBytesPerSample();

    int nSamples = BUFF_SIZE / channels;

    int processed = 0;

    if (finishing) {
        api.flush();
    } else {
        api.putSamples(fBufferIn, nSamples);
    }

    do {
        nSamples = api.receiveSamples(fBufferIn, buffSizeSamples);
        write(fBufferIn, nSamples * channels,bytesPerSample);
    } while (nSamples != 0);


}



extern "C"
JNIEXPORT void JNICALL
Java_com_caofu_hodgepodge_ndk_NdkManager_init(JNIEnv *env, jobject instance, jint sampleRatete,
                                              jint channels, jint pcm_bit) {

    api.setBytesPerSample(pcm_bit*channels/8);

    api.setSampleRate(sampleRatete);
    api.setChannels(channels);



    api.setSetting(SETTING_USE_QUICKSEEK, false);
    api.setSetting(SETTING_USE_AA_FILTER, true);
}
extern "C"
JNIEXPORT jbyteArray JNICALL
Java_com_caofu_hodgepodge_ndk_NdkManager_putBytes(JNIEnv *env, jobject instance, jbyteArray buffer_,
                                                  jint buff_len) {
    jbyte *buffer = env->GetByteArrayElements( buffer_, NULL);


    const int bytesPerSample = api.getBytesPerSample();
    const int BUFF_SIZE = buff_len / bytesPerSample;



    SAMPLETYPE* fBufferIn = new SAMPLETYPE[BUFF_SIZE];

    convertInput(buffer, fBufferIn, BUFF_SIZE, bytesPerSample);

    process(fBufferIn, BUFF_SIZE, false); //audio is ongoing.

    jbyte * res=new jbyte[out_vector.size()];
    for (int i = 0; i <out_vector.size() ; ++i) {
        res[i]=out_vector[i];
    }
    jbyteArray resArray=env->NewByteArray(out_vector.size());
    env->SetByteArrayRegion(resArray,0,out_vector.size(),res);

    delete[] fBufferIn;
    fBufferIn = NULL;
    out_vector.clear();
    env->ReleaseByteArrayElements( buffer_, buffer, JNI_ABORT);
    return resArray;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_caofu_hodgepodge_ndk_NdkManager_setParmeter(JNIEnv *env, jobject instance, jint tempo,
                                                     jint pitch, jint rate) {

    api.setTempoChange(tempo);
    api.setPitchSemiTones(pitch);
    api.setRateChange(rate);

}