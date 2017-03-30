package common.jlt.com.testdemo.rxjava;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.BuildConfig;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**Retrofit 客户端
 * Created by ZhangHaijun on 2017/3/10.
 */

public class RetrofitClient {
    private static final String CACHE_NAME  = "retrofitdmocache";
    private static RetrofitClient instance=null;
    private Retrofit retrofit;
    private RetrofitClient(){}//私有化构造方法

    public static RetrofitClient getInstance() {
        if (instance==null){
            synchronized (RetrofitClient.class){
                if (instance==null){
                    instance=new RetrofitClient();
                }
            }
        }
        return instance;
    }
    //普通的方法来返回Retrofit实例
    public Retrofit creatRetrofit() {

        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        /**
         * log拦截器
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) { // 判断是否为debug
            // 如果为 debug 模式，则添加日志拦截器
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        /**
         * 添加请求头
         */
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder1 = chain.request().newBuilder();
                builder1.addHeader("token","123");
                return chain.proceed(builder1.build());
            }
        };


        /**
         * 设置缓存
         */

        //设置缓存目录
        File cacheFile=new File(MyApplication.getContext().getExternalCacheDir(),CACHE_NAME);
        //生成缓存，20M
        Cache cache=new Cache(cacheFile,1024*1024*20);
        //缓存拦截器
        Interceptor cacheInterceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                //网络不可用
                if (!NetworkUtils.isAvailable(MyApplication.getContext())){
                    //在请求头中加入：强制使用缓存，不访问网络
                    request=request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                Response response=chain.proceed(request);
                //网络可用
                if (NetworkUtils.isAvailable(MyApplication.getContext())){
                    int maxAge=0;
                    // 有网络时 在响应头中加入：设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("pragma")
                            .build();

                }else {
                    // 无网络时，在响应头中加入：设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("pragma")
                            .build();
                }
                return response;
            }
        };
        builder.addInterceptor(interceptor)
                .addInterceptor(cacheInterceptor)
//                .addInterceptor(headerInterceptor)
                .cache(cache)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);//错误重连

        return new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    ;

}
