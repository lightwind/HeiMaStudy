package com.lightwind.a12_transition;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Transition：事物
 * 点击按钮张三给李四转账500块钱
 */
public class MainActivity extends AppCompatActivity {

    private MyOpenHelper mMyOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMyOpenHelper = new MyOpenHelper(this);

    }

    /**
     * 点击按钮进行转账的逻辑
     */
    @OnClick(R.id.btn)
    public void onViewClicked() {
        SQLiteDatabase database = mMyOpenHelper.getWritableDatabase();
        // 使用事物进行转账
        // 开启事物
        database.beginTransaction();
        try {
            // 实现转账的逻辑
            database.execSQL("update info set money = money - 100 where name = ?", new
                    Object[]{"张三"});
            database.execSQL("update info set money = money + 100 where name = ?", new
                    Object[]{"李四"});
            // 给当前事物设置成功的标记
            database.setTransactionSuccessful();
        } catch (Exception e) {
            // 如果转账中间有错误，给出友好的提示
            Toast.makeText(this, "服务器忙，请稍后再转！", Toast.LENGTH_SHORT).show();
        } finally {
            // 关闭事物
            database.endTransaction();
        }
    }
}
