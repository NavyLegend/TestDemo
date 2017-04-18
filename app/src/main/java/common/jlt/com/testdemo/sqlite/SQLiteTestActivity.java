package common.jlt.com.testdemo.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import common.jlt.com.testdemo.R;
import common.jlt.com.testdemo.sqlite.dao.ContactDao;

public class SQLiteTestActivity extends AppCompatActivity {
    private EditText mEtName;
    private EditText mEtPhone;
    private ContactDao mDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test);
        mEtName= (EditText) findViewById(R.id.mEtName);
        mEtPhone= (EditText) findViewById(R.id.mEtPhone);
        mDao=new ContactDao(this);
    }
    public void add(View view){

        String name=mEtName.getText().toString().trim();
        String phone=mEtPhone.getText().toString().trim();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)){
            Toast.makeText(this,"填写不完整",Toast.LENGTH_SHORT).show();
            return;
        }else{
            long addLong = mDao.add(name, phone);
            if(addLong==-1){
                Toast.makeText(this,"添加失败",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"数据添加在第  "+addLong+"   行",Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void delete(View view){
        String name=mEtName.getText().toString().trim();


        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"填写不完整",Toast.LENGTH_SHORT).show();
            return;
        }else{
            int deleteDate = mDao.delete(name);
            if(deleteDate==-1){
                Toast.makeText(this,"删除失败",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"成功删除  "+deleteDate+"   条数据",Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void update(View view){
        String name=mEtName.getText().toString().trim();
        String phone=mEtPhone.getText().toString().trim();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)){
            Toast.makeText(this,"填写不完整",Toast.LENGTH_SHORT).show();
            return;
        }else{
            int count=mDao.updateDate(phone,name);
            if(count==-1){
                Toast.makeText(this,"更新失败",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"数据更新了  "+count+"   行",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void query(View view){

        String name=mEtName.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"填写不完整",Toast.LENGTH_SHORT).show();
            return;
        }else{
            String phoneResult = mDao.quaryDate(name);
            Toast.makeText(this,"手机号码为:    "+phoneResult,Toast.LENGTH_SHORT).show();

        }
    }


}
