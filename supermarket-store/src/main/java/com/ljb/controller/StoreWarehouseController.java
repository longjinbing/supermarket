package com.ljb.controller;

import java.util.List;
import java.util.Map;

import com.ljb.util.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ljb.entity.StoreWarehouse;
import com.ljb.service.StoreWarehouseService;
import com.ljb.util.R;
import com.ljb.annotation.Desc;

/**
 * 仓库管理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:12
 */
@RestController
@RequestMapping("storewarehouse")
@Desc(group="仓库管理",name="仓库管理",type=1,url="shop/storewarehouse.html")
public class StoreWarehouseController extends AbstractController {
    @Autowired
    private StoreWarehouseService storeWarehouseService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storewarehouse:list")
    @Desc(name="仓库管理列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(storeWarehouseService.queryList(params)));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storewarehouse:info")
    @Desc(name="查看仓库管理",type=2)
    public R info(@PathVariable("id") Integer id) {
        StoreWarehouse storeWarehouse = storeWarehouseService.queryObject(id);

        return R.ok().put("storeWarehouse", storeWarehouse);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storewarehouse:save")
    @Desc(name="添加仓库管理",type=2)
    public R save(@RequestBody StoreWarehouse storeWarehouse) {
        storeWarehouseService.save(storeWarehouse);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storewarehouse:update")
    @Desc(name="修改仓库管理",type=2)
    public R update(@RequestBody StoreWarehouse storeWarehouse) {
        storeWarehouseService.update(storeWarehouse);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storewarehouse:delete")
    @Desc(name="删除仓库管理",type=2)
    public R delete(@RequestBody Integer[]ids) {
        storeWarehouseService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="仓库管理导出",type=2)
    @RequiresPermissions("storewarehouse:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StoreWarehouse> list = storeWarehouseService.queryAll(params);

        return R.ok().put("data", list);
    }
}
