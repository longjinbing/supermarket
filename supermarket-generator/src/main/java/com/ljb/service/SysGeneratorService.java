package com.ljb.service;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年12月19日 下午3:33:38
 */
public interface SysGeneratorService {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    Long queryTotal(Map<String, Object> map);

    Map<String, Object> queryTable(String tableName);

    List<Map<String, Object>> queryColumns(String tableName);

    /**
     * 生成代码
     */
    byte[] generatorCode(Map<String, Object> map);
}
