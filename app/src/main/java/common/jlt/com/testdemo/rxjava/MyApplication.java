package common.jlt.com.testdemo.rxjava;

import android.app.Application;
import android.content.Context;

/**Application
 * Created by ZhangHaijun on 2017/1/22.
 */

public class MyApplication extends Application {
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    private MyApplication getApplication(){
        return application;
    }
    public static Context getContext(){
        return  application.getApplicationContext();
    }

}
