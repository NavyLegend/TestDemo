package common.jlt.com.testdemo.viewpagergallery;

import android.util.TypedValue;

import common.jlt.com.testdemo.rxjava.MyApplication;


public class DimenUtils {


    /**
     * dp 转 px
     * @param dp
     * @return
     */
    public static int dp2px(int dp){
        int  px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, MyApplication.getContext().getResources().getDisplayMetrics());

        return px;
    }
}
