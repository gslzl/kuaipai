package com.example.myproject.bean;

import java.io.Serializable;
import java.util.List;

public class BannerBean implements Serializable {

    public String code;
    private List<bannerBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<bannerBean> getData() {
        return data;
    }

    public void setData(List<bannerBean> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String message;
    public static class bannerBean{
        public String ID;
        public String name;
        public String user_ID;
        public String picture;
        public String information;
        public String star_price;
        public String current_price;
        public String time;
        public String state;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUser_ID() {
            return user_ID;
        }

        public void setUser_ID(String user_ID) {
            this.user_ID = user_ID;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public String getStar_price() {
            return star_price;
        }

        public void setStar_price(String star_price) {
            this.star_price = star_price;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

}
