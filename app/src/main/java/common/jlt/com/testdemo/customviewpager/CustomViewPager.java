package common.jlt.com.testdemo.customviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import common.jlt.com.testdemo.R;

/**
 * 自定义view来实现ViewPager效果
 */
public class CustomViewPager extends AppCompatActivity {

    private CustomView container;
    private int[] imageRes={R.mipmap.pic_1,R.mipmap.pic_2,R.mipmap.pic_6,R.mipmap.pic_4,R.mipmap.pic_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);
        container = ((CustomView) findViewById(R.id.container));
        for (int i = 0; i <imageRes.length ; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(imageRes[i]);
            container.addView(imageView);
        }

    }
}
