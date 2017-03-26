package common.jlt.com.testdemo.okhttp;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import common.jlt.com.testdemo.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {
    private String mBaseUrl= "http://zhushou.72g.com/app/gift/gift_list/";
    private TextView content;
    //1.拿到OkHttpClient对象
    OkHttpClient okHttpClient=new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        content = ((TextView) findViewById(R.id.content));
    }

    /**
     * Get请求
     * @param view
     */
    public void doGet(View view) {

        // 2.构造Request
        Request.Builder builder=new Request.Builder();
        Request request = builder.get().url("http://www.imooc.com/").build();
        executeRequest(request);
    }

    /**
     * Post请求
     * @param view
     */
    public void doPost(View view) {
        Request.Builder builder=new Request.Builder();
        RequestBody requestBodyPost = new FormBody.Builder().add("page", "1").add("code", "news").add("pageSize", "20").add("parentid", "0").add("type", "1").build();
        Request request = builder.url(mBaseUrl).post(requestBodyPost).build();
        executeRequest(request);
    }

    /**
     * Post字符串
     * @param view
     */
    public void doPostString(View view) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;chaset=utf-8"), "{username:ZhangHaijun,password:123456}");
        Request request = new Request.Builder().url(mBaseUrl).post(requestBody).build();
        executeRequest(request);
    }

    /**
     * Post File文件
     * @param view
     */
    public void doPostFile(View view) {
        File file=new File(Environment.getExternalStorageDirectory(),"banner.jpg");
        if (!file.exists()){
            LogUtils.e(file.getAbsolutePath()+" not exist");
            return;
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        Request request = new Request.Builder().url(mBaseUrl).post(requestBody).build();
        executeRequest(request);
    }

    private void executeRequest(Request request) {
        //3.执行请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e("onfail"+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                /**
                 * 子线程中（为了支持大文件下载）
                 */
                LogUtils.e("onResponse :");
                final String result = response.body().string();
                LogUtils.e(result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        content.setText(result);
                    }
                });

//                InputStream inputStream=response.body().byteStream();//支持大文件下载

            }
        });
    }

}
