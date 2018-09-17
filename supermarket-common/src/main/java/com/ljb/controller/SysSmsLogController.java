package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.SysSmsLog;
import com.ljb.service.SysSmsLogService;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 短信日志Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("syssmslog")
@Desc(group="系统设置",name="短信日志",type=1,url="admin/syssmslog.html",status = 0)
public class SysSmsLogController extends AbstractController {
    @Autowired
    private SysSmsLogService sysSmsLogService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("syssmslog:list")
    @Desc(name="短信日志列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("page", sysSmsLogService.queryList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("syssmslog:info")
    @Desc(name="查看短信日志",type=2)
    public R info(@PathVariable("id") Long id) {
        SysSmsLog sysSmsLog = sysSmsLogService.queryObject(id);

        return R.ok().put("sysSmsLog", sysSmsLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("syssmslog:save")
    @Desc(name="添加短信日志",type=2)
    public R save(@RequestBody SysSmsLog sysSmsLog) {
        sysSmsLogService.save(sysSmsLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("syssmslog:update")
    @Desc(name="修改短信日志",type=2)
    public R update(@RequestBody SysSmsLog sysSmsLog) {
        sysSmsLogService.update(sysSmsLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("syssmslog:delete")
    @Desc(name="删除短信日志",type=2)
    public R delete(@RequestBody Long[]ids) {
        sysSmsLogService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="短信日志导出",type=2)
    @RequiresPermissions("syssmslog:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysSmsLog> list = sysSmsLogService.queryAll(params);

        return R.ok().put("list", list);
    }
}
