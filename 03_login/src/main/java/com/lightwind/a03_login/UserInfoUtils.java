package com.lightwind.a03_login;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 储存用户信息的方法
 * Created by lightwind on 2017/8/10.
 */
class UserInfoUtils {

    /*
     * 保存用户信息的方法
     */
    static boolean saveInfo(Context context, String name, String pwd) {
        try {

            // 通过上下文获取文件保存的路径
//            String path = context.getFilesDir().getPath();

            String result = name + "#" + pwd;
            // 创建一个File类，指定数据存储的位置
//            File file = new File("/data/data/com.lightwind.a03_login/info.txt");
//            File file = new File(path, "info.txt");

            // 创建一个文件输出流
//            FileOutputStream outputStream = new FileOutputStream(file);
            // 通过上下问获取FileOutputStream
            FileOutputStream outputStream = context.openFileOutput("info2.txt", Context
                    .MODE_PRIVATE);

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
    static Map<String, String> readInfo(Context context) {
        try {
            // 定义Map
            Map<String, String> maps = new HashMap<>();
//            File file = new File("/data/data/com.lightwind.a03_login/info.txt");
//            FileInputStream inputStream = new FileInputStream(file);
            FileInputStream inputStream = context.openFileInput("info2.txt");
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
