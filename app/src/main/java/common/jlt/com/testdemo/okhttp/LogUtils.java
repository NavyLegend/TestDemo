package common.jlt.com.testdemo.okhttp;

import android.util.Log;




/**
 * Created by Administrator on 2017/2/26.
 */

public class LogUtils {
    private static boolean debug=true;
    public static final String Tag="okhttp";

    public static void e(String msg){
   if (debug)
       Log.e(Tag, msg);
   }

}
