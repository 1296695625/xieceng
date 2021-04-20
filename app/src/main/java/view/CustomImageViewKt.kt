package com.example.liu.androidx

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import com.example.xieceng.R
import java.util.jar.Attributes

class CustomImageViewKt : AppCompatImageView {
    private var rightTopRadius = 0
    private var rightBottomRadius = 0;
    private var leftBottomRadius = 0;
    private var mWidth = 0
    private var mHeight = 0;
    private var defaultRadius = 0
    private var leftTopRadius = 0
    private lateinit var path: Path;

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
        inits(context, attrs)
    }

    constructor(context: Context) : this(context, null, 0) {
        inits(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inits(context, attrs)
    }

    init {

//        val typedArray = context.obtainStyledAttributes(mAttrs, R.styleable.CustomImmageViewKt)
//        rightTopRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_rightTopRadiuskt, 0)
//        leftBottomRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_leftBottomRadiuskt, 0)
//        leftTopRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_leftTopRadiuskt, 0)
//        rightBottomRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_rightBottomRadiuskt, 0)
//        defaultRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_cornerradiuskt, 0)
//        if(leftTopRadius==0)leftBottomRadius = defaultRadius;
//        if(rightBottomRadius==0)rightBottomRadius = defaultRadius;
//        if(rightTopRadius==0)rightTopRadius = defaultRadius;
//        if(leftTopRadius==0)leftTopRadius = defaultRadius;
//        typedArray.recycle()
    }

    fun inits(context: Context?, attrs: AttributeSet?) {
        val typedArray: TypedArray = context!!.obtainStyledAttributes(attrs, R.styleable.CustomImmageViewKt)
        rightTopRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_rightTopRadiuskt, 0)
        leftBottomRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_leftBottomRadiuskt, 0)
        leftTopRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_leftTopRadiuskt, 0)
        rightBottomRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_rightBottomRadiuskt, 0)
        defaultRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImmageViewKt_cornerradiuskt, 0)
        if (leftTopRadius == 0) leftBottomRadius = defaultRadius;
        if (rightBottomRadius == 0) rightBottomRadius = defaultRadius;
        if (rightTopRadius == 0) rightTopRadius = defaultRadius;
        if (leftTopRadius == 0) leftTopRadius = defaultRadius;
        typedArray.recycle()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mWidth = width
        mHeight = height
    }

    override fun onDraw(canvas: Canvas) {
        val maxLeft = Math.max(leftTopRadius, leftBottomRadius);
        val maxRight = Math.max(rightTopRadius, rightBottomRadius);
        val minWidth = maxLeft + maxRight;
        val maxTop = Math.max(leftTopRadius, rightTopRadius);
        val maxBottom = Math.max(leftBottomRadius, rightBottomRadius);
        val minHeight = maxTop + maxBottom;
        if (paddingLeft > width || paddingTop > height || paddingRight > width || paddingBottom > height) {//防止超出边界
            return
        }
        if (width >= minWidth && height > minHeight) {//防止超出边界
            path = Path();
            path.moveTo(leftTopRadius + paddingLeft.toFloat(), 0 + paddingTop.toFloat());
            path.lineTo(width - paddingRight.toFloat() - rightTopRadius, 0 + paddingTop.toFloat());
            path.quadTo(width - paddingRight.toFloat(), 0 + paddingTop.toFloat(), width - paddingLeft.toFloat(), rightTopRadius + paddingTop.toFloat());
            path.lineTo(width - paddingRight.toFloat(), height - rightBottomRadius - paddingBottom.toFloat());
            path.quadTo(width - paddingRight.toFloat(), height - paddingBottom.toFloat(), width - rightBottomRadius - paddingRight.toFloat(), height - paddingBottom.toFloat());

            path.lineTo(leftBottomRadius + paddingLeft.toFloat(), height - paddingBottom.toFloat());
            path.quadTo(0 + paddingRight.toFloat(), height - paddingBottom.toFloat(), 0 + paddingLeft.toFloat(), height - leftBottomRadius - paddingBottom.toFloat());

            path.lineTo(0 + paddingLeft.toFloat(), leftTopRadius + paddingTop.toFloat());
            path.quadTo(0 + paddingLeft.toFloat(), 0 + paddingTop.toFloat(), leftTopRadius + paddingLeft.toFloat(), 0 + paddingTop.toFloat());

            canvas.clipPath(path);
        }
        if (null != getDrawable()) getDrawable().draw(canvas);

        super.onDraw(canvas)
    }


}

