package com.ljb.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.ljb.repository.TableRepository;
import com.ljb.service.SysGeneratorService;
import com.ljb.util.GenUtils;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysGeneratorServiceImpl implements SysGeneratorService {
	
	@Autowired
    private TableRepository tableRepository;

    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        return tableRepository.findAll(map);
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        return tableRepository.queryTotal(map);
    }

    @Override
    public Map<String, Object> queryTable(String tableName) {
         return tableRepository.queryTable(tableName);
    }

    @Override
    public List<Map<String, Object>> queryColumns(String tableName) {
        /*if ("ORACLE".equals(Constant.USE_DATA)) {
            List<ResultMap> list = sysOracleGeneratorDao.queryColumns(tableName);

            //oracle
            List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
            for (ResultMap stringObjectMap : list) {
                // 获取实体类的所有属性，返回Field数组
                Field[] field = stringObjectMap.getClass().getDeclaredFields();

                Map<String, String> objectMap = new HashMap<String, String>();
                for (int j = 0; j < field.length; j++) {
                    // 获取属性的名字
                    String name = field[j].getName();
                    // 将属性的首字符大写，方便构造get，set方法
                    String Name = name.substring(0, 1).toUpperCase() + name.substring(1);

                    try {
                        Method m = stringObjectMap.getClass().getMethod("get" + Name);
                        // 调用getter方法获取属性值
                        String value = (String) m.invoke(stringObjectMap);
                        objectMap.put(name, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mapList.add(objectMap);
            }
            return mapList;
        }*/
        return tableRepository.queryColumns(tableName);
    }

    @Override
    public byte[] generatorCode(Map<String, Object> map) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        String[] tableNames = new String[]{};
        tableNames = JSON.parseArray((String) map.get("tables")).toArray(tableNames);
        for (String tableName : tableNames) {
            //查询表信息
            Map<String, Object> table = queryTable(tableName);
            //查询列信息
            List<Map<String, Object>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip,map);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

}
