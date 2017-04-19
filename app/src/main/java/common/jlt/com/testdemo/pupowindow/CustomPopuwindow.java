package common.jlt.com.testdemo.pupowindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import common.jlt.com.testdemo.Constant;
import common.jlt.com.testdemo.R;

/**
 * Created by Administrator on 2017/4/18.
 */

public class CustomPopuwindow extends PopupWindow {

    SelParaListener selParaListener;
    Good           good  ;
    private Context mContext;
    private View view;
    private TextView btn_cancel;
    private TextView num_tv;
    private int num;
    private TextView sum_price;

    public CustomPopuwindow(final Context mContext, final Good good , final SelParaListener selParaListener) {
        this.mContext=mContext;
        this.selParaListener=selParaListener;
        this.good=good;

        this.view = LayoutInflater.from(mContext).inflate(R.layout.popu_view, null);
        ((TextView) view.findViewById(R.id.name_tv)).setText(good.getName());
        ((TextView) view.findViewById(R.id.price_tv)).setText(String.valueOf(good.getSingle_price()));
        sum_price = ((TextView) view.findViewById(R.id.sum_price));
        sum_price.setText(String.valueOf(good.getPrice()));
        //减
        view.findViewById(R.id.minus_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num>1){
                    num=num-1;
                    num_tv.setText(String.valueOf(num));
                    good.setNum(num);
                    good.setPrice(num*good.getPrice());
                    sum_price.setText(String.valueOf(good.getPrice()));
                }else {
                    Toast.makeText(mContext, "最少一件", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        //加
        view.findViewById(R.id.plus_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num<10){
                    num=num+1;
                    num_tv.setText(String.valueOf(num));
                    good.setNum(num);
                    good.setPrice(num*good.getPrice());
                    sum_price.setText(String.valueOf(good.getPrice()));
                }else {
                    Toast.makeText(mContext, "最多十件", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
        //数量
        num_tv = ((TextView) view.findViewById(R.id.num_tv));
        num_tv.setText(String.valueOf(good.getNum()));

        btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
        // 取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
                if (selParaListener!=null){
                    selParaListener.onResult(Constant.option,good);
                }
            }
        });

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });


    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.pupo_window_anim);

    }

    /**
     * 回调接口，把选择后的数据回传到宿主页面；
     */
    public interface SelParaListener{
        public void onResult(int option,Good good);
    }
}
