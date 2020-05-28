package com.tanghongtu.jetpackdemo.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import com.google.android.material.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapeAppearancePathProvider

/**
 * Created by tanghongtu on 2020/5/26.
 */
class MaskedCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.materialCardViewStyle
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val pathProvider = ShapeAppearancePathProvider()
    private val path = Path()
    private val rectF = RectF(0f, 0f, 0f, 0f)
    private val shapeModel = ShapeAppearanceModel.builder(
        context,
        attrs,
        defStyleAttr,
        R.style.Widget_MaterialComponents_CardView
    )
    .build()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rectF.right = w.toFloat()
        rectF.bottom = h.toFloat()
        pathProvider.calculatePath(shapeModel, 1f, rectF, path)
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }

}