package cn.zuqiuxi.greendaolib.manager;

import org.greenrobot.greendao.AbstractDao;

import cn.zuqiuxi.greendaolib.entity.User;

/**
 * 关于用户表的所有操作都在这个类内完成
 * Created by zhwl on 2017/7/18.
 */

public class UserManager extends AbstractDatabaseManager<User, Long> {
    @Override
    AbstractDao<User, Long> getAbstractDao() {
        return daoSession.getUserDao();
    }

    /**
     * 保存用户,存在的话更新用户信息
     * @param user
     */
    public boolean save(User user){
        return insertOrReplace(user);
    }
}
