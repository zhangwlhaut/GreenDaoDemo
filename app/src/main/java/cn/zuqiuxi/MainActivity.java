package cn.zuqiuxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zuqiuxi.greendaolib.base.DaoUtils;
import cn.zuqiuxi.greendaolib.entity.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User();
        user.setAge(18);
        user.setName("xxx");
        user.setUserid("0001");
        user.setMobile("17700000000");
        DaoUtils.getUserManagerInstance().save(user);
    }
}
