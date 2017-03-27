package common.jlt.com.testdemo.viewpagergallery.transformer;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;


public abstract class BasePageTransformer implements ViewPager.PageTransformer
{
    public static final float DEFAULT_CENTER = 0.5f;
    protected ViewPager.PageTransformer mPageTransformer = NonPageTransformer.INSTANCE;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void transformPage(View view, float position)
    {
        if (mPageTransformer != null)
        {
            mPageTransformer.transformPage(view, position);
        }

        pageTransform(view, position);
    }

    protected abstract void pageTransform(View view, float position);


}
