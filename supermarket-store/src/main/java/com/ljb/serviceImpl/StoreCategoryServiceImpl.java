package com.ljb.serviceImpl;

import com.ljb.repository.StoreCategoryRepository;
import com.ljb.entity.StoreCategory;
import com.ljb.service.StoreCategoryService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ljb.shiro.ShiroUtils;
import java.util.Map;

/**
 * 商品分类Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
@Service
public class StoreCategoryServiceImpl implements StoreCategoryService {
    @Autowired
    private StoreCategoryRepository storeCategoryRepository;
    
    @Override
    public StoreCategory queryObject(Integer id) {
        return storeCategoryRepository.findOne(id);
    }

    @Override
    public Page<StoreCategory> queryList(Map<String, Object> map) {
       Query query = new Query<StoreCategory>(map);
       return storeCategoryRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<StoreCategory> queryAll(Map<String, Object> map){
    	Query query = new Query<StoreCategory>(map);
       return storeCategoryRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<StoreCategory>(map);
       return storeCategoryRepository.count(query.getSpecification());
    }
    
    
    @Override
	public StoreCategory save(StoreCategory entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return storeCategoryRepository.save(entity);
	}

    @Override
    public StoreCategory update(StoreCategory entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return storeCategoryRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	storeCategoryRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
