package com.lightwind.a08_xml;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private List<Sms> mSmsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 初始化要备份的数据
        mSmsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Sms sms = new Sms();
            sms.setAddress("100086" + i);
            sms.setBody("sms" + i);
            sms.setDate("201" + i);
            // 把sms对象加入到集合中
            mSmsList.add(sms);
        }
    }

    /**
     * 点击按钮通过StringBuffer的方式生成一个xml文件
     */
    @OnClick(R.id.btn_create_xml)
    public void onViewClicked() {

        // 创建StringBuffer对象
        StringBuffer stringBuffer = new StringBuffer();
        // 开始组拼xml文件头
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        // 开始组拼xml根节点
        stringBuffer.append("<smss>");
        // 开始组拼sms节点
        for (Sms sms : mSmsList) {
            stringBuffer.append("<sms>");

            // 组拼address节点
            stringBuffer.append("<address>");
            stringBuffer.append(sms.getAddress());
            stringBuffer.append("</address>");

            // 组拼body节点
            stringBuffer.append("<body>");
            stringBuffer.append(sms.getBody());
            stringBuffer.append("</body>");

            // 组拼date节点
            stringBuffer.append("<date>");
            stringBuffer.append(sms.getDate());
            stringBuffer.append("</date>");

            stringBuffer.append("</sms>");
        }

        stringBuffer.append("</smss>");
        try {
            // 将数据保存到sd卡中
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "sms.xml");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(stringBuffer.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击按钮通过XmlSerializer方式生成一个xml文件
     */
    @OnClick(R.id.btn_create_xml2)
    public void onClicked() {
        try {
            // 获取XmlSerializer类的实例，通过xml这个工具类去获取
            XmlSerializer serializer = Xml.newSerializer();
            // 设置XmlSerializer序列化器的参数
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "sms2.xml");
            FileOutputStream outputStream;
            outputStream = new FileOutputStream(file);
            serializer.setOutput(outputStream, "utf-8");
            // 开始写xml文档的开头
            serializer.startDocument("utf-8", true);

            // 写xml根节点 namespace 命名空间
            serializer.startTag(null, "smss");

            for (Sms sms : mSmsList) {
                serializer.startTag(null, "sms");

                serializer.startTag(null, "address");
                serializer.text(sms.getAddress());
                serializer.endTag(null, "address");

                serializer.startTag(null, "body");
                serializer.text(sms.getBody());
                serializer.endTag(null, "body");

                serializer.startTag(null, "date");
                serializer.text(sms.getDate());
                serializer.endTag(null, "date");

                serializer.endTag(null, "sms");
            }

            serializer.endTag(null, "smss");
            // 写文档的结尾
            serializer.endDocument();
            outputStream.close();
            Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
