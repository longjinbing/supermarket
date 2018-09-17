package com.ljb.serviceImpl;

import com.ljb.repository.StoreCartRepository;
import com.ljb.entity.StoreCart;
import com.ljb.service.StoreCartService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ljb.shiro.ShiroUtils;
import java.util.Map;

/**
 * 下单处理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:33
 */
@Service
public class StoreCartServiceImpl implements StoreCartService {
    @Autowired
    private StoreCartRepository storeCartRepository;


    @Override
   public StoreCart findByType(Integer type){
        List<StoreCart> list=storeCartRepository.findByType(type);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public  List<StoreCart> findAllByType(Integer type){
        return storeCartRepository.findByType(type);
    }

    @Override
    public StoreCart findByGoodsSn(String sn){
        return storeCartRepository.findByGoodsSn(sn);
    }

    @Override
    public StoreCart findByTypeAndGoodsSn(Integer type,String sn){
        return storeCartRepository.findByTypeAndGoodsSn(type, sn);
    }

    @Override
    public StoreCart queryObject(Integer id) {
        return storeCartRepository.findOne(id);
    }

    @Override
    public Page<StoreCart> queryList(Map<String, Object> map) {
       Query query = new Query<StoreCart>(map);
       return storeCartRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<StoreCart> queryAll(Map<String, Object> map){
    	Query query = new Query<StoreCart>(map);
       return storeCartRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<StoreCart>(map);
       return storeCartRepository.count(query.getSpecification());
    }
    
    
    @Override
	public StoreCart save(StoreCart entity) {
		return storeCartRepository.save(entity);
	}

    @Override
    public StoreCart update(StoreCart entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return storeCartRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	storeCartRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
