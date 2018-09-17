package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.SysUserRole;
import com.ljb.service.SysUserRoleService;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("sysuserrole")
@Desc(group="系统设置",name="用户与角色",type=1,url="admin/sysuserrole.html",status = 0)
public class SysUserRoleController extends AbstractController {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysuserrole:list")
    @Desc(name="用户与角色列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("page", sysUserRoleService.queryList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysuserrole:info")
    @Desc(name="查看用户与角色",type=2)
    public R info(@PathVariable("id") Integer id) {
        SysUserRole sysUserRole = sysUserRoleService.queryObject(id);

        return R.ok().put("sysUserRole", sysUserRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysuserrole:save")
    @Desc(name="添加用户与角色",type=2)
    public R save(@RequestParam("id") Integer id,@RequestParam("roleIds") Integer[] roleIds) {
        sysUserRoleService.save(id,roleIds,getSysUserId());

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysuserrole:update")
    @Desc(name="修改用户与角色",type=2)
    public R update(@RequestBody SysUserRole sysUserRole) {
        sysUserRoleService.update(sysUserRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysuserrole:delete")
    @Desc(name="删除用户与角色",type=2)
    public R delete(@RequestBody Integer[]ids) {
        sysUserRoleService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="用户与角色导出",type=2)
    @RequiresPermissions("sysuserrole:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysUserRole> list = sysUserRoleService.queryAll(params);

        return R.ok().put("list", list);
    }
}
