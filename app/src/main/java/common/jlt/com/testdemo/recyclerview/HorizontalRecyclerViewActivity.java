package common.jlt.com.testdemo.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import common.jlt.com.testdemo.R;

/**
 * 横向的RecyclerView
 */
public class HorizontalRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<ItemBean> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horiental_recycler_view);
        recyclerView = ((RecyclerView) findViewById(R.id.recyclerView));
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        for (int i = 0; i < 20; i++) {
            ItemBean itemBean=new ItemBean();
            itemBean.setName("name"+i);
            list.add(itemBean);
        }

        myAdapter=new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}
