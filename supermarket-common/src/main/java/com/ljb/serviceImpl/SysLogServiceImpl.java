package com.ljb.serviceImpl;

import com.ljb.entity.SysLog;
import com.ljb.repository.SysLogRepository;
import com.ljb.service.SysLogService;
import com.ljb.shiro.ShiroUtils;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统日志Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogRepository sysLogRepository;
    
    @Override
    public SysLog queryObject(Long id) {
        return sysLogRepository.findOne(id);
    }

    @Override
    public Page<SysLog> queryList(Map<String, Object> map) {
       Query query = new Query<SysLog>(map);
       return sysLogRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<SysLog> queryAll(Map<String, Object> map){
    	Query query = new Query<SysLog>(map);
       return sysLogRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysLog>(map);
       return sysLogRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysLog save(SysLog entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return sysLogRepository.save(entity);
	}

    @Override
    public SysLog update(SysLog entity) {
        return sysLogRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
    	sysLogRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
      for(Long id: ids ){
      	delete(id);
      }
    }
}
