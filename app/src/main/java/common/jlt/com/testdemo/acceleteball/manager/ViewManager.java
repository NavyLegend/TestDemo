package common.jlt.com.testdemo.acceleteball.manager;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import common.jlt.com.testdemo.acceleteball.view.FloatBall;
import common.jlt.com.testdemo.acceleteball.view.FloatMenu;

/**
 * 管理者，单例模式
 * Created by 15930 on 2018/3/4.
 */

public class ViewManager {

    private FloatBall floatBall;

    private FloatMenu floatMenu;

    private WindowManager windowManager;

    private WindowManager.LayoutParams floatBallParams;

    private WindowManager.LayoutParams floatMenuParams;

    private Context context;

    private static ViewManager manager;

    //私有化构造方法
    private ViewManager(Context context) {
        this.context = context;
        init();
    }

    //获取ViewManager实例
    public static ViewManager getInstance(Context context) {

        if (manager == null) {
            manager = new ViewManager(context);
        }
        return manager;
    }

    public void init() {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatBall = new FloatBall(context);
        floatMenu = new FloatMenu(context);
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            float startX;
            float startY;
            float tempX;
            float tempY;

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getRawX();
                        startY=event.getRawY();

                        tempX=event.getRawX();
                        tempY=event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float x=event.getRawX()-startX;
                        float y=event.getRawY()-startY;
                        //计算偏移量，刷新视图
                        floatBallParams.x += x;
                        floatBallParams.y += y;
                        floatBall.setDragState(true);
                        windowManager.updateViewLayout(floatBall,floatBallParams);
                        startX=event.getRawX();
                        startY=event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getRawX();
                        float endY = event.getRawY();
                        if (endX < getScreenWidth() / 2) {
                            endX = 0;
                        } else {
                            endX = getScreenWidth() - floatBall.width;
                        }
                        floatBallParams.x = (int) endX;
                        floatBall.setDragState(false);
                        windowManager.updateViewLayout(floatBall, floatBallParams);
                        //如果初始落点与松手落点的坐标差值超过6个像素，则拦截该点击事件
                        //否则继续传递，将事件交给OnClickListener函数处理
                        if (Math.abs(endX - tempX) > 6 && Math.abs(endY - tempY) > 6) {
                            return true;
                        }

                        break;
                }
                return false;
            }
        };
        View.OnClickListener clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                windowManager.removeView(floatBall);
                showFloatMenu();
                floatMenu.startAnimation();
            }
        };
        floatBall.setOnTouchListener(touchListener);
        floatBall.setOnClickListener(clickListener);



    }
    //显示浮动小球
    public void showFloatBall() {
        if (floatBallParams == null) {
            floatBallParams = new WindowManager.LayoutParams();
            floatBallParams.width = floatBall.width;
            floatBallParams.height = floatBall.height - getStatusHeight();
            floatBallParams.gravity = Gravity.TOP | Gravity.LEFT;
            floatBallParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            floatBallParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            floatBallParams.format = PixelFormat.RGBA_8888;
        }
        windowManager.addView(floatBall, floatBallParams);
    }
    //显示底部菜单
    private void showFloatMenu() {
        if (floatMenuParams == null) {
            floatMenuParams = new WindowManager.LayoutParams();
            floatMenuParams.width = getScreenWidth();
            floatMenuParams.height = getScreenHeight() - getStatusHeight();
            floatMenuParams.gravity = Gravity.BOTTOM;
            floatMenuParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            floatMenuParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            floatMenuParams.format = PixelFormat.RGBA_8888;
        }
        windowManager.addView(floatMenu, floatMenuParams);
    }
    //隐藏底部菜单
    public void hideFloatMenu() {
        if (floatMenu != null) {
            windowManager.removeView(floatMenu);
        }
    }

    //获取屏幕宽度
    public int getScreenWidth() {
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.x;
    }

    //获取屏幕高度
    public int getScreenHeight() {
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.y;
    }

    //获取状态栏高度
    public int getStatusHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object object = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(object);
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            return 0;
        }
    }

}
