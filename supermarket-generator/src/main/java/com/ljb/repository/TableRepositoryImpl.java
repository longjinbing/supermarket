package com.ljb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/15<br>
 * 描述: <br>
 */
@Repository
public class TableRepositoryImpl implements TableRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    @Override
    public String quertDatabaseName() {
        String sql="select database() as database";
        Map<String,Object> result=jdbcOperations.queryForMap(sql,new HashMap<>());
        return result.get("database").toString();
    }

    @Override
    public List<Map<String, Object>> findAll(Map<String,Object> map) {
        String sql="select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database())";
        if(map.containsKey("tableName")){
            sql+="and table_name like concat('%', :tableName, '%')";
        }
        sql+="limit :offset,:limit";
        List<Map<String,Object>> result=jdbcOperations.queryForList(sql, map);
        return result;
    }

    @Override
    public Long queryTotal(Map<String,Object> map) {
        String sql="select count(*) as number from information_schema.tables where table_schema = (select database())";
        if(map.containsKey("tableName")){
            sql+="and table_name like concat('%', :tableName, '%')";
        }
        Map<String,Object> result=jdbcOperations.queryForMap(sql, map);
        return (Long) result.get("number");
    }

    @Override
    public Map<String, Object> queryTable(String tableName) {
        String sql="select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) and table_name = :tableName";
        Map<String,Object> map=new HashMap<>();
        map.put("tableName", tableName);
        Map<String,Object> result=jdbcOperations.queryForMap(sql, map);
        return result;
    }

    @Override
    public List<Map<String, Object>> queryColumns(String tableName) {
        String sql="select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_name = :tableName and table_schema = (select database()) order by ordinal_position";
        Map<String,Object> map=new HashMap<>();
        map.put("tableName", tableName);
        List<Map<String,Object>> result=jdbcOperations.queryForList(sql, map);
        return result;
    }

}
