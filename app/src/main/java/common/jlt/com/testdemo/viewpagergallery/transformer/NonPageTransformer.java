package common.jlt.com.testdemo.viewpagergallery.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;


public class NonPageTransformer implements ViewPager.PageTransformer
{
    public static final ViewPager.PageTransformer INSTANCE = new NonPageTransformer();

    @Override
    public void transformPage(View page, float position)
    {
        page.setScaleX(0.999f);//hack
    }
}
