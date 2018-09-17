package com.ljb.serviceImpl;

import com.ljb.repository.StoreGoodsRepository;
import com.ljb.entity.StoreGoods;
import com.ljb.service.StoreGoodsService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ljb.shiro.ShiroUtils;
import java.util.Map;

/**
 * 商品管理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
@Service
public class StoreGoodsServiceImpl implements StoreGoodsService {
    @Autowired
    private StoreGoodsRepository storeGoodsRepository;
    
    @Override
    public StoreGoods queryObject(Integer id) {
        return storeGoodsRepository.findOne(id);
    }

    @Override
    public StoreGoods findByGoodsSn(String sn){
        return storeGoodsRepository.findByGoodsSn(sn);
    }

    @Override
    public Page<StoreGoods> queryList(Map<String, Object> map) {
       Query query = new Query<StoreGoods>(map);
       return storeGoodsRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<StoreGoods> queryAll(Map<String, Object> map){
    	Query query = new Query<StoreGoods>(map);
       return storeGoodsRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<StoreGoods>(map);
       return storeGoodsRepository.count(query.getSpecification());
    }
    
    
    @Override
	public StoreGoods save(StoreGoods entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return storeGoodsRepository.save(entity);
	}

    @Override
    public StoreGoods update(StoreGoods entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return storeGoodsRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	storeGoodsRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
