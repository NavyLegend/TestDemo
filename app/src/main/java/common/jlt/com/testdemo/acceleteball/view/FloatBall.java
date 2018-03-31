package common.jlt.com.testdemo.acceleteball.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import common.jlt.com.testdemo.R;

/**
 * 自定义悬浮球
 * Created by 15930 on 2018/3/4.
 */

public class FloatBall extends View {

    public int width = 150;
    public int height = 150;
    //默认显示的进度文本
    private String text = "50%";
    //是否在拖动
    private boolean isDrag;

    //画笔：球
    private Paint ballPaint;

    //画笔：文本
    private Paint textPaint;

    //拖动时显示图片
    private Bitmap bitmap;


    public FloatBall(Context context) {
        super(context);
        init();
    }

    public FloatBall(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FloatBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        ballPaint = new Paint();
        ballPaint.setColor(Color.GREEN);
        ballPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setTextSize(25);
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);

        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.ninja);
        //将图片裁剪到指定尺寸的大小
        bitmap = Bitmap.createScaledBitmap(src, width, height, true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设定自定义View的尺寸
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isDrag) {
            canvas.drawCircle(width / 2, height / 2, width / 2, ballPaint);
            float textWidth = textPaint.measureText(text);
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float dy = -(fontMetrics.descent + fontMetrics.ascent) / 2;
            canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + dy, textPaint);

        } else {
            //正在拖动时则显示指定的图片
            canvas.drawBitmap(bitmap, 0, 0, null);
        }

    }

    //设置当前移动状态
    public void setDragState(boolean isDrag) {
        this.isDrag = isDrag;
        invalidate();
    }
}
