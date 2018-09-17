package com.ljb.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:31:36
 */
public interface BaseDao<T> {
	
    List<Map<String, Object>> selectByPage(Map<String, Object> map);

    int selectTotal(Map<String, Object> map);
    
    Map<String, Object> select(Serializable id);
}
