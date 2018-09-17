package com.ljb.repository;

import com.ljb.entity.StoreUser;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 会员管理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:02
 */
public interface StoreUserRepository extends ExpandJpaRepository<StoreUser,Integer>, JpaSpecificationExecutor<StoreUser> {

}
