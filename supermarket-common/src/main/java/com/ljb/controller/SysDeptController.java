package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.SysDept;
import com.ljb.service.SysDeptService;
import com.ljb.util.PageUtil;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 部门管理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("sysdept")
@Desc(group="系统设置",name="部门管理",type=1,url="admin/sysdept.html")
public class SysDeptController extends AbstractController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysdept:list")
    @Desc(name="部门管理列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(sysDeptService.queryList(params)));
    }

    @RequestMapping("/deptlist")
    @RequiresPermissions("sysdept:list")
    public R zTreelist() {
        //查询列表数据
        return R.ok().put("selectlist", sysDeptService.zTreeList(getSysUserId()));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysdept:info")
    @Desc(name="查看部门管理",type=2)
    public R info(@PathVariable("id") Integer id) {
        SysDept sysDept = sysDeptService.queryObject(id);

        return R.ok().put("sysDept", sysDept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysdept:save")
    @Desc(name="添加部门管理",type=2)
    public R save(@RequestBody SysDept sysDept) {
        sysDeptService.save(sysDept);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysdept:update")
    @Desc(name="修改部门管理",type=2)
    public R update(@RequestBody SysDept sysDept) {
        sysDeptService.update(sysDept);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysdept:delete")
    @Desc(name="删除部门管理",type=2)
    public R delete(@RequestBody Integer[]ids) {
        sysDeptService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="部门管理导出",type=2)
    @RequiresPermissions("sysdept:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysDept> list = sysDeptService.queryAll(params);

        return R.ok().put("list", list);
    }
}
