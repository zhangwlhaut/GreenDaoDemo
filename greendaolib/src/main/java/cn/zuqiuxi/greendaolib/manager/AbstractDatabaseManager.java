package cn.zuqiuxi.greendaolib.manager;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collection;
import java.util.List;

import cn.zuqiuxi.greendaolib.base.IDatabase;
import cn.zuqiuxi.greendaolib.base.IndexCreator;
import cn.zuqiuxi.greendaolib.base.UpdateSQLiteOpenHelper;
import cn.zuqiuxi.greendaolib.gen.DaoMaster;
import cn.zuqiuxi.greendaolib.gen.DaoSession;

/**
 * Created by zhangwl on 2016/9/1 0001.
 */
public abstract class AbstractDatabaseManager<M, K> implements IDatabase<M, K> {
    private static final String DEFAULT_DATABASE_NAME = "greendao.db";
    /**
     * The Android Activity reference for access to DatabaseManager.
     */
  //  private static DaoMaster.DevOpenHelper mHelper;
    private static UpdateSQLiteOpenHelper mHelper;
    protected static DaoSession daoSession;

    /**
     * 初始化OpenHelper
     *
     * @param context
     */
    public static void initOpenHelper(@NonNull Context context) {
        mHelper = getOpenHelper(context, DEFAULT_DATABASE_NAME);
        openWritableDb();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        //创建唯一索引
        IndexCreator.getInstance().createIndex();
    }

    /**
     * 初始化OpenHelper     *
     *
     * @param context
     * @param dataBaseName
     */
    public static void initOpenHelper(@NonNull Context context, @NonNull String dataBaseName) {
        mHelper = getOpenHelper(context, dataBaseName);
        openWritableDb();
    }

    /**
     * Query for readable DB
     */
    protected static void openReadableDb() throws SQLiteException {
        //IdentityScopeType.None  不启用缓存
        daoSession = new DaoMaster(getReadableDatabase()).newSession(IdentityScopeType.None);
    }

    /**
     * Query for writable DB
     */
    protected static void openWritableDb() throws SQLiteException {
        //不启用缓存
        daoSession = new DaoMaster(getWritableDatabase()).newSession(IdentityScopeType.None);
    }

    private static SQLiteDatabase getWritableDatabase() {
        return mHelper.getWritableDatabase();
    }

    private static SQLiteDatabase getReadableDatabase() {
        return mHelper.getReadableDatabase();
    }

    /**
     * 在applicaiton中初始化DatabaseHelper
     */
    private static UpdateSQLiteOpenHelper getOpenHelper(@NonNull Context context, @Nullable String dataBaseName) {
        closeDbConnections();
       // return new DaoMaster.DevOpenHelper(context, dataBaseName, null);
        //数据库升级的配置
        //UpdateSQLiteOpenHelper helper = ;
        return new UpdateSQLiteOpenHelper(context, dataBaseName, null);
    }

    /**
     * 只关闭helper就好,看源码就知道helper关闭的时候会关闭数据库
     */
    public static void closeDbConnections() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

    @Override
    public void clearDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

