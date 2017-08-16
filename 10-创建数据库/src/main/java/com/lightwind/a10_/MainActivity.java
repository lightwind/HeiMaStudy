package com.lightwind.a10_;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private MyOpenHelper mMyOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMyOpenHelper = new MyOpenHelper(this);
        // 打开或者创建数据库，如果是第一次就创建
//        SQLiteDatabase database = mMyOpenHelper.getWritableDatabase();
        // 打开或者创建数据库，如果是第一次就创建，如果磁盘满了，就返回只读
//        SQLiteDatabase database=myOpenHelper.getReadableDatabase();
    }

    @OnClick({R.id.insert, R.id.delete, R.id.update, R.id.select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 增加数据
            case R.id.insert:
                // 获取数据库对象
                SQLiteDatabase database1 = mMyOpenHelper.getWritableDatabase();
                // 执行增加一条的SQL语句
                database1.execSQL("insert into info(name, phone) values (?, ?)", new
                        Object[]{"张三", "12346879"});
                // 关闭数据库
                database1.close();
                break;
            // 删除数据
            case R.id.delete:
                SQLiteDatabase database2 = mMyOpenHelper.getWritableDatabase();
                database2.execSQL("delete from info where name = ?", new Object[]{"张三"});
                database2.close();
                break;
            // 修改数据
            case R.id.update:
                SQLiteDatabase database3 = mMyOpenHelper.getWritableDatabase();
                database3.execSQL("update info set phone = ? where name = ?", new
                        Object[]{"123468722", "张三"});
                database3.close();
                break;
            // 查询数据
            case R.id.select:
                SQLiteDatabase database4 = mMyOpenHelper.getWritableDatabase();
                Cursor cursor = database4.rawQuery("select * from info", null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(1);
                        String phone = cursor.getString(2);
                        Log.i("TAG", name + "---" + phone);
                    }
                }
                database4.close();
                break;
        }
    }
}
