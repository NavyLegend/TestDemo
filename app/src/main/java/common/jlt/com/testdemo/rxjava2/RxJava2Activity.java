package common.jlt.com.testdemo.rxjava2;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import common.jlt.com.testdemo.R;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class RxJava2Activity extends NetworkBaseActivity {
    private TextView name;
    private TextView age;
    private Observable observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);

        getUsers();
    }

    private void getUsers() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "123");
        map.put("name", "gesanri");

        observable = RetroFactory.getInstance().getUser(map);
        observable.compose((ObservableTransformer) composeFunction).subscribe(new BaseObserver<UserBean>(RxJava2Activity.this, pd) {
            @Override
            void onHandleSuccess(UserBean user) {
                name.setText("姓名：" + user.getName());
                age.setText("年龄：" + user.getAge());
            }
        });
    }
}

