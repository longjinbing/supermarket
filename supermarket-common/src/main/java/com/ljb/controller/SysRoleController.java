package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.SysRole;
import com.ljb.service.SysRoleService;
import com.ljb.util.PageUtil;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("sysrole")
@Desc(group="系统设置",name="角色管理",type=1,url="admin/sysrole.html")
public class SysRoleController extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/rolelist/{id}")
    //@RequiresPermissions("sysuserrole:add")
    public R roleList(@PathVariable Integer id) {
        //查询列表数据
        return R.ok().put("rolelist", sysRoleService.roleListByuserIdAndAdminId(id, getSysUserId()));
    }

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysrole:list")
    @Desc(name="角色管理列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(sysRoleService.queryList(params)));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysrole:info")
    @Desc(name="查看角色管理",type=2)
    public R info(@PathVariable("id") Integer id) {
        SysRole sysRole = sysRoleService.queryObject(id);

        return R.ok().put("sysRole", sysRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysrole:save")
    @Desc(name="添加角色管理",type=2)
    public R save(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysrole:update")
    @Desc(name="修改角色管理",type=2)
    public R update(@RequestBody SysRole sysRole) {
        sysRoleService.update(sysRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysrole:delete")
    @Desc(name="删除角色管理",type=2)
    public R delete(@RequestBody Integer[]ids) {
        sysRoleService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="角色管理导出",type=2)
    @RequiresPermissions("sysrole:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysRole> list = sysRoleService.queryAll(params);

        return R.ok().put("list", list);
    }
}
