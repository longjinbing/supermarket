package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.SysLog;
import com.ljb.service.SysLogService;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统日志Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("syslog")
@Desc(group="系统设置",name="系统日志",type=1,url="admin/syslog.html")
public class SysLogController extends AbstractController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("syslog:list")
    @Desc(name="系统日志列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("page", sysLogService.queryList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("syslog:info")
    @Desc(name="查看系统日志",type=2)
    public R info(@PathVariable("id") Long id) {
        SysLog sysLog = sysLogService.queryObject(id);

        return R.ok().put("sysLog", sysLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("syslog:save")
    @Desc(name="添加系统日志",type=2)
    public R save(@RequestBody SysLog sysLog) {
        sysLogService.save(sysLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("syslog:update")
    @Desc(name="修改系统日志",type=2)
    public R update(@RequestBody SysLog sysLog) {
        sysLogService.update(sysLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("syslog:delete")
    @Desc(name="删除系统日志",type=2)
    public R delete(@RequestBody Long[]ids) {
        sysLogService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="系统日志导出",type=2)
    @RequiresPermissions("syslog:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysLog> list = sysLogService.queryAll(params);

        return R.ok().put("list", list);
    }
}
