package common.jlt.com.testdemo.acceleteball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import common.jlt.com.testdemo.R;
import common.jlt.com.testdemo.acceleteball.service.StartFloatBallService;

public class StartAcceleteBallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_accelete_ball);
        findViewById(R.id.startAcceleteBall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartAcceleteBallActivity.this, StartFloatBallService.class);
                startService(intent);
                finish();
            }
        });
    }
}
