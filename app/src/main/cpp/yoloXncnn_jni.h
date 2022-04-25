//
// Created by Jiwon_Hae on 2022/04/26.
//

#ifndef ANDROID_NCNN_ML_YOLOXNCNN_JNI_H
#define ANDROID_NCNN_ML_YOLOXNCNN_JNI_H


#include <android/asset_manager_jni.h>
#include <android/bitmap.h>
#include <android/log.h>

#include <jni.h>

#include <string>
#include <vector>

// ncnn
#include "layer.h"
#include "net.h"
#include "benchmark.h"

struct Object
{
    float x;
    float y;
    float w;
    float h;
    int label;
    float prob;
};

struct GridAndStride
{
    int grid0;
    int grid1;
    int stride;
};

#endif //ANDROID_NCNN_ML_YOLOXNCNN_JNI_H
