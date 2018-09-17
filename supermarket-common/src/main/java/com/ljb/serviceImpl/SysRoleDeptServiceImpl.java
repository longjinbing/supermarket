package com.ljb.serviceImpl;

import com.ljb.entity.SysRoleDept;
import com.ljb.repository.SysRoleDeptRepository;
import com.ljb.service.SysRoleDeptService;
import com.ljb.shiro.ShiroUtils;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色与部门对应关系Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
    @Autowired
    private SysRoleDeptRepository sysRoleDeptRepository;
    
    @Override
    public SysRoleDept queryObject(Integer id) {
        return sysRoleDeptRepository.findOne(id);
    }

    @Override
    public Page<SysRoleDept> queryList(Map<String, Object> map) {
       Query query = new Query<SysRoleDept>(map);
       return sysRoleDeptRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<SysRoleDept> queryAll(Map<String, Object> map){
    	Query query = new Query<SysRoleDept>(map);
       return sysRoleDeptRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysRoleDept>(map);
       return sysRoleDeptRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysRoleDept save(SysRoleDept entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return sysRoleDeptRepository.save(entity);
	}

    @Override
    public SysRoleDept update(SysRoleDept entity) {
        return sysRoleDeptRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	sysRoleDeptRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
