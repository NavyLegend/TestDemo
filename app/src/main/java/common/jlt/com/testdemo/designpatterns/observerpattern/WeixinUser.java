package common.jlt.com.testdemo.designpatterns.observerpattern;

import android.content.Context;
import android.widget.Toast;

/**具体观察者（ConcrereObserver）
 *
 * 微信用户是观察者，里面实现了更新的方法：
 * Created by Administrator on 2017/9/13.
 */

public class WeixinUser implements Observer{
    private String name;//微信用户名
    private Context context;

    public WeixinUser(Context context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        Toast.makeText(context, name+"--"+message, Toast.LENGTH_SHORT).show();
    }
}
