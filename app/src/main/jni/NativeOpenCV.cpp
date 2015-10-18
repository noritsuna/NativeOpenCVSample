#include "NativeOpenCV.h"
#include <opencv2/opencv.hpp>


JNIEXPORT void JNICALL
        Java_org_siprop_android_nativeopencvsample_MainActivity_gaussianBlur(JNIEnv * , jobject , jlong addrSrc) {
    cv::Mat& src_img  = *(cv::Mat*)addrSrc;

    cv::GaussianBlur(src_img, src_img, cv::Size(51,3), 80, 3);

}
