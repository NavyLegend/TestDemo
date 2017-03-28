package common.jlt.com.testdemo.customviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/3/28.
 */

public class CustomVerticalView extends ViewGroup {
    private int lastX;
    private int lastY;
    private int currentIndex = 0; //当前子元素
    private int childWidth = 0;
    private Scroller scroller;
    private VelocityTracker tracker;    //增加速度检测,如果速度比较快的话,就算没有滑动超过一半的屏幕也可以
    private int lastInterceptX=0;
    private int lastInterceptY=0;
    private int mScreenHeight;
    private int mStartY;
    private int mEnd;
    private Scroller mScroller;
    private int mLastY;
    private int childCount;


    public CustomVerticalView(Context context) {
        super(context);
        init();
    }

    public CustomVerticalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomVerticalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        scroller=new Scroller(getContext());
        tracker=VelocityTracker.obtain();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mScreenHeight = wm.getDefaultDisplay().getHeight();
    }

    /**
     * 事件拦截
     * @param
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept=false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercept=false;
                //如果动画还没执行完，则打断
                if (!scroller.isFinished())
                    scroller.abortAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX=x-lastInterceptX;
                int deltaY=y-lastInterceptY;
                if (Math.abs(deltaX)-Math.abs(deltaY)>0){
                    intercept=true;
                }else {
                    intercept=true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept=false;
                break;

        }
        lastX=x;
        lastY=y;
        lastInterceptX=x;
        lastInterceptY=y;
        return intercept;
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


    //确定子View的位子
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
                childView.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tracker.addMovement(event);
        int x= (int) event.getX();
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished())
                    scroller.abortAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                //跟随手指滑动
//                int deltaX=x-lastX;
                int deltaY=y-lastY;

                scrollBy(0,-deltaY);
                break;
            //释放手指以后开始自动滑动到目标位置
            case MotionEvent.ACTION_UP:
                //相对于当前View滑动的距离,正为向左,负为向右
                int distance=getScrollY()-currentIndex*mScreenHeight;
                //必须滑动的距离要大于1/2个宽度,否则不会切换到其他页面
                if (Math.abs(distance)>mScreenHeight/2){
                    if (distance>0)
                        currentIndex++;
                    else
                        currentIndex--;
                }else {
                    tracker.computeCurrentVelocity(1000);
                    float yV=tracker.getYVelocity();
                    if (Math.abs(yV)>50){
                        if (yV>0)
                            currentIndex--;
                        else
                            currentIndex++;
                    }
                }
                currentIndex=currentIndex<0?0:currentIndex>getChildCount()-1?getChildCount()-1:currentIndex;
                smoothScrollTo(0,currentIndex*mScreenHeight);
                tracker.clear();
                break;
            default:
                break;
        }
        lastX=x;
        lastY=y;

        return true;
    }

    private void smoothScrollTo(int destX, int destY) {
        scroller.startScroll(getScrollX(),getScrollY(),destX-getScrollX(),destY-getScrollY(),1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }
}
