package cn.zuqiuxi.greendaolib.base;


import cn.zuqiuxi.greendaolib.manager.UserManager;

/**
 * 获取数据库操作对象管理类
 * Created by zhangwl on 2016/8/30 0030.
 */
public class DaoUtils {
    private static UserManager userManager;


    /**
     * 单列模式获取UserManager对象
     *
     * @return
     */
    public static UserManager getUserManagerInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }


}
