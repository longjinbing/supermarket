package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.SysRegion;
import com.ljb.service.SysRegionService;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 全球地址Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("sysregion")
@Desc(group="系统设置",name="全球地址",type=1,url="admin/sysregion.html",status = 0)
public class SysRegionController extends AbstractController {
    @Autowired
    private SysRegionService sysRegionService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysregion:list")
    @Desc(name="全球地址列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("page", sysRegionService.queryList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysregion:info")
    @Desc(name="查看全球地址",type=2)
    public R info(@PathVariable("id") Integer id) {
        SysRegion sysRegion = sysRegionService.queryObject(id);

        return R.ok().put("sysRegion", sysRegion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysregion:save")
    @Desc(name="添加全球地址",type=2)
    public R save(@RequestBody SysRegion sysRegion) {
        sysRegionService.save(sysRegion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysregion:update")
    @Desc(name="修改全球地址",type=2)
    public R update(@RequestBody SysRegion sysRegion) {
        sysRegionService.update(sysRegion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysregion:delete")
    @Desc(name="删除全球地址",type=2)
    public R delete(@RequestBody Integer[]ids) {
        sysRegionService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="全球地址导出",type=2)
    @RequiresPermissions("sysregion:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysRegion> list = sysRegionService.queryAll(params);

        return R.ok().put("list", list);
    }
}
