package com.ljb.serviceImpl;

import com.ljb.entity.SysUserRole;
import com.ljb.repository.SysUserRoleRepository;
import com.ljb.service.SysUserRoleService;
import com.ljb.shiro.ShiroUtils;
import com.ljb.util.DateUtils;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户与角色Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Override
    public int save(Integer userId,Integer[] roleIds,Integer adminId) {
        sysUserRoleRepository.deleteByRoleId(userId);
        List<SysUserRole> list=new ArrayList<>();
        for (Integer roleId : roleIds) {
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            sysUserRole.setCreateId(adminId);
            sysUserRole.setCreateTime(DateUtils.currentTime());
            list.add(sysUserRole);
        }
        return sysUserRoleRepository.saveAll(list).size()>0?1:0;
    }

    @Override
    public SysUserRole queryObject(Integer id) {
        return sysUserRoleRepository.findOne(id);
    }

    @Override
    public Page<SysUserRole> queryList(Map<String, Object> map) {
       Query query = new Query<SysUserRole>(map);
       return sysUserRoleRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<SysUserRole> queryAll(Map<String, Object> map){
    	Query query = new Query<SysUserRole>(map);
       return sysUserRoleRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysUserRole>(map);
       return sysUserRoleRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysUserRole save(SysUserRole entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return sysUserRoleRepository.save(entity);
	}

    @Override
    public SysUserRole update(SysUserRole entity) {
        return sysUserRoleRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	sysUserRoleRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
