package common.jlt.com.testdemo.designpatterns.facadepattern;

import android.content.Context;
import android.widget.Toast;

/** 子系统类：子系统经脉
 * Created by Administrator on 2017/8/31.
 */

public class JingMai {
    private Context context;

    public JingMai(Context context) {
        this.context = context;
    }
    public void jingmai(){
        Toast.makeText(context, "开启经脉", Toast.LENGTH_SHORT).show();
    }
}
