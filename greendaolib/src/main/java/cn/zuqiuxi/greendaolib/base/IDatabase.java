package cn.zuqiuxi.greendaolib.base;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by zhangwl on 2016/9/1 0001.
 */
public interface IDatabase<M, K> {

    boolean insert(M m);

    boolean delete(M m);

    boolean deleteByKey(K key);

    boolean deleteList(List<M> mList);

    boolean deleteByKeyInTx(K... key);

    boolean deleteAll();

    boolean insertOrReplace(@NonNull M m);

    boolean update(M m);

    boolean updateInTx(M... m);

    boolean updateList(List<M> mList);

    M selectByPrimaryKey(K key);

    List<M> loadAll();

    boolean refresh(M m);
    /**
     * 清理缓存
     */
    void clearDaoSession();

    /**
     * Delete all tables and content from our database
     */
    boolean dropDatabase();

    /**
     * 事务
     */
    void runInTx(Runnable runnable);

    /**
     * 添加集合
     *
     * @param mList
     */
    boolean insertList(List<M> mList);

    /**
     * 添加集合
     *
     * @param mList
     */
    boolean insertOrReplaceList(List<M> mList);

    /**
     * 自定义查询
     *
     * @return
     */
    QueryBuilder<M> getQueryBuilder();

    /**
     * @param where
     * @param selectionArg
     * @return
     */
    List<M> queryRaw(String where, String... selectionArg);
    /**
     * rx  插入或者替换一个集合
     */
   // Observable<Iterable<M>> insertMultObjects(List<M> object);

    /**
     * 分页查询
     * @param limit
     * @param offset
     * @return
     */
   // Observable<List<M>> queryBuilderPaging(int limit, int offset, WhereCondition cond, WhereCondition... condMore);
}