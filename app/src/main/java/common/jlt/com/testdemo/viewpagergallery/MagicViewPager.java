package common.jlt.com.testdemo.viewpagergallery;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import common.jlt.com.testdemo.R;
import common.jlt.com.testdemo.viewpagergallery.transformer.AlphaPageTransformer;
import common.jlt.com.testdemo.viewpagergallery.transformer.NonPageTransformer;
import common.jlt.com.testdemo.viewpagergallery.transformer.RotateDownPageTransformer;
import common.jlt.com.testdemo.viewpagergallery.transformer.RotateUpPageTransformer;
import common.jlt.com.testdemo.viewpagergallery.transformer.RotateYTransformer;
import common.jlt.com.testdemo.viewpagergallery.transformer.ScaleInAlphaTransformer;
import common.jlt.com.testdemo.viewpagergallery.transformer.ScaleInTransformer;

public class MagicViewPager extends AppCompatActivity {
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;

    int[] imgRes = {R.mipmap.no1, R.mipmap.no2, R.mipmap.no3, R.mipmap.no4,
            R.mipmap.no5, R.mipmap.no6, R.mipmap.no7, R.mipmap.no8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_view_pager);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mViewPager.setPageMargin(40);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.setPageTransformer(true, new ScaleInAlphaTransformer());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String[] effects=this.getResources().getStringArray(R.array.magic_effect);
        for (String effect :
                effects) {
            menu.add(effect);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String title = item.getTitle().toString();
        mViewPager.setAdapter(new MyPagerAdapter());

        if ("RotateDown".equals(title))
        {
            mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
        } else if ("RotateUp".equals(title))
        {
            mViewPager.setPageTransformer(true, new RotateUpPageTransformer());
        } else if ("RotateY".equals(title))
        {
            mViewPager.setPageTransformer(true, new RotateYTransformer(45));
        } else if ("Standard".equals(title))
        {
//            mViewPager.setClipChildren(false);
            mViewPager.setPageTransformer(true, NonPageTransformer.INSTANCE);
        } else if ("Alpha".equals(title))
        {
//            mViewPager.setClipChildren(false);
            mViewPager.setPageTransformer(true, new AlphaPageTransformer());
        } else if ("ScaleIn".equals(title))
        {
            mViewPager.setPageTransformer(true, new ScaleInTransformer());
        } else if ("RotateDown and Alpha".equals(title))
        {
            mViewPager.setPageTransformer(true, new RotateDownPageTransformer(new AlphaPageTransformer()));
        }else if ("RotateDown and Alpha And ScaleIn".equals(title))
        {
            mViewPager.setPageTransformer(true, new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));
        }else  if ("ScaleInAlphaTransformer".equals(title)){
            mViewPager.setPageTransformer(true, new ScaleInAlphaTransformer());
        }

        setTitle(title);

        return true;
    }

    /**
     * pager的适配器
     */
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgRes.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(MagicViewPager.this);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setImageResource(imgRes[position]);
            container.addView(view);
            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
