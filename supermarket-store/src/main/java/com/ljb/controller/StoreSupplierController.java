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
import com.ljb.entity.StoreSupplier;
import com.ljb.service.StoreSupplierService;
import com.ljb.util.R;
import com.ljb.annotation.Desc;

/**
 * 供应商管理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:01
 */
@RestController
@RequestMapping("storesupplier")
@Desc(group="客户管理",name="供应商管理",type=1,url="shop/storesupplier.html")
public class StoreSupplierController extends AbstractController {
    @Autowired
    private StoreSupplierService storeSupplierService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storesupplier:list")
    @Desc(name="供应商管理列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(storeSupplierService.queryList(params)));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storesupplier:info")
    @Desc(name="查看供应商管理",type=2)
    public R info(@PathVariable("id") Integer id) {
        StoreSupplier storeSupplier = storeSupplierService.queryObject(id);

        return R.ok().put("storeSupplier", storeSupplier);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storesupplier:save")
    @Desc(name="添加供应商管理",type=2)
    public R save(@RequestBody StoreSupplier storeSupplier) {
        storeSupplierService.save(storeSupplier);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storesupplier:update")
    @Desc(name="修改供应商管理",type=2)
    public R update(@RequestBody StoreSupplier storeSupplier) {
        storeSupplierService.update(storeSupplier);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storesupplier:delete")
    @Desc(name="删除供应商管理",type=2)
    public R delete(@RequestBody Integer[]ids) {
        storeSupplierService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="供应商管理导出",type=2)
    @RequiresPermissions("storesupplier:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StoreSupplier> list = storeSupplierService.queryAll(params);

        return R.ok().put("data", list);
    }
}
