package common.jlt.com.testdemo.rxjava2;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/4/12.
 */

public interface RetrofitService {
    @FormUrlEncoded
    @POST("account/login")
    Observable<BaseEntity<UserInfo>> login(
            @Field("userId") String userId,
            @Field("password") String password
    );

//    @GET("video/getUrl")
//    Observable<BaseEntity<VideoUrl>> getVideoUrl(
//            @Query("id") long id
//    );

    @FormUrlEncoded
    @POST("user/addVideo")
    Observable<BaseEntity<Boolean>> addVideo(
            @FieldMap Map<String, Object> map
    );

}
