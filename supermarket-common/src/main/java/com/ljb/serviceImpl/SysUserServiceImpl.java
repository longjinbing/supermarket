package com.ljb.serviceImpl;

import com.ljb.entity.SysUser;
import com.ljb.repository.SysUserRepository;
import com.ljb.service.SysUserService;
import com.ljb.shiro.ShiroUtils;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 系统用户Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser queryObject(Integer id) {
        return sysUserRepository.findOne(id);
    }

    @Override
    public SysUser findByUsername(String username){
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public SysUser findByUsernameAndPassword(String username,String password){
        return sysUserRepository.findByUsernameAndPassword(username,password );
    }

    @Override
    public Page<SysUser> queryList(Map<String, Object> map) {
       Query query = new Query<SysUser>(map);
       return sysUserRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<SysUser> queryAll(Map<String, Object> map){
    	Query query = new Query<SysUser>(map);
       return sysUserRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysUser>(map);
       return sysUserRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysUser save(SysUser entity) {
		return sysUserRepository.save(entity);
	}

    @Override
    public SysUser update(SysUser entity) {
        return sysUserRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	sysUserRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
