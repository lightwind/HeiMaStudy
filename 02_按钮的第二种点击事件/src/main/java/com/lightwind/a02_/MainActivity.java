package com.lightwind.a02_;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_main)
    EditText mEtMain;
    @BindView(R.id.btn_main)
    Button mBtnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mBtnMain.setOnClickListener(new MyClickListener());

        mBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String number = mEtMain.getText().toString().trim();

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            /**
             * uri:统一资源标识符，自己定义的路径，想代表什么就代表什么
             * url:统一资源定位符
             */
            intent.setData(Uri.parse("tel:" + number));

            startActivity(intent);
        }
    }
}
