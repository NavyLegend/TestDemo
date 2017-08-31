package common.jlt.com.testdemo.designpatterns.facadepattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import common.jlt.com.testdemo.R;

public class FacadePatternActivity extends AppCompatActivity {
private ZhangWuJi zhangWuJi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facade_pattern);
        zhangWuJi=new ZhangWuJi(this);


    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.qiankun:
                zhangWuJi.QianKun();
                break;
            case R.id.qishnagquan:
                zhangWuJi.QiShang();
                break;
        }
    }
}
