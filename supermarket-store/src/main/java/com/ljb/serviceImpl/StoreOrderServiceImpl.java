package com.ljb.serviceImpl;

import com.ljb.entity.SysMenu;
import com.ljb.repository.StoreOrderRepository;
import com.ljb.entity.StoreOrder;
import com.ljb.service.StoreOrderService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ljb.shiro.ShiroUtils;

import javax.persistence.criteria.*;
import java.util.Map;

/**
 * 订单管理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:33
 */
@Service
public class StoreOrderServiceImpl implements StoreOrderService {
    @Autowired
    private StoreOrderRepository storeOrderRepository;
    
    @Override
    public StoreOrder queryObject(Integer id) {
        return storeOrderRepository.findOne(id);
    }

    @Override
    public Page<StoreOrder> queryList(Map<String, Object> map) {
       Query query = new Query<StoreOrder>(map);
        Specification<StoreOrder> specification=new Specification<StoreOrder>() {
            @Override
            public Predicate toPredicate(Root<StoreOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Integer> name=root.get("parentId");
                criteriaQuery.where(criteriaBuilder.equal(name,0));
                return null;
            }
        };
       return storeOrderRepository.findAll(query.getSpecification().and(specification), query.getPageable());
    }
    
     @Override
    public List<StoreOrder> queryAll(Map<String, Object> map){
    	Query query = new Query<StoreOrder>(map);
       return storeOrderRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<StoreOrder>(map);
       return storeOrderRepository.count(query.getSpecification());
    }
    
    
    @Override
	public StoreOrder save(StoreOrder entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return storeOrderRepository.save(entity);
	}

    @Override
    public StoreOrder update(StoreOrder entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return storeOrderRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	storeOrderRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
