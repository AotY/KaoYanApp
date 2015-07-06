package com.qtao.kaoyanknowledge.models;

import java.io.Serializable;

/**
 * 英语 和 政治 Bean
 */
public class EngAndPolItem implements Serializable {

    /**
     * 类型 ， 如英语一 ，英语二， 英语三， 常考英语单词， 常考试题
     */
    public int type;

    /**
     * 名称
     */
    private String name;

    /**
     * 简要描述
     */
    private String berif;

    /**
     * 图片
     */
    private int img;


    public EngAndPolItem() {
    }

    public EngAndPolItem(int type, String name, String berif) {
        this.berif = berif;
        this.name = name;
        this.type = type;
    }

    public EngAndPolItem(int type, String name, String berif, int img) {
        this.type = type;
        this.name = name;
        this.berif = berif;
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBerif() {
        return berif;
    }

    public void setBerif(String berif) {
        this.berif = berif;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
