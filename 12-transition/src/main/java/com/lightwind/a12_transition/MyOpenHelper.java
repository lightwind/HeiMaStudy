package com.lightwind.a12_transition;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建数据库
 * Created by lightwind on 2017/8/16.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, "Account.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info (_id integer primary key autoincrement,name varchar(20)," +
                "phone varchar(20),money varchar(20))");
        db.execSQL("insert into info ('name', 'phone', 'money') values ('张三', '123456', '2000')");
        db.execSQL("insert into info ('name', 'phone', 'money') values ('李四', '456789', '5000')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