    @Override
    public boolean dropDatabase() {
        try {
            openWritableDb();
            // DaoMaster.dropAllTables(database, true); // drops all tables
            // mHelper.onCreate(database); // creates the tables
//          daoSession.deleteAll(BankCardBean.class); // clear all elements
            // from
            // a table
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean insert(@NonNull M m) {
        try {
            if (m == null)
                return false;
            openWritableDb();
            getAbstractDao().insert(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean insertOrReplace(@NonNull M m) {
        try {
            if (m == null)
                return false;
            openWritableDb();
            getAbstractDao().insertOrReplace(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(@NonNull M m) {
        try {
            if (m == null)
                return false;
            openWritableDb();
            getAbstractDao().delete(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByKey(K key) {
        try {
            if (TextUtils.isEmpty(key.toString()))
                return false;
            openWritableDb();
            getAbstractDao().deleteByKey(key);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByKeyInTx(K... key) {
        try {
            openWritableDb();
            getAbstractDao().deleteByKeyInTx(key);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteList(List<M> mList) {
        try {
            if (mList == null || mList.size() == 0)
                return false;
            openWritableDb();
            getAbstractDao().deleteInTx(mList);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try {
            openWritableDb();
            getAbstractDao().deleteAll();
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(@NonNull M m) {
        try {
            if (m == null)
                return false;
            openWritableDb();
            getAbstractDao().update(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInTx(M... m) {
        try {
            if (m == null)
                return false;
            openWritableDb();
            getAbstractDao().updateInTx(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateList(List<M> mList) {
        try {
            if (mList == null || mList.size() == 0)
                return false;
            openWritableDb();
            getAbstractDao().updateInTx(mList);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public M selectByPrimaryKey(@NonNull K key) {
        try {
            openReadableDb();
            return getAbstractDao().load(key);
        } catch (SQLiteException e) {
            return null;
        }
    }

    @Override
    public List<M> loadAll() {
        openReadableDb();
        return getAbstractDao().loadAll();
    }

    @Override
    public boolean refresh(@NonNull M m) {
        try {
            if (m == null)
                return false;
            openWritableDb();
            getAbstractDao().refresh(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public void runInTx(Runnable runnable) {
        try {
            openWritableDb();
            daoSession.runInTx(runnable);
        } catch (SQLiteException e) {
        }
    }

    @Override
    public boolean insertList(@NonNull List<M> list) {
        try {
            if (list == null || list.size() == 0)
                return false;
            openWritableDb();
            getAbstractDao().insertInTx(list);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    /**
     * @param list
     * @return
     */
    @Override
    public boolean insertOrReplaceList(@NonNull List<M> list) {
        try {
            if (list == null || list.size() == 0)
                return false;
            openWritableDb();
            getAbstractDao().insertOrReplaceInTx(list);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public QueryBuilder<M> getQueryBuilder() {
        openReadableDb();
        return getAbstractDao().queryBuilder();
    }

    /**
     * @param where
     * @param selectionArg
     * @return
     */
    @Override
    public List<M> queryRaw(String where, String... selectionArg) {
        openReadableDb();
        return getAbstractDao().queryRaw(where, selectionArg);
    }


    public Query<M> queryRawCreate(String where, Object... selectionArg) {
        openReadableDb();
        return getAbstractDao().queryRawCreate(where, selectionArg);
    }

    public Query<M> queryRawCreateListArgs(String where, Collection<Object> selectionArg) {
        openReadableDb();
        return getAbstractDao().queryRawCreateListArgs(where, selectionArg);
    }

    //******************************************
   /* @Override
    *//**
     *   Rx  插入多条数据
     *//*
    public Observable<Iterable<M>> insertMultObjects(List<M> objects) {
        if (null == objects || objects.isEmpty()) {
            return Observable.error(new Throwable("null"));
        }

        return getAbstractDao().rx().insertOrReplaceInTx(objects);
    }
*/
    /**
     * 自定义多字段唯一索引
     *
     * @param
     */
    public void createIndex(String... rows) {
        StringBuffer sb = new StringBuffer();
        for (String s : rows) {
            if (s.equals(rows[rows.length - 1])) {
                sb.append(s);
            } else {
                sb.append(s+",");
            }
        }
        String tablename = getAbstractDao().getTablename();

        String sql = "create unique index if not exists " + tablename + "_index on " + tablename + "(" + sb.toString()+ ")";

        daoSession.getDatabase().execSQL(sql);
    }
    /**
     * 自定义多条件查询
     */
/*    @Override
    public  Observable<List<M>> queryBuilderPaging(int limit, int offset, WhereCondition cond, WhereCondition... condMore){
        return getAbstractDao().queryBuilder().limit(limit).offset(offset).where(cond, condMore).rx().list();
    }*/
    /**
     * 获取Dao
     *
     * @return
     */
    abstract AbstractDao<M, K> getAbstractDao();



}
