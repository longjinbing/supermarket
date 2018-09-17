package com.ljb.repository;

import com.ljb.entity.StoreCart;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 下单处理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:33
 */
public interface StoreCartRepository extends ExpandJpaRepository<StoreCart,Integer>, JpaSpecificationExecutor<StoreCart> {

    StoreCart findByGoodsSn(String goodsSn);

    StoreCart findByTypeAndGoodsSn(Integer type,String goodsSn);

    List<StoreCart> findByType(Integer type);

}
