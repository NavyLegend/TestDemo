package common.jlt.com.testdemo.gradienttitlebar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import common.jlt.com.library.utils.StatusBarUtil;
import common.jlt.com.library.view.GradientScrollView;
import common.jlt.com.library.view.MaterialIndicator;
import common.jlt.com.testdemo.R;

/**
 * 渐变颜色的titleBar
 */
public class GradientTitleBarActivity extends AppCompatActivity implements GradientScrollView.ScrollViewListener{

    private GradientScrollView scrollView;
    private ListView listView;
    private TextView textView;
    private int imageHeight;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        StatusBarUtil.setImgTransparent(this);
        setContentView(R.layout.activity_gradient_title_bar);

        scrollView = (GradientScrollView) findViewById(R.id.scrollview);
        listView = (ListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.textview);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setFocusable(true);
        viewPager.setFocusableInTouchMode(true);
        viewPager.requestFocus();

        MaterialIndicator indicator = (MaterialIndicator) findViewById(R.id.indicator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.addOnPageChangeListener(indicator);
        indicator.setAdapter(viewPager.getAdapter());

        initListeners();
        initData();
    }

    /**
     * viewpager适配器
     */
    private  class MyPagerAdapter extends PagerAdapter {
        public int[] drawables = {R.mipmap.no1
                ,R.mipmap.no2,R.mipmap.no3,R.mipmap.no4,R.mipmap.no5};
        @Override
        public int getCount() {
            return drawables.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(drawables[position]);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
        }
    }

    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListeners() {

        ViewTreeObserver vto = viewPager.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = viewPager.getHeight();

                scrollView.setScrollViewListener(GradientTitleBarActivity.this);
            }
        });
    }



    private void initData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(GradientTitleBarActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.data));
        listView.setAdapter(adapter);
    }


    /**
     * 滑动监听
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(GradientScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            textView.setBackgroundColor(Color.argb((int) 0, 144,151,166));
        } else if (y > 0 && y <= imageHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            textView.setTextColor(Color.argb((int) alpha, 255,255,255));
            textView.setBackgroundColor(Color.argb((int) alpha, 144,151,166));
        } else {    //滑动到banner下面设置普通颜色
            textView.setBackgroundColor(Color.argb((int) 255, 144,151,166));
        }
    }








//    private GradientScrollView scrollView;
//    private ListView listView;
//    private TextView textView;
//    private int imageHeight;
//    private ViewPager viewPager;
//    private MaterialIndicator indicator;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        StatusBarUtil.setImgTransparent(this);
//        setContentView(R.layout.activity_gradient_title_bar);
//
//        scrollView = (GradientScrollView) findViewById(R.id.scrollview);
//        listView= (ListView) findViewById(R.id.listview);
//        textView= (TextView) findViewById(R.id.textview);
//        viewPager= (ViewPager) findViewById(R.id.viewPager);
//        indicator= (MaterialIndicator) findViewById(R.id.indicator);
//
//        viewPager.setFocusable(true);
//        viewPager.setFocusableInTouchMode(true);
//        viewPager.requestFocus();
//
//        viewPager.setAdapter(new MyPagerAdapter());
//        viewPager.addOnPageChangeListener(indicator);
//        indicator.setAdapter(viewPager.getAdapter());
//
//        initListeners();
//        initData();
//
//
//    }
//
//    private void initData() {
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(GradientTitleBarActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.data));
//        listView.setAdapter(adapter);
//    }
//
//    private void initListeners() {
//        ViewTreeObserver treeObserver=viewPager.getViewTreeObserver();
//        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                imageHeight=viewPager.getHeight();
//                scrollView.setScrollViewListener((GradientScrollView.ScrollViewListener) GradientTitleBarActivity.this);
//            }
//        });
//    }
//
//
//    /**
//     * viewPager适配器
//     */
//    private class MyPagerAdapter extends PagerAdapter {
//        public int[] drawables={R.mipmap.pic_1,R.mipmap.pic_2,R.mipmap.pic_3,R.mipmap.pic_4};
//        @Override
//        public int getCount() {
//            return drawables.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return object==view;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            ImageView view=new ImageView(container.getContext());
//            view.setImageResource(drawables[position]);
//            view.setScaleType(ImageView.ScaleType.FIT_XY);
//            container.addView(view);
//            return view;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
//            container.removeView((View) object);
//        }
//    }
//
//    /**
//     * 滑动监听
//     * GradientScrollView的回调方法
//     * @param scrollView
//     * @param now_x
//     * @param now_y
//     * @param old_x
//     * @param old_y
//     */
//    @Override
//    public void onScrollChanged(GradientScrollView scrollView, int now_x, int now_y, int old_x, int old_y) {
//        if (y<=0){
//            //未滑动时：设置标题的背景颜色
//            textView.setBackgroundColor(Color.argb((int)0,144,151,166));
//        }else if (y>0&&y<=imageHeight){
//            //滑动距离小于轮播图的高度时，设置背景和字体颜色颜色透明度渐变
//            float scale=(float) now_y/imageHeight;//滑动的比例
//            float alpha=(255*scale);
//            textView.setTextColor(Color.argb((int) alpha,255,255,255));
//            textView.setBackgroundColor(Color.argb((int) alpha,144,151,166));
//        }else {
//            //滑动距离大于轮播图时，设置为普通颜色
//            textView.setBackgroundColor(Color.argb(255,144,151,166));
//        }
//
//    }

}
