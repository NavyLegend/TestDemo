package common.jlt.com.testdemo.pupowindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import common.jlt.com.testdemo.Constant;
import common.jlt.com.testdemo.R;

public class PupoWindowTestActivity extends AppCompatActivity {

    Good good=new Good("录音机",666,666,1);
    private WindowManager.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupo_window_test);
    }
    public void showPopFormBottom(View view) {
        CustomPopuwindow takePhotoPopWin = new CustomPopuwindow(this,good,new CustomPopuwindow.SelParaListener() {
            @Override
            public void onResult(int option, Good good_2) {
                switch (option){
                    case Constant.option:
                        good=good_2;
                        Toast.makeText(PupoWindowTestActivity.this,good.getName()+good.getPrice()+good.getNum(), Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
//        设置Popupwindow显示位置（从底部弹出）
        takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        params = getWindow().getAttributes();
        //当弹出Popupwindow时，背景变半透明
        params.alpha=0.5f;
        getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        takePhotoPopWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params = getWindow().getAttributes();
                params.alpha=1f;
                getWindow().setAttributes(params);
            }
        });

//        takePhotoPopWin.lis
    }
}
