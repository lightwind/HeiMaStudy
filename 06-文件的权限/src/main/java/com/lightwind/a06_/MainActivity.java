package com.lightwind.a06_;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.FileOutputStream;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_private, R.id.btn_append, R.id.btn_read, R.id.btn_write})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 生成一个私有的文件
            case R.id.btn_private:
                try {
                    FileOutputStream outputStream = openFileOutput("private.txt", MODE_PRIVATE);
                    outputStream.write("private".getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            // 生成一个可追加的文件
            case R.id.btn_append:
                try {
                    FileOutputStream outputStream = openFileOutput("append.txt", MODE_APPEND);
                    outputStream.write("append".getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            // 生成一个可读的文件
            case R.id.btn_read:
                try {
                    FileOutputStream outputStream = openFileOutput("read.txt", MODE_WORLD_READABLE);
                    outputStream.write("read".getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            // 生成一个可写的文件
            case R.id.btn_write:
                try {
                    FileOutputStream outputStream = openFileOutput("write.txt",
                            MODE_WORLD_WRITEABLE);
                    outputStream.write("write".getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
