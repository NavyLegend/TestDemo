package common.jlt.com.testdemo.greendao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import common.jlt.com.testdemo.R;
import common.jlt.com.testdemo.greendao.entity.User;

/**
 * Created by Administrator on 2017/2/27.
 */

public class MyAdapter extends BaseAdapter {
    private List<User> userList;
    private Context context;

    public MyAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_layout,null);
            holder=new ViewHolder();
            holder.name= (TextView) convertView.findViewById(R.id.name);
            holder.age= (TextView) convertView.findViewById(R.id.age);
            convertView.setTag(holder);
        }else {
          holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText(userList.get(i).getName());
        holder.age.setText(userList.get(i).getAge());
        return convertView;
    }
    static class ViewHolder{
        TextView name;
        TextView age;
    }
}
