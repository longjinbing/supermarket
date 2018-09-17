package com.ljb.serviceImpl;

import com.ljb.entity.SysRole;
import com.ljb.repository.SysRoleRepository;
import com.ljb.service.SysRoleService;
import com.ljb.shiro.ShiroUtils;
import com.ljb.util.Constant;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;
    
    @Override
    public SysRole queryObject(Integer id) {
        return sysRoleRepository.findOne(id);
    }

    @Override
    public List<Map<String, Object>> roleListByuserIdAndAdminId(Integer userId,Integer adminId){
        //登录账户所拥有的角色
        List<SysRole> allRoles;
        //判断是否是超级管理员
        if(adminId== Constant.SUPER_ADMIN) {
            allRoles=sysRoleRepository.findAll();
        }else {
            allRoles=sysRoleRepository.roleByUserId(adminId);
        }
        //将赋予角色用户拥有的角色
        List<SysRole> hasRoles;
        if(userId==Constant.SUPER_ADMIN) {
            hasRoles=sysRoleRepository.findAll();;
        }else {
            hasRoles=sysRoleRepository.roleByUserId(adminId);
        }
        Map<Integer, Object> ids=new HashMap<>();
        for (SysRole role : hasRoles) {
            ids.put(role.getId(), "");
        }
        List<Map<String, Object>> result=new ArrayList<>();
        for (SysRole role : allRoles) {
            Map<String, Object> map=new HashMap<>();
            map.put("id", role.getId());
            map.put("name", role.getName());
            if(ids.containsKey(role.getId())) {
                map.put("checked", true);
            }else {
                map.put("checked", false);
            }
            result.add(map);
        }
        return result;
    }


    @Override
    public Page<SysRole> queryList(Map<String, Object> map) {
       Query query = new Query<SysRole>(map);
       return sysRoleRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<SysRole> queryAll(Map<String, Object> map){
    	Query query = new Query<SysRole>(map);
       return sysRoleRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysRole>(map);
       return sysRoleRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysRole save(SysRole entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return sysRoleRepository.save(entity);
	}

    @Override
    public SysRole update(SysRole entity) {
        return sysRoleRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	sysRoleRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
