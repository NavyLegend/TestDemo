package common.jlt.com.testdemo.designpatterns.observerpattern;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import common.jlt.com.testdemo.R;

/**
 * 观察者模式这种发布-订阅的形式我们可以拿微信公众号来举例，
 * 假设微信用户就是观察者，
 * 微信公众号是被观察者，有多个的微信用户关注了程序猿这个公众号，
 * 当这个公众号更新时就会通知这些订阅的微信用户。
 */
public class Client extends AppCompatActivity {
private  ConcreteSubject concreteSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        concreteSubject=new ConcreteSubject();

    }

    public void onClick(View view) {
        switch (view.getId()){
            //增加订阅者
            case R.id.attach:
                final EditText editText=new EditText(this);
                new AlertDialog.Builder(this).setTitle("请输入订阅人姓名")
                        .setView(editText)
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //订阅公众号
                                WeixinUser user=new WeixinUser(Client.this);
                                user.setName(editText.getText().toString());
                                concreteSubject.attach(user);

                            }
                        }).create().show();
                break;
            //删除订阅者
            case R.id.detch:
                final EditText editText2=new EditText(this);
                new AlertDialog.Builder(this).setTitle("请输入订阅人姓名")
                        .setView(editText2)
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //删除订阅者
                                WeixinUser user=new WeixinUser(Client.this);
                                user.setName(editText2.getText().toString());
                                concreteSubject.detach(user);

                            }
                        }).create().show();

                break;
            //发布消息
            case R.id.update:
                final EditText editText3=new EditText(this);
                new AlertDialog.Builder(this).setTitle("请输入更新信息")
                        .setView(editText3)
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //更新消息
                                concreteSubject.notify(editText3.getText().toString());

                            }
                        }).create().show();

                break;

        }
    }
}
