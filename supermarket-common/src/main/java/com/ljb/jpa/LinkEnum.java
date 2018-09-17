package com.ljb.jpa;

import org.springframework.util.StringUtils;

public enum LinkEnum {
    /**
     * 等于
     */
    EQ("="),
    /**
     * 大于等于
     */
    GE(">="),
    /**
     * 大于
     */
    GT(">"),
    /**
     * in
     */
    IN("IN"),
    /**
     * 需要手动在 value中添加 %
     */
    LIKE("LIKE"),
    /**
     * 小于等于
     */
    LE("<="),
    /**
     * 小于
     */
    LT("<"),
    /**
     * 不等于
     */
    NE("<>"),
    /**
     * not in
     */
    NIN("NOT IN");
    /**
     * 为空 is null
     */


    private String name;

    LinkEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toCondition(String key,String index){
        return key+" "+this.name+" :"+ (StringUtils.replace(key, ".", "_")+index);
    }

}
