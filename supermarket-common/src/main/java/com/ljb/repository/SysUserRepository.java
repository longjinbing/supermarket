package com.ljb.repository;

import com.ljb.entity.SysUser;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 系统用户Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysUserRepository extends ExpandJpaRepository<SysUser,Integer>, JpaSpecificationExecutor<SysUser> {

    SysUser findByUsername(String username);

    SysUser findByUsernameAndPassword(String username,String password);

}
