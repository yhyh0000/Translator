package com.yinggg.translator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsode() {
        return smsode;
    }

    public void setSmsode(String smsode) {
        this.smsode = smsode;
    }

    public String getImgcode() {
        return imgcode;
    }

    public void setImgcode(String imgcode) {
        this.imgcode = imgcode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private  String username;
    private  String password;
    //短信验证码
    private  String smsode;
    //图片验证码
    private  String imgcode;

    private  String uuid;
}
