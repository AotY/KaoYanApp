package com.qtao.kaoyanknowledge.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AotY on 2015/7/7.
 */
public class MajorItem implements Serializable {

    /**
     * 专业编号
     */
    private String number;

    /**
     * 专业名称
     */
    private String name;

//    /**
//     * 该专业的类别名称 ； eg: 计算机 属于 工科
//     */
//    private String className;

    /**
     * 如果是类别，该类别下面还有多少专业
     */
    private List<MajorItem> classChildren;


    public MajorItem() {
    }

    public MajorItem(String number, String name) {
        this.number = number;
        this.name = name;
    }
    public MajorItem(String number, String name, List<MajorItem> classChildren) {
        this.number = number;
        this.name = name;
        this.classChildren = classChildren;
    }

    public void addChild(MajorItem item) {
        if (classChildren == null) {
            classChildren = new ArrayList<>();
        }
        classChildren.add(item);
    }


    public List<MajorItem> getClassChildren() {
        return classChildren;
    }

    public void setClassChildren(List<MajorItem> classChildren) {
        this.classChildren = classChildren;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 判断两个专业是否相等
     * @param item
     * @return
     */
    public boolean isMajorEqual(MajorItem item){
        if(this.number.equals(item.getNumber()) && this.name.equals(item.getName())){
            return true;
        }
        return false;
    }

}
