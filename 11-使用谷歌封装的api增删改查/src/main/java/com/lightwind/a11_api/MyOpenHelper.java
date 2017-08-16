package com.lightwind.a11_api;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 自定义数据库帮助类
 * Created by lightwind on 2017/8/15.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    /**
     * @param context 上下文
     *                //@param name    数据库名字
     *                //@param factory 目的创建cursor对象
     *                //@param version 数据库的版本，从1开始
     */
    public MyOpenHelper(Context context) {
        super(context, "lightwind.db", null, 2);
    }

    /**
     * Called when the database is created for the first time.
     * 当数据库的第一次创建的时候调用
     * 这个方法特别适合做表结构的初始化，创建表就是写SQL语句
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // 创建info表
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20))");

    }

    /**
     * Called when the database needs to be upgraded.
     * 当数据库版本需要升级的时候调用
     * 这个方法适合做表结构的更新
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // 在info表中添加phone列
        db.execSQL("alter table info add phone varchar(20)");

    }
}
