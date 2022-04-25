package com.jiwon.android_ncnn_ml

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jiwon.android_ncnn_ml.databinding.ActivityMainBinding
import com.jiwon.android_ncnn_ml.ml.YOLOX


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val yolox = YOLOX()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        yolox.init(this.assets)

        val image = (getDrawable(R.drawable.dog) as BitmapDrawable).bitmap
        binding.testImage.setImageBitmap(image)

        binding.startBtn.setOnClickListener {
            val objects = yolox.detect(image, false)
            binding.testImage.setImageBitmap(image.plotObjects(objects))
        }
    }

    fun Bitmap.plotObjects(objects : Array<YOLOX.Object>) : Bitmap{
        val rgba: Bitmap = copy(Bitmap.Config.ARGB_8888, true)

        val colors = intArrayOf(
            Color.rgb(54, 67, 244),
            Color.rgb(99, 30, 233),
            Color.rgb(176, 39, 156),
            Color.rgb(183, 58, 103),
            Color.rgb(181, 81, 63),
            Color.rgb(243, 150, 33),
            Color.rgb(244, 169, 3),
            Color.rgb(212, 188, 0),
            Color.rgb(136, 150, 0),
            Color.rgb(80, 175, 76),
            Color.rgb(74, 195, 139),
            Color.rgb(57, 220, 205),
            Color.rgb(59, 235, 255),
            Color.rgb(7, 193, 255),
            Color.rgb(0, 152, 255),
            Color.rgb(34, 87, 255),
            Color.rgb(72, 85, 121),
            Color.rgb(158, 158, 158),
            Color.rgb(139, 125, 96)
        )

        val canvas = Canvas(rgba)

        val paint = Paint()
        paint.setStyle(Paint.Style.STROKE)
        paint.setStrokeWidth(4f)

        val textbgpaint = Paint()
        textbgpaint.setColor(Color.WHITE)
        textbgpaint.setStyle(Paint.Style.FILL)

        val textpaint = Paint()
        textpaint.setColor(Color.BLACK)
        textpaint.setTextSize(26f)
        textpaint.setTextAlign(Paint.Align.LEFT)

        for (i in 0 until objects.size) {
            paint.setColor(colors[i % 19])
            canvas.drawRect(
                objects.get(i).x,
                objects.get(i).y,
                objects.get(i).x + objects.get(i).w,
                objects.get(i).y + objects.get(i).h,
                paint
            )

            // draw filled text inside image
            val text: String =
                objects.get(i).label.toString() + " = " + java.lang.String.format("%.1f",
                    objects.get(i).prob * 100) + "%"
            val text_width: Float = textpaint.measureText(text)
            val text_height: Float = -textpaint.ascent() + textpaint.descent()
            var x: Float = objects.get(i).x
            var y: Float = objects.get(i).y - text_height
            if (y < 0) y = 0f
            if (x + text_width > rgba.width) x = rgba.width - text_width
            canvas.drawRect(x, y, x + text_width, y + text_height, textbgpaint)
            canvas.drawText(text, x, y - textpaint.ascent(), textpaint)

        }

        return rgba
    }
}