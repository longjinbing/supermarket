package com.ljb.serviceImpl;

import com.ljb.repository.StoreInwarehouseRepository;
import com.ljb.entity.StoreInwarehouse;
import com.ljb.service.StoreInwarehouseService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ljb.shiro.ShiroUtils;
import java.util.Map;

/**
 * 商品入库Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
@Service
public class StoreInwarehouseServiceImpl implements StoreInwarehouseService {
    @Autowired
    private StoreInwarehouseRepository storeInwarehouseRepository;
    
    @Override
    public StoreInwarehouse queryObject(Integer id) {
        return storeInwarehouseRepository.findOne(id);
    }

    @Override
    public Page<StoreInwarehouse> queryList(Map<String, Object> map) {
       Query query = new Query<StoreInwarehouse>(map);
       return storeInwarehouseRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<StoreInwarehouse> queryAll(Map<String, Object> map){
    	Query query = new Query<StoreInwarehouse>(map);
       return storeInwarehouseRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<StoreInwarehouse>(map);
       return storeInwarehouseRepository.count(query.getSpecification());
    }
    
    
    @Override
	public StoreInwarehouse save(StoreInwarehouse entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return storeInwarehouseRepository.save(entity);
	}

    @Override
    public StoreInwarehouse update(StoreInwarehouse entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return storeInwarehouseRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	storeInwarehouseRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
