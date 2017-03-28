package common.jlt.com.testdemo.customviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import common.jlt.com.testdemo.R;

public class CustomVerticalActivity extends AppCompatActivity {
    private CustomVerticalView container;
    private int[] imageRes={R.color.material_deep_teal_20,R.color.text_color,R.color.red_btn_bg_color,R.color.material_blue_grey_80,R.color.material_deep_teal_50,R.color.error_stroke_color,R.color.warning_stroke_color};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_vertical);
        container = ((CustomVerticalView) findViewById(R.id.container));
        for (int i = 0; i <imageRes.length ; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(imageRes[i]);
            container.addView(imageView);
        }
    }
}
