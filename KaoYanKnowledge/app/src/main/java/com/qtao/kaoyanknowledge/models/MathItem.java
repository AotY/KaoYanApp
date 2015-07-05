package com.qtao.kaoyanknowledge.models;

/**
 * Created by AotY on 2015/7/3.
 */
public class MathItem {

    /**
     * 类型
     */
    public int type ;

    /**
     * 名称
     */
    private String name ;

    /**
     * 包含内容
     */
    private String content ;

    /**
     * 图片
     */
    private int img ;

    public MathItem(){

    }


    public MathItem(String content, String name) {
        this.content = content;
        this.name = name;
    }

    public MathItem(int type, String name, String content, int img) {
        this.type = type;
        this.name = name;
        this.content = content;
        this.img = img;
    }

    public MathItem(String name, String content, int img) {
        this.name = name;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
