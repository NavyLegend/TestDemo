package common.jlt.com.testdemo.rxjava2;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("getUser")
    Observable<BaseEntity<UserBean>> getUser(@FieldMap Map<String, String> map);
}
