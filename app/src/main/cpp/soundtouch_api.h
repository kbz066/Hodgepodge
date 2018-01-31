//
// Created by ASUS-06 on 2018/1/30.
//

#include <jni.h>
#include "SoundTouch.h"
#ifndef HODGEPODGE_SOUNDTOUCH_API_H
#define HODGEPODGE_SOUNDTOUCH_API_H

#endif //HODGEPODGE_SOUNDTOUCH_API_H

using namespace soundtouch;

 inline int saturate(float, float, float);
 void* getConvBuffer(int);
 void convertInput(jbyte*, float*, int, int);
// int process(SoundTouchStream&, SAMPLETYPE*, queue<jbyte>*, int, bool);

class soundtouch_api: public SoundTouch {

private:


    int bytesPerSample;
public:

    int getBytesPerSample() {
        return bytesPerSample;
    }



    void setBytesPerSample(int bytesPerSample) {
        this->bytesPerSample = bytesPerSample;
    }

};