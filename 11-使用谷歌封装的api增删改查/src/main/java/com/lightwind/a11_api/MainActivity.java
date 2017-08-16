package com.lightwind.a11_api;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
    }

    @OnClick({R.id.insert, R.id.delete, R.id.update, R.id.select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 增加数据
            case R.id.insert:
                // 获取数据库对象
                SQLiteDatabase database1 = mMyOpenHelper.getWritableDatabase();
                // 执行增加一条的SQL语句
                // ContentValues内部封装了一个Map集合，其中key：表的列名，value：相应的值。
                ContentValues values1 = new ContentValues();
                values1.put("name", "张三");
                values1.put("phone", "110");
                // 返回值代表新行的id
                long insert = database1.insert("info", null, values1);
                // 关闭数据库
                database1.close();
                if (insert > 0) {
                    Toast.makeText(this, "添加成功：" + insert, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "添加失败：" + insert, Toast.LENGTH_SHORT).show();
                }
                break;
            // 删除数据
            case R.id.delete:
                SQLiteDatabase database2 = mMyOpenHelper.getWritableDatabase();
                // 返回删除的行数
                int delete = database2.delete("info", "name = ?", new String[]{"李四"});
                database2.close();
                Toast.makeText(this, "删除了：" + delete + "行", Toast.LENGTH_SHORT).show();
                break;
            // 修改数据
            case R.id.update:
                SQLiteDatabase database3 = mMyOpenHelper.getWritableDatabase();
                ContentValues values2 = new ContentValues();
                values2.put("phone", "114");
                // 返回修改的行数
                int update = database3.update("info", values2, "name = ?", new String[]{"张三"});
                database3.close();
                Toast.makeText(this, "更新了：" + update + "行", Toast.LENGTH_SHORT).show();
                break;
            // 查询数据
            case R.id.select:
                SQLiteDatabase database4 = mMyOpenHelper.getWritableDatabase();
                // columns  代表要查询的列，null代表查询所有列
                // selection    代表根据什么查询phone
                // selectionArgs    查询的具体信息
                // groupBy  根据什么分组
                // having   过滤条件
                // orderBy  根据什么排序
                Cursor cursor = database4.query("info", new String[]{"phone"}, "name = ?", new
                        String[]{"张三"}, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        // 因为在前面查询条件（第二个参数）中已经列出了要查询的条件，而phone是在第一位，
                        // 所以索引值为0
                        String phone = cursor.getString(0);
                        Log.d("TAG", phone);
                    }
                }
                cursor.close();
                database4.close();
                break;
        }
    }
}
