package com.lightwind.a08_xml;

/**
 * 短信实体类
 * Created by lightwind on 2017/8/14.
 */

public class Sms {

    private String address;
    private String body;
    private String date;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
