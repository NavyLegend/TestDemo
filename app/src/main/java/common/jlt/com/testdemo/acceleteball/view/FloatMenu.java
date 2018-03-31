package common.jlt.com.testdemo.acceleteball.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import common.jlt.com.testdemo.R;
import common.jlt.com.testdemo.acceleteball.manager.ViewManager;

/** 底部菜单栏
 * Created by 15930 on 2018/3/4.
 */

public class FloatMenu extends LinearLayout{
    private LinearLayout layout;

    private TranslateAnimation animation;

    public FloatMenu(final Context context) {
        super(context);
        View root=View.inflate(context, R.layout.float_menu,null);
        layout= (LinearLayout) root.findViewById(R.id.layout);
        animation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,1.0f,
                Animation.RELATIVE_TO_SELF,0
                );
        animation.setDuration(1000);
        animation.setFillAfter(true);
        layout.setAnimation(animation);
        root.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ViewManager manager = ViewManager.getInstance(context);
                manager.showFloatBall();
                manager.hideFloatMenu();
                return false;

            }
        });
        addView(root);

    }
    public void startAnimation() {
        animation.start();
    }

}
