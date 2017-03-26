package common.jlt.com.testdemo.recyclerviewgallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import common.jlt.com.testdemo.R;

/**
 * 由RecyclerView打造Gallery画廊
 * http://www.jianshu.com/p/3b827b6384db
 */
public class RecyclerViewGallery extends AppCompatActivity {
    private RecyclerView rv;
    private List<Integer> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_gallery);
        rv = (RecyclerView) findViewById(R.id.rv);
        mDataList = new ArrayList<>();
        mDataList.add(R.mipmap.pic_1);
        mDataList.add(R.mipmap.pic_2);
        mDataList.add(R.mipmap.pic_3);
        mDataList.add(R.mipmap.pic_4);
        mDataList.add(R.mipmap.pic_5);
        mDataList.add(R.mipmap.pic_6);

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new CardRvAdapter(this, mDataList));
        /**1.官方自带的 LinearSnapHelper
         * SnapHelper 的实现原理就是是监听 RecyclerView.OnFlingListener 中的 onFling 接口。
         * support library 中只提供了一个继承类 LinearSnapHelper ，LinearSnapHelper 是抽象类 SnapHelper 的具体实现。
         * 通过 LinearSnapHelper，我们就可以使 RecyclerView 实现类似 ViewPager 的功能，无论怎么滑动最终都会停留在列表页面正中间。
         */
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        mLinearSnapHelper.attachToRecyclerView(rv);
        /**
         * 2.自定义 SnapHelper
         */
//        CustomSnapHelper mMySnapHelper = new CustomSnapHelper();
//        mMySnapHelper.attachToRecyclerView(rv);
    }
}
