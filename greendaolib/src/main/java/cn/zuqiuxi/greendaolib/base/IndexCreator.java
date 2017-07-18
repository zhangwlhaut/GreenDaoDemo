package cn.zuqiuxi.greendaolib.base;


import cn.zuqiuxi.greendaolib.gen.UserDao;

/**
 * 用来统一生产自定义索引
 * Created by zhangwl on 2016/9/2 0002.
 */
public class IndexCreator {
    private IndexCreator() {

    }

    public static IndexCreator instance;

    public static IndexCreator getInstance() {
        if (instance == null) {
            synchronized (IndexCreator.class) {
                if (instance == null) {
                    instance = new IndexCreator();
                }
            }
        }
        return instance;
    }

    /**
     * 生成所有表的索引
     */
    public void createIndex() {

        /**
         * 生成联合主键，保证唯一,Userid和mobile联合唯一
         */
        DaoUtils.getUserManagerInstance().createIndex(UserDao.Properties.Userid.columnName,UserDao.Properties.Mobile.columnName);


    }
}
