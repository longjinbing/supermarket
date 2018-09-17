package com.ljb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名称：TreeUtils <br>
 * 描述：<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class TreeUtils {
	
	private static Logger logger=LoggerFactory.getLogger(TreeUtils.class);
    /**
     * 包装成树形结构 (全部属性)
     * 必须要有id parentId children
     *
     * @param tree
     * @return
     * @throws Exception
     */
    public static Collection<Object> buildTree(List _list) {
        try {
            if (_list != null) {
            	List tree=new ArrayList<>();
            	for (Object object : _list) {
					ZTree zTree=new ZTree();
					BeanUtils.copyProperties(object, zTree);
					tree.add(zTree);
				}
                List t_list = new ArrayList();
                Map map = new HashMap();
                for (Object o : tree) {
                    Class clazz = o.getClass();
                    Field id = clazz.getDeclaredField("id");
                    if (!id.isAccessible()) {
                        id.setAccessible(true);
                    }
                    Integer lId = (Integer) id.get(o);
                    map.put(lId, o);
                }
                for (Object o : map.keySet()) {
                    Integer cId = (Integer) o;
                    Object obj = map.get(cId);
                    Class clazz = obj.getClass();
                    Field pId = clazz.getDeclaredField("parentId");
                    if (!pId.isAccessible()) {
                        pId.setAccessible(true);
                    }
                    Integer id = (Integer) pId.get(obj);
                    if (null == map.get(id)) {
                    	((ZTree)obj).setOpen(true);
                        t_list.add(obj);
                    } else {
                        Object object = map.get(id);
                        Class clazz1 = object.getClass();
                        Field children = null;
                        children = clazz1.getDeclaredField("children");
                        if (!children.isAccessible()) {
                            children.setAccessible(true);
                        }
                        List list = (List) children.get(object);
                        if (CollectionUtils.isEmpty(list)) {
                            list = new ArrayList();
                        }
                        list.add(obj);
                        children.set(object, list);
                    }
                }
                return t_list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
