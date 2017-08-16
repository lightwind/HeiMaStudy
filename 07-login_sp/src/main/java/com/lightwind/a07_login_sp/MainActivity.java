package com.lightwind.a07_login_sp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.cb_main)
    CheckBox mCbMain;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBtnLogin.setOnClickListener(this);

        // 使用SharedPreferences保存数据
        /*
         * name 会帮助我们生成一个xml文件
         */
        mPreferences = getSharedPreferences("config", MODE_PRIVATE);

        // 在config.xml文件中取出数据，并展示
        String name = mPreferences.getString("name", "");
        String password = mPreferences.getString("password", "");

        mEtUsername.setText(name);
        mEtPassword.setText(password);

    }

    @Override
    public void onClick(View v) {

        String name = mEtUsername.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();

        // 判断用户名和密码是否为空
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 登陆
            //TODO 连接网络进行登陆
            Toast.makeText(this, "登陆！", Toast.LENGTH_SHORT).show();
            if (mCbMain.isChecked()) {
                // 获取sp编辑器
                SharedPreferences.Editor edit = mPreferences.edit();
                // 存储数据
                edit.putString("name", name);
                edit.putString("password", password);
                // 提交
                edit.commit();
            }
        }
    }
}
