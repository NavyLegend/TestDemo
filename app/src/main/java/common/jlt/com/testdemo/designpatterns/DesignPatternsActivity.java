package common.jlt.com.testdemo.designpatterns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import common.jlt.com.testdemo.R;
import common.jlt.com.testdemo.strategy.Strategy;

public class DesignPatternsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_patterns);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.strategy:
                startActivity(new Intent(this, Strategy.class));
                break;

        }
    }
}
