package com.ljb.serviceImpl;

import com.ljb.entity.SysRegion;
import com.ljb.repository.SysRegionRepository;
import com.ljb.service.SysRegionService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 全球地址Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysRegionServiceImpl implements SysRegionService {
    @Autowired
    private SysRegionRepository sysRegionRepository;
    
    @Override
    public SysRegion queryObject(Integer id) {
        return sysRegionRepository.findOne(id);
    }

    @Override
    public Page<SysRegion> queryList(Map<String, Object> map) {
       Query query = new Query<SysRegion>(map);
       return sysRegionRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<SysRegion> queryAll(Map<String, Object> map){
    	Query query = new Query<SysRegion>(map);
       return sysRegionRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysRegion>(map);
       return sysRegionRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysRegion save(SysRegion entity) {
		return sysRegionRepository.save(entity);
	}

    @Override
    public SysRegion update(SysRegion entity) {
        return sysRegionRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	sysRegionRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
