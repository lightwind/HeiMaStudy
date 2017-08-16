package com.lightwind.a05_sdk;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_total)
    TextView mTvTotal;
    @BindView(R.id.tv_free)
    TextView mTvFree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 获取SD卡总大小和可用空间
        File file = Environment.getExternalStorageDirectory();
        // 总大小
        long totalSpace = file.getTotalSpace();
        // 可用空间
        long fileUsableSpace = file.getUsableSpace();

        // 转换数据格式
        String total = Formatter.formatFileSize(this, totalSpace);
        String free = Formatter.formatFileSize(this, fileUsableSpace);

        mTvTotal.setText("总大小：" + total);
        mTvFree.setText("可用空间：" + free);

    }
}
