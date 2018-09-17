package com.ljb.repository;

import com.ljb.entity.StoreGoods;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 商品管理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
public interface StoreGoodsRepository extends ExpandJpaRepository<StoreGoods,Integer>, JpaSpecificationExecutor<StoreGoods> {

    StoreGoods findByGoodsSn(String sn);

}
