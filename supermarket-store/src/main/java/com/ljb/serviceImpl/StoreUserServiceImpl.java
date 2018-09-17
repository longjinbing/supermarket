package com.ljb.serviceImpl;

import com.ljb.repository.StoreUserRepository;
import com.ljb.entity.StoreUser;
import com.ljb.service.StoreUserService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ljb.shiro.ShiroUtils;
import java.util.Map;

/**
 * 会员管理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:02
 */
@Service
public class StoreUserServiceImpl implements StoreUserService {
    @Autowired
    private StoreUserRepository storeUserRepository;
    
    @Override
    public StoreUser queryObject(Integer id) {
        return storeUserRepository.findOne(id);
    }

    @Override
    public Page<StoreUser> queryList(Map<String, Object> map) {
       Query query = new Query<StoreUser>(map);
       return storeUserRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<StoreUser> queryAll(Map<String, Object> map){
    	Query query = new Query<StoreUser>(map);
       return storeUserRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<StoreUser>(map);
       return storeUserRepository.count(query.getSpecification());
    }
    
    
    @Override
	public StoreUser save(StoreUser entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return storeUserRepository.save(entity);
	}

    @Override
    public StoreUser update(StoreUser entity) {
		entity.setUpdateId(ShiroUtils.getSysUserId());
        return storeUserRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	storeUserRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}
