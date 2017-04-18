package common.jlt.com.testdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.jpush.android.api.JPushInterface;
import common.jlt.com.testdemo.checknetstatus.CheckNetStatus;
import common.jlt.com.testdemo.customviewpager.CustomViewPager;
import common.jlt.com.testdemo.gradienttitlebar.GradientTitleBarActivity;
import common.jlt.com.testdemo.greendao.GreenDaoActivity;
import common.jlt.com.testdemo.okhttp.OkHttpActivity;
import common.jlt.com.testdemo.recyclerviewgallery.RecyclerViewGallery;
import common.jlt.com.testdemo.rxjava.RxJavaActivity;
import common.jlt.com.testdemo.sqlite.SQLiteTestActivity;
import common.jlt.com.testdemo.strategy.Strategy;
import common.jlt.com.testdemo.viewpagergallery.MagicViewPager;
import common.jlt.com.testdemo.viewpagergallery.ViewPagerGallery;

/**
 * 首页
 * Created by ZhangHaijun
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.check_net_status).setOnClickListener(this);
        findViewById(R.id.strategy).setOnClickListener(this);
        findViewById(R.id.okhttp).setOnClickListener(this);
        findViewById(R.id.greenDao).setOnClickListener(this);
        findViewById(R.id.RxJava).setOnClickListener(this);
        findViewById(R.id.RecyclerViewGallery).setOnClickListener(this);
        findViewById(R.id.gradientTitleBar).setOnClickListener(this);
        findViewById(R.id.viewpagerGallery).setOnClickListener(this);
        findViewById(R.id.viewpagerGallery2).setOnClickListener(this);
        findViewById(R.id.customeViewPager).setOnClickListener(this);
        findViewById(R.id.RxJava2).setOnClickListener(this);
        findViewById(R.id.SQLiteDb).setOnClickListener(this);

        /**
         * 初始化极光推送
         */
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //检测网络状态
            case R.id.check_net_status:
                startActivity(new Intent(this, CheckNetStatus.class));
                break;
            //设计模式：策略模式
            case R.id.strategy:
                startActivity(new Intent(this, Strategy.class));
                break;
              //OKHttp
            case R.id.okhttp:
                startActivity(new Intent(this, OkHttpActivity.class));
                break;
            //GreenDao的使用案例
            case R.id.greenDao:
                startActivity(new Intent(this, GreenDaoActivity.class));
                break;
            //SQLiteDate存储数据
            case R.id.SQLiteDb:
                startActivity(new Intent(this, SQLiteTestActivity.class));
                break;
            //RxJava + Retriofit + OkHttp 简单Demo
            case R.id.RxJava:
                startActivity(new Intent(this, RxJavaActivity.class));
                break;
//            //RxJava + Retriofit 的封装
//            case R.id.RxJava2:
//                startActivity(new Intent(this, RxJava2Activity.class));
//                break;
            //由RecyclerView打造Gallery画廊
            case R.id.RecyclerViewGallery:
                startActivity(new Intent(this, RecyclerViewGallery.class));
                break;
            //渐变的titleBar
            case R.id.gradientTitleBar:
                startActivity(new Intent(this, GradientTitleBarActivity.class));
                break;
            //viewpagerGallery
            case R.id.viewpagerGallery:
                startActivity(new Intent(this, ViewPagerGallery.class));
                break;
            //MagicViewPager
            case R.id.viewpagerGallery2:
                startActivity(new Intent(this, MagicViewPager.class));
                break;
            //自定义View来实现ViewPager效果
            case R.id.customeViewPager:
                startActivity(new Intent(this, CustomViewPager.class));
                break;
            default:
                break;
        }
    }
}
