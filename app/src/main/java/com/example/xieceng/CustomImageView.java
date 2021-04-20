package com.example.xieceng;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class CustomImageView extends AppCompatImageView {
    private int defaultRadius;
    private int radius;
    private int leftTopRadius;
    private int rightTopRadius;
    private int rightBottomRadius;
    private int leftBottomRadius;
    private int width, height;

    private void initData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView);
        defaultRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImageView_cornerradius, 0);
        leftTopRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImageView_leftTopRadius, 0);
        rightTopRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImageView_rightTopRadius, 0);
        leftBottomRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImageView_leftBottomRadius, 0);
        rightBottomRadius = typedArray.getDimensionPixelOffset(R.styleable.CustomImageView_rightBottomRadius, 0);
        if (leftTopRadius == 0) leftBottomRadius = defaultRadius;
        if (rightBottomRadius == 0) rightBottomRadius = defaultRadius;
        if (rightTopRadius == 0) rightTopRadius = defaultRadius;
        if (leftTopRadius == 0) leftTopRadius = defaultRadius;
        typedArray.recycle();
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int scrollx = getScrollX();
        int scrolly = getScrollY();
        int maxLeft = Math.max(leftTopRadius, leftBottomRadius);
        int maxRight = Math.max(rightTopRadius, rightBottomRadius);
        int minWidth = maxLeft + maxRight;
        int maxTop = Math.max(leftTopRadius, rightTopRadius);
        int maxBottom = Math.max(leftBottomRadius, rightBottomRadius);
        int minHeight = maxTop + maxBottom;
        if (getPaddingTop() > height || getPaddingLeft() > width || getPaddingBottom() > height || getPaddingRight() > width) {
            return;
        }
        if (width >= minWidth && height > minHeight) {//防止超出边界
            Path path = new Path();
            path.moveTo(leftTopRadius + getPaddingLeft(), 0 + getPaddingTop());
            path.lineTo(width - getPaddingRight() - rightTopRadius, 0 + getPaddingTop());
            path.quadTo(width - getPaddingRight(), 0 + getPaddingTop(), width - getPaddingLeft(), rightTopRadius + getPaddingTop());
            path.lineTo(width - getPaddingRight(), height - rightBottomRadius - getPaddingBottom());
            path.quadTo(width - getPaddingRight(), height - getPaddingBottom(), width - rightBottomRadius - getPaddingRight(), height - getPaddingBottom());

            path.lineTo(leftBottomRadius + getPaddingLeft(), height - getPaddingBottom());
            path.quadTo(0 + getPaddingRight(), height - getPaddingBottom(), 0 + getPaddingLeft(), height - leftBottomRadius - getPaddingBottom());

            path.lineTo(0 + getPaddingLeft(), leftTopRadius + getPaddingTop());
            path.quadTo(0 + getPaddingLeft(), 0 + getPaddingTop(), leftTopRadius + getPaddingLeft(), 0 + getPaddingTop());

            canvas.clipPath(path);
        }
        if (null != getDrawable()) getDrawable().draw(canvas);
        super.onDraw(canvas);

    }

}
