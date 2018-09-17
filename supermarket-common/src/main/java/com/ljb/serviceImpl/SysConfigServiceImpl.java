package com.ljb.serviceImpl;

import com.ljb.entity.SysConfig;
import com.ljb.repository.SysConfigRepository;
import com.ljb.service.SysConfigService;
import com.ljb.shiro.ShiroUtils;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息表Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigRepository sysConfigRepository;
    
    @Override
    public SysConfig queryObject(Integer id) {
        return sysConfigRepository.findOne(id);
    }

    @Override
    public Page<SysConfig> queryList(Map<String, Object> map) {
       Query query = new Query<SysConfig>(map);
       return sysConfigRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<SysConfig> queryAll(Map<String, Object> map){
    	Query query = new Query<SysConfig>(map);
       return sysConfigRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysConfig>(map);
       return sysConfigRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysConfig save(SysConfig entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return sysConfigRepository.save(entity);
	}

    @Override
    public SysConfig update(SysConfig entity) {
        return sysConfigRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	sysConfigRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
