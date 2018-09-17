package com.ljb.serviceImpl;

import com.ljb.entity.SysSmsLog;
import com.ljb.repository.SysSmsLogRepository;
import com.ljb.service.SysSmsLogService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 短信日志Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysSmsLogServiceImpl implements SysSmsLogService {
    @Autowired
    private SysSmsLogRepository sysSmsLogRepository;
    
    @Override
    public SysSmsLog queryObject(Long id) {
        return sysSmsLogRepository.getOne(id);
    }

    @Override
    public Page<SysSmsLog> queryList(Map<String, Object> map) {
       Query query = new Query<SysSmsLog>(map);
       return sysSmsLogRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<SysSmsLog> queryAll(Map<String, Object> map){
    	Query query = new Query<SysSmsLog>(map);
       return sysSmsLogRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysSmsLog>(map);
       return sysSmsLogRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysSmsLog save(SysSmsLog entity) {
		return sysSmsLogRepository.save(entity);
	}

    @Override
    public SysSmsLog update(SysSmsLog entity) {
        return sysSmsLogRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
    	sysSmsLogRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
      for(Long id: ids ){
      	delete(id);
      }
    }
}
