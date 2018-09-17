package com.ljb.serviceImpl;

import com.ljb.repository.StoreSupplierRepository;
import com.ljb.entity.StoreSupplier;
import com.ljb.service.StoreSupplierService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ljb.shiro.ShiroUtils;
import java.util.Map;

/**
 * 供应商管理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:01
 */
@Service
public class StoreSupplierServiceImpl implements StoreSupplierService {
    @Autowired
    private StoreSupplierRepository storeSupplierRepository;
    
    @Override
    public StoreSupplier queryObject(Integer id) {
        return storeSupplierRepository.findOne(id);
    }

    @Override
    public Page<StoreSupplier> queryList(Map<String, Object> map) {
       Query query = new Query<StoreSupplier>(map);
       return storeSupplierRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<StoreSupplier> queryAll(Map<String, Object> map){
    	Query query = new Query<StoreSupplier>(map);
       return storeSupplierRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<StoreSupplier>(map);
       return storeSupplierRepository.count(query.getSpecification());
    }
    
    
    @Override
	public StoreSupplier save(StoreSupplier entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return storeSupplierRepository.save(entity);
	}

    @Override
    public StoreSupplier update(StoreSupplier entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return storeSupplierRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	storeSupplierRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
