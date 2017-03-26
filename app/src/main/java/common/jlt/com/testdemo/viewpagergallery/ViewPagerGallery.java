package common.jlt.com.testdemo.viewpagergallery;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import common.jlt.com.testdemo.R;

public class ViewPagerGallery extends AppCompatActivity implements HomeShufAdapter.OnPageSelectListener {

    private ViewPager vp_home_shuf;
    private RelativeLayout rl_home_shuf;
    private List<ImageView> mShufImages=new ArrayList<>();
    private HomeShufAdapter mShufAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_gallery);
        initView();
        initData();
        initShufProducitons();
    }

    private void initData() {

        ImageView imageView1 = new ImageView(this);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView1.setImageResource(R.mipmap.no1);
//        imageView1.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.no1));

        mShufImages.add(imageView1);
        ImageView imageView2 = new ImageView(this);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setImageResource(R.mipmap.no2);
//        imageView2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.no2));
        mShufImages.add(imageView2);
        ImageView imageView3 = new ImageView(this);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView3.setImageResource(R.mipmap.no3);
//        imageView3.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.no3));
        mShufImages.add(imageView3);
        ImageView imageView4 = new ImageView(this);
        imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView4.setImageResource(R.mipmap.no4);
//        imageView4.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.no4));
        mShufImages.add(imageView4);
        ImageView imageView5 = new ImageView(this);
        imageView5.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView5.setImageResource(R.mipmap.no5);
//        imageView5.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.no5));
        mShufImages.add(imageView5);

    }
    private void initShufProducitons() {

        mShufAdapter = new HomeShufAdapter(mShufImages);
        mShufAdapter.setOnPageSelectListener(this);

        vp_home_shuf.setAdapter(mShufAdapter);
        vp_home_shuf.setPageTransformer(true, new ZoomOutPageTransformer());
        vp_home_shuf.addOnPageChangeListener(mShufAdapter);
        vp_home_shuf.setOffscreenPageLimit(3);
        vp_home_shuf.setPageMargin(80);
        rl_home_shuf.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vp_home_shuf.dispatchTouchEvent(event);
            }
        });

    }
    /**
     * 实现的原理是，在当前显示页面放大至原来的MAX_SCALE
     * 其他页面才是正常的的大小MIN_SCALE
     */
    class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MAX_SCALE = 1.2f;
        private static final float MIN_SCALE = 1.0f;//0.85f

        @Override
        public void transformPage(View view, float position) {
            //setScaleY只支持api11以上
            if (position < -1){
                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);
            } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            { // [-1,1]
//				Log.e("TAG", view + " , " + position + "");
                float scaleFactor =  MIN_SCALE+(1-Math.abs(position))*(MAX_SCALE-MIN_SCALE);
                view.setScaleX(scaleFactor);
                //每次滑动后进行微小的移动目的是为了防止在三星的某些手机上出现两边的页面为显示的情况
                if(position>0){
                    view.setTranslationX(-scaleFactor*2);
                }else if(position<0){
                    view.setTranslationX(scaleFactor*2);
                }
                view.setScaleY(scaleFactor);

            } else
            { // (1,+Infinity]

                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);

            }
        }

    }

    private void initView() {
        vp_home_shuf = (ViewPager) findViewById(R.id.vp_home_shuf);
        rl_home_shuf = (RelativeLayout) findViewById(R.id.rl_home_shuf);
    }

    @Override
    public void select(int position) {
        Toast.makeText(this, "Click" + position, Toast.LENGTH_SHORT).show();

    }
}
