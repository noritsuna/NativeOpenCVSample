#ifndef NATIVEOPENCVSAMPLE_NATIVEOPENCV_H
#define NATIVEOPENCVSAMPLE_NATIVEOPENCV_H

#include <jni.h>

extern "C" {

JNIEXPORT void JNICALL
Java_org_siprop_android_nativeopencvsample_MainActivity_gaussianBlur(JNIEnv * , jobject , jlong addrSrc);

}

#endif //NATIVEOPENCVSAMPLE_NATIVEOPENCV_H
