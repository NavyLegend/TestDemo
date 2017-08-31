package common.jlt.com.testdemo.designpatterns.facadepattern;

import android.content.Context;
import android.widget.Toast;

/** 子系统类：子系统招式
 * Created by Administrator on 2017/8/31.
 */

public class ZhaoShi {
    private Context context;

    public ZhaoShi(Context context) {
        this.context = context;
    }

    public void TaiJiQuan(){
        Toast.makeText(context, "使用着招式太极拳", Toast.LENGTH_SHORT).show();
    }
    public void QiShangQuan(){
        Toast.makeText(context, "使用招式七伤拳", Toast.LENGTH_SHORT).show();
    }
    public void ShengHuo() {
        Toast.makeText(context, "使用招式圣火令", Toast.LENGTH_SHORT).show();

    }
}
