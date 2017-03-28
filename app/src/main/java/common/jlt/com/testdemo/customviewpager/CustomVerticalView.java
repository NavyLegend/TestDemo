package common.jlt.com.testdemo.customviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/3/28.
 */

public class CustomVerticalView extends ViewGroup {
    private int mScreenHeight;
    private int mStartY;
    private int mEnd;
    private Scroller mScroller;
    private int mLastY;
    private int childCount;

    public CustomVerticalView(Context context) {
        super(context);
    }

    public CustomVerticalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化一些需要的属性
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mScreenHeight = wm.getDefaultDisplay().getHeight();
        mScroller = new Scroller(getContext());
    }

    public CustomVerticalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //在onMeasure中测量子view
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    //确定子View的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        childCount = getChildCount();
        //设置这个ViewGroup的高度
        MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
        lp.height = mScreenHeight * childCount;
        setLayoutParams(lp);
        //绘制子view的位置
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                childView.layout(l, mScreenHeight * i, r, mScreenHeight * (i + 1));
            }
        }
    }

    //增加我们需要的触摸响应事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //在这个触摸事件中，需要判断两个距离，一个是手指移动的距离一个是view滚动的距离
        //这是随着手指的移动会发送改变的量
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStartY = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                //当我们再次触碰屏幕时，如果之前的滚动动画还没有停止，我们也让他立即停止
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dY=mLastY-y;
                //滚动触碰到上边缘时给个下拉反弹的效果
                if (getScrollY()<0){
                    dY/=3;
                }
                //判断滚动的
                if (getScrollY()>mScreenHeight*(getChildCount()-1)){
                    dY=0;
                }
                //让我们的view滚动相应的dy距离
                scrollBy(0,dY);
                mLastY=y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd=getScrollY();
                int dScrollY=mEnd-mStartY;
                //向上滚动
                if (dScrollY>0){
                    if (getScrollY()<0){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else {
                        if (dScrollY<mScreenHeight/3)
                            mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                        else
                            mScroller.startScroll(0,getScrollY(),0,mScreenHeight-dScrollY);
                    }

                }else
                //向下滚动
                {
                    if (getScrollY()>mScreenHeight*(getChildCount()-1)){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else {
                        if (-dScrollY<mScreenHeight/3)
                            mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                        else
                            mScroller.startScroll(0,getScrollY(),0,-mScreenHeight-dScrollY);
                    }

                }
                break;
            default:
                break;
        }
        //重绘界面
        postInvalidate();
        return true;
    }
    //滑屏控制


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }
}
