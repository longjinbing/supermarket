package com.ljb.repository;

import com.ljb.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/15<br>
 * 描述: <br>
 */
public interface TableRepository{

    String quertDatabaseName();

    List<Map<String, Object>> findAll(Map<String,Object> map);

    Long queryTotal(Map<String,Object> map);

    Map<String, Object> queryTable(String tableName);

    List<Map<String, Object>> queryColumns(String tableName);

}
