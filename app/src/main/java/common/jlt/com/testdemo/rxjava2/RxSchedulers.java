package common.jlt.com.testdemo.rxjava2;

import android.content.Context;
import android.widget.Toast;

import common.jlt.com.testdemo.rxjava.NetworkUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**通常我们会在IO线程进行请求，在主线程进行回调
 * Created by Administrator on 2017/4/12.
 */

public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> compose(final Context context) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (!NetworkUtils.isAvailable(context)) {
                                    Toast.makeText(context, "网络异常！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
