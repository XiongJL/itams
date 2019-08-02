package com.liwinon.itams.entity.primay;
// 抽象接口 , 用于设置下拉菜单的值
//实体类继承此接口即可.
public interface Select {
    void setId(int id);
    int getId();
    String getValue();
    void setValue(String value);

}
