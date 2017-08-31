package common.jlt.com.testdemo.designpatterns.facadepattern;

import android.content.Context;
import android.widget.Toast;

/** 子系统类：子系统内功
 * Created by Administrator on 2017/8/31.
 */

public class NeiGong {
    private Context context;

    public NeiGong(Context context) {
        this.context = context;
    }

    public void JiuYang(){
        Toast.makeText(context, "使用九阳神功", Toast.LENGTH_SHORT).show();
    }
    public void QianKun(){
        Toast.makeText(context, "使用乾坤大挪移", Toast.LENGTH_SHORT).show();
    }

}
