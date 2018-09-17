package com.ljb.service;

import com.ljb.entity.SysRole;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 角色管理Service接口
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysRoleService {


    List<Map<String, Object>> roleListByuserIdAndAdminId(Integer userId,Integer adminId);

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    SysRole queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    Page<SysRole> queryList(Map<String, Object> map);

	/**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
	Long queryTotal(Map<String, Object> map);
	
	/**
     * 查询
     *
     * @param map 参数
     * @return list
     */
	List<SysRole> queryAll(Map<String, Object> map);
	

    /**
     * 保存实体
     *
     * @param sysRole 实体
     * @return 保存条数
     */
    SysRole save(SysRole entity);

    /**
     * 根据主键更新实体
     *
     * @param sysRole 实体
     * @return 更新条数
     */
    SysRole update(SysRole entity);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    void delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    void deleteBatch(Integer[] ids);
}
