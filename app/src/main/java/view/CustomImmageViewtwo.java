package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class CustomImmageViewtwo extends AppCompatImageView {
    private RectF rectF;
    private Paint paint;
    private float width, height;

    public CustomImmageViewtwo(Context context) {
        super(context);
        init();
    }

    private void init() {
        rectF = new RectF();

    }

    public CustomImmageViewtwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomImmageViewtwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        rectF.left = 0;
        rectF.right=width;
        rectF.top=0;
        rectF.bottom=height;
        Path path = new Path();
        path.addRoundRect(rectF,20,20, Path.Direction.CW);
        canvas.clipPath(path);
        getDrawable().draw(canvas);
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getRight()-getLeft();
        height = getBottom()-getTop();
    }


}
