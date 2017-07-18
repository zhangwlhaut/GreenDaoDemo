
## GreenDao 3.0+ 使用封装
### base文件下
1. DaoUtils中单例获取manager
2. IndexCreator 创建唯一索引
 ``` java 
 DaoUtils.getUserManagerInstance()
 .createIndex(UserDao.Properties.Userid.columnName,UserDao.Properties.Mobile.columnName);
 ```
 3. UpdateSQLiteOpenHelper 数据库升级
 4. IDatabase 定义常用的数据库操作方法
 
 ### entity 文件下
  1. 所有的实体类
  
  ### manager 文件下是对各个实体类对应的数据操作方法
  
 
