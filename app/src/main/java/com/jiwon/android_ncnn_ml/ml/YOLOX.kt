package com.jiwon.android_ncnn_ml.ml

import android.content.res.AssetManager
import android.graphics.Bitmap

class YOLOX {
    data class Object(
        val x : Float,
        val y : Float,
        val w : Float,
        val h : Float,
        val label : String,
        val prob : Float
    )

    external fun init(mgr : AssetManager) : Boolean

    external fun detect(var1 : Bitmap, useGPU : Boolean) : Array<Object>


    init{
        System.loadLibrary("YoloXNCNN")
    }
}