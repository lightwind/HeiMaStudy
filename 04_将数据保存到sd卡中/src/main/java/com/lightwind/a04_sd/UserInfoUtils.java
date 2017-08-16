package com.lightwind.a04_sd;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 储存用户信息的方法
 * Created by lightwind on 2017/8/12.
 */
class UserInfoUtils {

    /*
     * 保存用户信息的方法
     */
    static boolean saveInfo(String name, String pwd) {
        try {
            String result = name + "#" + pwd;
            // 创建一个File类，指定数据存储的位置-SD卡
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path, "info.txt");

            // 创建一个文件输出流
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(result.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * 读取用户信息的方法
     */
    static Map<String, String> readInfo() {
        try {
            // 定义Map
            Map<String, String> maps = new HashMap<>();
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path, "info.txt");
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String content = bufferedReader.readLine();// 读取一行

            // 切割字符串，封装到maps中
            String[] split = content.split("#");
            String name = split[0];
            String password = split[1];

            maps.put("NAME", name);
            maps.put("PASSWORD", password);

            return maps;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
