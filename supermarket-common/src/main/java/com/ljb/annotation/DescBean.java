package com.ljb.annotation;

import java.util.List;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import com.ljb.util.StringUtils;
import com.ljb.util.ZTree;

/**
 * @author Lvbey
 */
@Component("descBean") // 交给spring管理方便其他类获取该类对象
public class DescBean implements ApplicationContextAware {

	private static Logger logger = LoggerFactory.getLogger(DescBean.class);

	private ApplicationContext applicationContext;

	private String basePackages;

	private Map<String,ZTree> groups;

	private List<ZTree> ztreeList;

	/**
	 * 通过依赖注入获取配置文件中的属性值
	 * 
	 *
	 */
	public void setBasePackages() {
		this.basePackages = "com.ljb.controller";
		groups=new HashMap<>();
		ztreeList = new ArrayList<>();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void init() {
		setBasePackages();
		if (this.applicationContext == null) {
			return;
		}
		Map<String, Object> descMap = this.applicationContext.getBeansWithAnnotation(Desc.class);
		logger.info(descMap.toString());
		Class<? extends Object> clazz = null;
		ZTree root = new ZTree();
		for (Map.Entry<String, Object> entry : descMap.entrySet()) {
			clazz = entry.getValue().getClass();
			logger.warn(clazz.getName());
			String className="";
			if(clazz.getName().indexOf('$')>-1) {
				className = (clazz.getName()).substring(0, clazz.getName().indexOf('$'));
			}else{
				className = clazz.getName();
			}
			try {
				clazz = Class.forName(className);
				Desc desc1 = (Desc) clazz.getAnnotation(Desc.class);
				RequiresPermissions permission1 = (RequiresPermissions) clazz.getAnnotation(RequiresPermissions.class);
				logger.info("获取到权限类");
				logger.info(String.valueOf(groups.containsKey(desc1.group())));
				if (!groups.containsKey(desc1.group())) {
					logger.info("添加根菜单");
					if (root.getName()!=null) {
						ztreeList.add(root);
					}
					root = getZTree(desc1, permission1);
					root.setName(desc1.group());
					root.setUrl("#");
					groups.put(desc1.group(), null);
				}
				logger.info("添加菜单");
				ZTree menu = getZTree(desc1, permission1);
				Method[] methods = clazz.getMethods();
				logger.info("添加按钮");
				for (Method c : methods) {
					logger.info("循环方法:" + c.getName());
					Desc desc2 = (Desc) c.getAnnotation(Desc.class);
					RequiresPermissions permission2 = (RequiresPermissions) c.getAnnotation(RequiresPermissions.class);
					if (desc2 != null) {
						ZTree button = getZTree(desc2, permission2);
						menu.addChild(button);
					}
				}
				root.addChild(menu);
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		ztreeList.add(root);
	}

	public List<ZTree> getZtree() {
		if (this.ztreeList == null) {
			init();
			logger.info(String.valueOf(ztreeList.get(0).getChildren().size()));
		}
		return this.ztreeList;
	}

	public ZTree getZTree(Desc desc, RequiresPermissions permission) {
		ZTree zTree = new ZTree();

		zTree.setName(desc.name());
		zTree.setIcon(desc.icon());
		zTree.setChecked(desc.status()==1);
		zTree.setUrl(permission == null ? desc.url() :StringUtils.parseString(permission.value()));
		return zTree;
	}
}
