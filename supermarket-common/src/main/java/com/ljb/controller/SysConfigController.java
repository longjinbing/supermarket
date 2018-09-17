package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.SysConfig;
import com.ljb.service.SysConfigService;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息表Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("sysconfig")
@Desc(group="系统设置",name="系统配置信息表",type=1,url="admin/sysconfig.html",status = 0)
public class SysConfigController extends AbstractController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysconfig:list")
    @Desc(name="系统配置信息表列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("page", sysConfigService.queryList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysconfig:info")
    @Desc(name="查看系统配置信息表",type=2)
    public R info(@PathVariable("id") Integer id) {
        SysConfig sysConfig = sysConfigService.queryObject(id);

        return R.ok().put("sysConfig", sysConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysconfig:save")
    @Desc(name="添加系统配置信息表",type=2)
    public R save(@RequestBody SysConfig sysConfig) {
        sysConfigService.save(sysConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysconfig:update")
    @Desc(name="修改系统配置信息表",type=2)
    public R update(@RequestBody SysConfig sysConfig) {
        sysConfigService.update(sysConfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysconfig:delete")
    @Desc(name="删除系统配置信息表",type=2)
    public R delete(@RequestBody Integer[]ids) {
        sysConfigService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="系统配置信息表导出",type=2)
    @RequiresPermissions("sysconfig:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysConfig> list = sysConfigService.queryAll(params);

        return R.ok().put("list", list);
    }
}
