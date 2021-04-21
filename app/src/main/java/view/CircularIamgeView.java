package view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.xieceng.R;

/**
 *可以单独设置圆角的ImageView
 */
public class CircularIamgeView extends AppCompatImageView {
    private RectF rectF;
    private int defaultRadius;
    private int leftTopRadius;
    private int rightTopRadius;
    private int rightBottomRadius;
    private int leftBottomRadius;
    private int width, height;
    private float[] radii;
    private Path path;
    private Paint paint;

    public CircularIamgeView(Context context) {
        super(context);
        init(context, null);
    }

    public CircularIamgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircularIamgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularIamgeView);
        defaultRadius = typedArray.getDimensionPixelOffset(R.styleable.CircularIamgeView_cornerRadius, 0);
        leftTopRadius = typedArray.getDimensionPixelOffset(R.styleable.CircularIamgeView_lTRadius, 0);
        rightTopRadius = typedArray.getDimensionPixelOffset(R.styleable.CircularIamgeView_rTRadius, 0);
        leftBottomRadius = typedArray.getDimensionPixelOffset(R.styleable.CircularIamgeView_lBRadius, 0);
        rightBottomRadius = typedArray.getDimensionPixelOffset(R.styleable.CircularIamgeView_rBRadius, 0);
        if (leftBottomRadius == 0) {leftBottomRadius = defaultRadius;}
        if (rightBottomRadius == 0) {rightBottomRadius = defaultRadius;}
        if (rightTopRadius == 0) {rightTopRadius = defaultRadius;}
        if (leftTopRadius == 0) {leftTopRadius = defaultRadius;}
        typedArray.recycle();
        rectF = new RectF();
        path = new Path();
        paint=new Paint();
        paint.setAntiAlias(true);
        radii = new float[8];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int middle=Math.min(width,height)/2;
        leftTopRadius=Math.min(leftBottomRadius,middle);
        leftBottomRadius=Math.min(leftBottomRadius,middle);
        rightBottomRadius=Math.min(rightBottomRadius,middle);
        rightTopRadius=Math.min(rightTopRadius,middle);
        if (getPaddingTop() > height || getPaddingLeft() > width || getPaddingBottom() > height || getPaddingRight() > width) {
            return;
        }

        rectF.left = 0 + getPaddingLeft();
        rectF.right = width - getPaddingRight();
        rectF.top = 0 + getPaddingTop();
        rectF.bottom = height - getPaddingBottom();

        radii[0] = radii[1] = leftTopRadius;
        radii[2] = radii[3] = rightTopRadius;
        radii[4] = radii[5] = rightBottomRadius;
        radii[6] = radii[7] = leftBottomRadius;
        path.addRoundRect(rectF, radii, Path.Direction.CW);
        canvas.clipPath(path);
        if(null!=getDrawable()){getDrawable().draw(canvas);}
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }


}
