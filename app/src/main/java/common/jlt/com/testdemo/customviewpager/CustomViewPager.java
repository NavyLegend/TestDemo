package common.jlt.com.testdemo.customviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import common.jlt.com.testdemo.R;

/**
 * 自定义view来实现ViewPager效果
 */
public class CustomViewPager extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);
        initView();

    }

    private void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this,CustomHorizontalActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this,CustomVerticalActivity.class));
                break;
        }
    }
}
