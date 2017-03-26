package common.jlt.com.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**可以监听滚动事件的ScrollView
 * Created by ZhangHaijun on 2017/3/17.
 */

public class GradientScrollView extends ScrollView{
    public GradientScrollView(Context context) {
        super(context);
    }

    public GradientScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public interface ScrollViewListener{
        void onScrollChanged(GradientScrollView scrollView,int now_x,int now_y,int old_x,int old_y);
    }
    private ScrollViewListener scrollViewListener=null;

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    /**
     * ScrollView滚动时的回调方法
     * @param l 当前横向滑动距离
     * @param t 当前纵向滑动距离
     * @param oldl 之前横向滑动距离
     * @param oldt 之前纵向滑动距离
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener!=null){
            scrollViewListener.onScrollChanged(this,l,t,oldl,oldt);
        }
    }
}

