package com.ljb.serviceImpl;

import com.ljb.repository.StorePositionRepository;
import com.ljb.entity.StorePosition;
import com.ljb.service.StorePositionService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ljb.shiro.ShiroUtils;
import java.util.Map;

/**
 * 货架管理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:21
 */
@Service
public class StorePositionServiceImpl implements StorePositionService {
    @Autowired
    private StorePositionRepository storePositionRepository;
    
    @Override
    public StorePosition queryObject(Integer id) {
        return storePositionRepository.findOne(id);
    }

    @Override
    public Page<StorePosition> queryList(Map<String, Object> map) {
       Query query = new Query<StorePosition>(map);
       return storePositionRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<StorePosition> queryAll(Map<String, Object> map){
    	Query query = new Query<StorePosition>(map);
       return storePositionRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<StorePosition>(map);
       return storePositionRepository.count(query.getSpecification());
    }
    
    
    @Override
	public StorePosition save(StorePosition entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return storePositionRepository.save(entity);
	}

    @Override
    public StorePosition update(StorePosition entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return storePositionRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	storePositionRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
