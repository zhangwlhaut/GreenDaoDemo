package cn.zuqiuxi;

import android.app.Application;

import cn.zuqiuxi.greendaolib.manager.AbstractDatabaseManager;

/**
 * Created by zhwl on 2017/7/18.
 */

public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化数据库
         */
        AbstractDatabaseManager.initOpenHelper(getApplicationContext());//GreenDao初始化数据库

    }
}
