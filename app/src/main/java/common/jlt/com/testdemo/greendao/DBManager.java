package common.jlt.com.testdemo.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import common.jlt.com.testdemo.greendao.gen.DaoMaster;
import common.jlt.com.testdemo.greendao.gen.DaoSession;

/**
 * Created by Administrator on 2017/2/27.
 */

public class DBManager {
    public static final String dbName="student-db";
    private static DBManager instance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;
    private SQLiteDatabase dataBase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    public DBManager(Context context) {
        this.context = context;
        openHelper=new DaoMaster.DevOpenHelper(context,dbName,null);
    }
    /**
     * 获取单例引用
     */
    public static DBManager getInstance(Context context) {
        if (instance==null){
            synchronized (DBManager.class){
                instance=new DBManager(context);
            }
        }
        return instance;
    }
    public DaoSession getDaoSession() {
        if (daoMaster==null) {
            daoMaster = new DaoMaster(openHelper.getWritableDatabase());
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
    public SQLiteDatabase getDataBase() {
        return dataBase;
    }
}
