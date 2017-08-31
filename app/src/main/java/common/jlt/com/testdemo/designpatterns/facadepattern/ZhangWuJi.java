package common.jlt.com.testdemo.designpatterns.facadepattern;

import android.content.Context;

/**外观类: 外观类张无忌
 * Created by Administrator on 2017/8/31.
 */

public class ZhangWuJi {
    private Context context;
    private JingMai jingMai;
    private ZhaoShi zhaoShi;
    private NeiGong neiGong;

    public ZhangWuJi(Context context) {
        this.context = context;
        jingMai=new JingMai(context);
        zhaoShi=new ZhaoShi(context);
        neiGong=new NeiGong(context);
    }
    /**
     * 使用乾坤大挪移
     */
    public void QianKun(){
        jingMai.jingmai();//开启经脉
        neiGong.QianKun();//使用内功乾坤大挪移
    }
    /**
     * 使用七伤拳
     */
    public void QiShang(){
        jingMai.jingmai();//开启经脉
        neiGong.JiuYang();//使用内功九阳神功
        zhaoShi.QiShangQuan();//使用招式七伤拳
    }



}
