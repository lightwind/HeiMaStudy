package com.lightwind.a09_xml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于解析xml的类
 * Created by lightwind on 2017/8/14.
 */

public class WeatherParser {

    /**
     * 服务器是以流的形式将数据返回的
     */
    public static List<Channel> parserXml(InputStream inputStream) throws Exception {
        // 声明集合对象
        List<Channel> weatherList = null;
        Channel channel = null;
        // 获取XmlPullParser解析的实例
        XmlPullParser parser = Xml.newPullParser();
        // 设置XmlPullParser的参数
        parser.setInput(inputStream, "utf-8");

        // 获取文档的事件类型
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                // 解析开始标签
                case XmlPullParser.START_TAG:
                    // 具体判断解析到了哪个开始节点
                    if ("weather".equals(parser.getName())) {
                        // 创建一个集合对象
                        weatherList = new ArrayList<>();
                    } else if ("channel".equals(parser.getName())) {
                        channel = new Channel();
                        // 获取id值
                        String id = parser.getAttributeValue(0);
                        channel.setId(id);
                    } else if ("city".equals(parser.getName())) {
                        // 获取city的数据
                        String city = parser.nextText();
                        channel.setCity(city);
                    } else if ("temp".equals(parser.getName())) {
                        // 获取city的数据
                        String temp = parser.nextText();
                        channel.setTemp(temp);
                    } else if ("wind".equals(parser.getName())) {
                        // 获取city的数据
                        String wind = parser.nextText();
                        channel.setWind(wind);
                    } else if ("pm250".equals(parser.getName())) {
                        // 获取city的数据
                        String pm250 = parser.nextText();
                        channel.setPm250(pm250);
                    }
                    break;
                // 解析结束标签
                case XmlPullParser.END_TAG:
                    // 判断要解析的结束标签
                    if ("channel".equals(parser.getName())) {
                        // 将bean数据存到集合中
                        weatherList.add(channel);
                    }
                    break;
            }
            // 不停的向下解析
            type = parser.next();
        }

        return weatherList;
    }
}
