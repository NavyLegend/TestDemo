package common.jlt.com.testdemo.rxjava;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**Api 接口
 * Created by ZhangHaijun on 2017/3/10.
 */

public interface ApiService {
    String baseUrl="http://japi.juhe.cn/joke/content/";
    public static final String DESC = "desc"; // 指定时间之前发布的
    public static final String ASC = "asc";   // 指定时间之后发布的
    @GET("text.from?key=ae240f7fba620fc370b803566654949e")
    Observable<JokeBean> getCurrentJokeData(@Query("page") int page,
                                            @Query("pagesize") int pagesize);
    //接口完整地址
    // http://japi.juhe.cn/joke/content/list.from?key=ae240f7fba620fc370b803566654949e&page=1&pagesize=5&sort=desc
    @GET("list.from?key=ae240f7fba620fc370b803566654949e")
    Observable<JokeBean> getAssignJokeData(
            @Query("time") long time,
            @Query("page") int page,
            @Query("pagesize") int pagesize,
            @Query("sort") String sort
    );

}
