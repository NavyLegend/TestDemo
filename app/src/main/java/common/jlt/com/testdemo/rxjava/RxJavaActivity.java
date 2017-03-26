package common.jlt.com.testdemo.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import common.jlt.com.testdemo.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    private TextView result;
    private StringBuffer stringBuffer = new StringBuffer();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        result = ((TextView) findViewById(R.id.result));
        findViewById(R.id.startRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRequest();
            }
        });
    }

    private void startRequest() {
        RetrofitClient.getInstance().creatRetrofit()
                .create(ApiService.class)
                .getCurrentJokeData(1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JokeBean>() {
                    @Override
                    public void accept(JokeBean jokeBean) throws Exception {
                        List<JokeBean.ResultBean.DataBean> data = jokeBean.getResult().getData();
                        for (int i = 0; i < data.size(); i++) {
                            Log.e("返回结果", data.get(i).getContent() + "  " + data.get(i).getUnixtime() + "  " + data.get(i).getUpdatetime());
                            stringBuffer.append((i+1)+".\t"+data.get(i).getContent() + "\n" + data.get(i).getUnixtime() + "\n" + data.get(i).getUpdatetime()+"\n");
                        }
                        result.setText(stringBuffer.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(RxJavaActivity.this, "获取数据失败!", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
