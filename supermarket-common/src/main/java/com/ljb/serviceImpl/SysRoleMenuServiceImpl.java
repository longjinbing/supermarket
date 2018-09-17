package com.ljb.serviceImpl;

import com.ljb.entity.SysMenu;
import com.ljb.entity.SysRoleMenu;
import com.ljb.repository.SysMenuRepository;
import com.ljb.repository.SysRoleMenuRepository;
import com.ljb.service.SysRoleMenuService;
import com.ljb.shiro.ShiroUtils;
import com.ljb.util.*;
import com.ljb.viewmodel.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色与菜单Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Override
    public Collection<Object> menuList(Integer roleId, Integer adminId){
        List<MenuModel> result = new ArrayList<>();
        List<SysMenu> allMenu=new ArrayList<>();
        if(adminId== Constant.SUPER_ADMIN) {
            allMenu=sysMenuRepository.findAll();
        }else {
            allMenu=sysMenuRepository.findAllByUserId(adminId);
        }
        List<SysMenu> hasMenu=sysMenuRepository.findAllByRoleId(roleId);
        Map<Integer,Object> hasMap=hasMenu.stream().collect(Collectors.toMap(SysMenu::getId, a->a,(k1, k2)->k1));
        List<ZTree> list=new ArrayList<>();
        for (SysMenu menu : allMenu) {
            ZTree zTree=new ZTree();
            BeanUtils.copyProperties(menu, zTree);
            if(hasMap.containsKey(menu.getId()))
                zTree.setChecked(true);
            list.add(zTree);
        }
        return TreeUtils.buildTree(list);
    }

    @Override
    public int save(Integer roleId,Integer[] menuIds,Integer adminId) {
        sysRoleMenuRepository.deleteByRoleId(roleId);
        List<SysRoleMenu> list=new ArrayList<>();
        for (Integer menuId : menuIds) {
            SysRoleMenu sysRoleMenu=new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setCreateId(adminId);
            sysRoleMenu.setCreateTime(DateUtils.currentTime());
            list.add(sysRoleMenu);
        }
        return sysRoleMenuRepository.saveAll(list).size()>0?1:0;
    }

    @Override
    public SysRoleMenu queryObject(Integer id) {
        return sysRoleMenuRepository.findOne(id);
    }

    @Override
    public Page<SysRoleMenu> queryList(Map<String, Object> map) {
       Query query = new Query<SysRoleMenu>(map);
       return sysRoleMenuRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<SysRoleMenu> queryAll(Map<String, Object> map){
    	Query query = new Query<SysRoleMenu>(map);
       return sysRoleMenuRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysRoleMenu>(map);
       return sysRoleMenuRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysRoleMenu save(SysRoleMenu entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return sysRoleMenuRepository.save(entity);
	}

    @Override
    public SysRoleMenu update(SysRoleMenu entity) {
        return sysRoleMenuRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	sysRoleMenuRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
