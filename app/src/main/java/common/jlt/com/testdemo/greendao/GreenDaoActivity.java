package common.jlt.com.testdemo.greendao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import common.jlt.com.testdemo.R;
import common.jlt.com.testdemo.greendao.entity.User;
import common.jlt.com.testdemo.greendao.gen.UserDao;

/**greenDao的使用Demo
 *
 * 参考文章：
 * https://www.daidingkang.cc/2016/12/08/GreenDao/
 * http://www.cnblogs.com/whoislcj/p/5651396.html
 */
public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText1, editText2;
    private ListView listView;
    private User user;
    private UserDao userDao;
    private Long postion = 0l;
    private List<User> userList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        editText1 = ((EditText) findViewById(R.id.editText1));
        editText2 = ((EditText) findViewById(R.id.editText2));
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        userList = new ArrayList<>();
        userDao = DBManager.getInstance(this).getDaoSession().getUserDao();
        listView = ((ListView) findViewById(R.id.listview));

       //修改
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                final Long id = userList.get(position).getId();

                final AlertDialog dialog=new AlertDialog.Builder(GreenDaoActivity.this).create();
                View edit_view=View.inflate(getApplicationContext(),R.layout.layout,null);
                final EditText editText1 = (EditText) edit_view.findViewById(R.id.editText1);
                editText1.setTextColor(Color.RED);
                final EditText editText2 = (EditText) edit_view.findViewById(R.id.editText2);
                editText2.setTextColor(Color.RED);
                Button button1 = (Button) edit_view.findViewById(R.id.button1);
                Button button2 = (Button) edit_view.findViewById(R.id.button2);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(editText1.getText())&&!TextUtils.isEmpty(editText2.getText())){
                            updateDate(id,editText1.getText().toString(),editText2.getText().toString());
                            dialog.dismiss();
                        }else {
                            Toast.makeText(GreenDaoActivity.this, "请输入数据！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setView(edit_view);
                dialog.setCancelable(false);
                dialog.show();
            }
        });
        //删除
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new AlertDialog.Builder(GreenDaoActivity.this).setMessage("确认删除吗").setTitle("提示").setNegativeButton("cancle", null).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteDate(userList.get(position));
                    }
                }).create().show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //增
            case R.id.button1:
                addDate();
                break;
            //查
            case R.id.button4:
                findDate();
                break;
        }
    }

    /**
     * 增加数据
     */
    private void addDate() {
        if (!TextUtils.isEmpty(editText1.getText()) && !TextUtils.isEmpty(editText2.getText())) {
            user = new User(null, editText1.getText().toString(), editText2.getText().toString());
            try {
                userDao.insert(user);
                Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
                findDate();
            } catch (Exception e) {
                Toast.makeText(this, "添加失败！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请输入数据！", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    /**
     * 删除数据
     *
     * 根据对象来判断并删除数据库中的数据
     * @param user
     */
    private void deleteDate(User user) {
        userDao.delete(user);
        findDate();

    }

    /**
     * 修改数据
     * 根据id来索引数据并修改相应id所对应的内容
     */
    private void updateDate(Long id,String s1,String s2) {
        user = new User(id, s1, s2);
        userDao.update(user);
        findDate();
    }

    /**
     * 查询所有数据
     */
    private void findDate() {
        userList = userDao.loadAll();
        myAdapter = new MyAdapter(userList, this);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }


}
