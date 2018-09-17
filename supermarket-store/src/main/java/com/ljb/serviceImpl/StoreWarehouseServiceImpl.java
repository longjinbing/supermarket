package com.ljb.serviceImpl;

import com.ljb.repository.StoreWarehouseRepository;
import com.ljb.entity.StoreWarehouse;
import com.ljb.service.StoreWarehouseService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ljb.shiro.ShiroUtils;
import java.util.Map;

/**
 * 仓库管理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:12
 */
@Service
public class StoreWarehouseServiceImpl implements StoreWarehouseService {
    @Autowired
    private StoreWarehouseRepository storeWarehouseRepository;
    
    @Override
    public StoreWarehouse queryObject(Integer id) {
        return storeWarehouseRepository.findOne(id);
    }

    @Override
    public Page<StoreWarehouse> queryList(Map<String, Object> map) {
       Query query = new Query<StoreWarehouse>(map);
       return storeWarehouseRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<StoreWarehouse> queryAll(Map<String, Object> map){
    	Query query = new Query<StoreWarehouse>(map);
       return storeWarehouseRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<StoreWarehouse>(map);
       return storeWarehouseRepository.count(query.getSpecification());
    }
    
    
    @Override
	public StoreWarehouse save(StoreWarehouse entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return storeWarehouseRepository.save(entity);
	}

    @Override
    public StoreWarehouse update(StoreWarehouse entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return storeWarehouseRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	storeWarehouseRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
