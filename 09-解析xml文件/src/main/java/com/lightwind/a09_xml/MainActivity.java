package com.lightwind.a09_xml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_weather)
    TextView mTvWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        try {
            // 获取资产的管理者，通过上下文获取
            InputStream inputStream = getAssets().open("weather.xml");
            // 调用解析方法
            List<Channel> channelList = WeatherParser.parserXml(inputStream);

            StringBuffer sb = new StringBuffer();
            for (Channel channel : channelList) {
                sb.append(channel.toString());
            }

            // 把数据展示
            mTvWeather.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
