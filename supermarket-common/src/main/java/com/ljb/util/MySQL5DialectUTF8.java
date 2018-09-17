package com.ljb.util;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/8/30<br>
 * 描述: <br>
 */
public class MySQL5DialectUTF8 extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
