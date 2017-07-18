package cn.zuqiuxi.greendaolib.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import cn.zuqiuxi.greendaolib.gen.DaoMaster;
import cn.zuqiuxi.greendaolib.gen.UserDao;


/**
 * Created by zhwl on 2017/2/20.
 */

public class UpdateSQLiteOpenHelper extends DaoMaster.OpenHelper{
    public UpdateSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db,UserDao.class);
    }
}
