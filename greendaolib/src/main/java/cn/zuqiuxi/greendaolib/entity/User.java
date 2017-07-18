package cn.zuqiuxi.greendaolib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 实体类
 * Created by zhwl on 2017/7/18.
 */

@Entity
public class User {
    /**
     * 主键 自增长
     */
    @Id(autoincrement = true)
    private Long id;
    /**
     * userid 唯一
     */
    @Index(unique = true)
    private String userid;
    @Unique
    private String mobile;
    private int age;
    /**
     * 姓名不能为空
     */
    @NotNull
    private String name;
    @Generated(hash = 975253919)
    public User(Long id, String userid, String mobile, int age, @NotNull String name) {
        this.id = id;
        this.userid = userid;
        this.mobile = mobile;
        this.age = age;
        this.name = name;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
