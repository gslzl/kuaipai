package com.example.myproject.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
    public String code;
    public DataBean data;
    public String message;

    public static class DataBean {
        public String avatar;
        public String name;
        public String password;
        public String phone_number;
    }
}
