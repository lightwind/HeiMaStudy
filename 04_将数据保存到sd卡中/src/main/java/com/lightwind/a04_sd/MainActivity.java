package com.lightwind.a04_sd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 读取存储的信息
        Map<String, String> maps = UserInfoUtils.readInfo();
        if (maps != null) {
            String name = maps.get("NAME");
            String password = maps.get("PASSWORD");

            mEtUsername.setText(name);
            mEtPassword.setText(password);
        }
        mBtnLogin.setOnClickListener(this);
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
                // 将用户名的密码和数据存储起来
                boolean saveInfo = UserInfoUtils.saveInfo(name, password);
                if (saveInfo) {
                    Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "保存失败！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
