package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.SysMenu;
import com.ljb.service.SysMenuService;
import com.ljb.util.PageUtil;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("sysmenu")
@Desc(group="系统设置",name="菜单管理",type=1,url="admin/sysmenu.html")
public class SysMenuController extends AbstractController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysmenu:list")
    @Desc(name="菜单管理列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(sysMenuService.queryList(params)));
    }

    @RequestMapping("/refersh")
    @Desc(name="菜单重置",type=2)
    public R update() {
        if (sysMenuService.refershMenu())
            return R.ok();
        return R.error("刷新失败");
    }

    @RequestMapping("/menulist")
    public R list() {
        // 查询列表数据
        return R.ok().put("menulist", sysMenuService.menuList(getSysUserId()));
    }

    @RequestMapping("/menuselectlist")
    public R selectList() {
        // 查询列表数据
        return R.ok().put("selectlist", sysMenuService.menuSelectList(getSysUserId()));
    }

    @RequestMapping("/ztreelist")
    public R ztreeList() {
        // 查询列表数据
        return R.ok().put("selectlist", sysMenuService.zTreeList(getSysUserId()));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysmenu:info")
    @Desc(name="查看菜单管理",type=2)
    public R info(@PathVariable("id") Integer id) {
        SysMenu sysMenu = sysMenuService.queryObject(id);

        return R.ok().put("sysMenu", sysMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysmenu:save")
    @Desc(name="添加菜单管理",type=2)
    public R save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysmenu:update")
    @Desc(name="修改菜单管理",type=2)
    public R update(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysmenu:delete")
    @Desc(name="删除菜单管理",type=2)
    public R delete(@RequestBody Integer[]ids) {
        sysMenuService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="菜单管理导出",type=2)
    @RequiresPermissions("sysmenu:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysMenu> list = sysMenuService.queryAll(params);

        return R.ok().put("list", list);
    }
}
