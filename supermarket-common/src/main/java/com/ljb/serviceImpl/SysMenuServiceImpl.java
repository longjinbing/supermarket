package com.ljb.serviceImpl;

import com.ljb.annotation.DescBean;
import com.ljb.entity.SysMenu;
import com.ljb.repository.SysMenuRepository;
import com.ljb.service.SysMenuService;
import com.ljb.shiro.ShiroUtils;
import com.ljb.util.Constant;
import com.ljb.util.Query;
import com.ljb.util.TreeUtils;
import com.ljb.util.ZTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.*;

/**
 * 菜单管理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Autowired
    private DescBean descBean;

    @Override
    public Collection<Object> menuList(Integer adminId){
        return TreeUtils.buildTree(getSysMenuList(adminId));
    }

    @Override
    public Collection<Object> zTreeList(Integer adminId){
        List<SysMenu> menus=getAllSysMenu(adminId);
        SysMenu sysMenu=new SysMenu();
        sysMenu.setId(0);
        sysMenu.setName("主菜单");
        sysMenu.setParentId(-1);
        sysMenu.setType(-1);
        menus.add(sysMenu);
        return TreeUtils.buildTree(menus);
    }

    @Override
    public Collection<Object> menuSelectList(Integer adminId){
        return TreeUtils.buildTree(getAllSysMenu(adminId));
    }

    public List<SysMenu> getSysMenuList(Integer adminId){
        List<SysMenu> sysMenus;
        if(adminId==Constant.SUPER_ADMIN) {
            sysMenus=sysMenuRepository.findAllByTypeInAndStatus(Arrays.asList(new Integer[]{0,1}),1);
        }else {
            sysMenus=sysMenuRepository.findAllAndStatusByUserId(1,adminId);
        }
        return sysMenus;
    }

    public List<SysMenu> getAllSysMenu(Integer adminId){
        List<SysMenu> sysMenus;
        if(adminId==Constant.SUPER_ADMIN) {
            sysMenus=sysMenuRepository.findAllByTypeIn(Arrays.asList(new Integer[]{0,1}));
        }else {
            sysMenus=sysMenuRepository.findAllByUserId(adminId);
        }
        return sysMenus;
    }



    @Override
    public Boolean refershMenu() {
        sysMenuRepository.deleteAll();
        List<ZTree> ztreeList=descBean.getZtree();
        for (ZTree root : ztreeList) {
            int rootNum=1;
            List<ZTree>	menuList=root.getChildren();
            SysMenu r=new SysMenu();
            r.setName(root.getName());
            r.setIcon(root.getIcon());
            r.setOrderNum(rootNum);
            r.setType(0);
            r.setParentId(0);
            r.setStatus(1);
            sysMenuRepository.save(r);
            rootNum++;
            int menuNum=1;
            if(menuList!=null) {
                for (ZTree menu : menuList) {
                    SysMenu m=new SysMenu();
                    m.setName(menu.getName());
                    m.setIcon(menu.getIcon());
                    m.setOrderNum(menuNum);
                    m.setType(1);
                    m.setParentId(r.getId());
                    m.setUrl(menu.getUrl());
                    m.setStatus(menu.getChecked()?1:0);
                    sysMenuRepository.save(m);
                    menuNum++;
                    List<ZTree>	buttonList=menu.getChildren();
                    int buttonNum=1;
                    if(buttonList!=null) {
                        for (ZTree button : buttonList) {
                            SysMenu b=new SysMenu();
                            b.setName(button.getName());
                            b.setIcon(button.getIcon());
                            b.setOrderNum(buttonNum);
                            b.setType(2);
                            b.setParentId(m.getId());
                            b.setPerms(button.getUrl());
                            b.setStatus(button.getChecked()?1:0);
                            sysMenuRepository.save(b);
                            buttonNum++;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    @Override
    public SysMenu queryObject(Integer id) {
        return sysMenuRepository.findOne(id);
    }

    @Override
    public Page<SysMenu> queryList(Map<String, Object> map) {
       Query query = new Query<SysMenu>(map);
        Specification<SysMenu> specification=new Specification<SysMenu>() {
            @Override
            public Predicate toPredicate(Root<SysMenu> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Integer> name=root.get("parentId");
                criteriaQuery.where(criteriaBuilder.equal(name, map.get("id")));
                return null;
            }
        };
       return sysMenuRepository.findAll(query.getSpecification().and(specification), query.getPageable());
    }
    
     @Override
    public List<SysMenu> queryAll(Map<String, Object> map){
    	Query query = new Query<SysMenu>(map);
       return sysMenuRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysMenu>(map);
       return sysMenuRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysMenu save(SysMenu entity) {
		return sysMenuRepository.save(entity);
	}

    @Override
    public SysMenu update(SysMenu entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return sysMenuRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	sysMenuRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
