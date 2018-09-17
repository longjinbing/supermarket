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
import com.ljb.entity.StoreOrder;
import com.ljb.service.StoreOrderService;
import com.ljb.util.R;
import com.ljb.annotation.Desc;

/**
 * 订单管理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:33
 */
@RestController
@RequestMapping("storeorder")
@Desc(group="订单中心",name="订单管理",type=1,url="shop/storeorder.html")
public class StoreOrderController extends AbstractController {
    @Autowired
    private StoreOrderService storeOrderService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storeorder:list")
    @Desc(name="订单管理列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(storeOrderService.queryList(params)));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storeorder:info")
    @Desc(name="查看订单管理",type=2)
    public R info(@PathVariable("id") Integer id) {
        StoreOrder storeOrder = storeOrderService.queryObject(id);

        return R.ok().put("storeOrder", storeOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storeorder:save")
    @Desc(name="添加订单管理",type=2)
    public R save(@RequestBody StoreOrder storeOrder) {
        storeOrderService.save(storeOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storeorder:update")
    @Desc(name="修改订单管理",type=2)
    public R update(@RequestBody StoreOrder storeOrder) {
        storeOrderService.update(storeOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storeorder:delete")
    @Desc(name="删除订单管理",type=2)
    public R delete(@RequestBody Integer[]ids) {
        storeOrderService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="订单管理导出",type=2)
    @RequiresPermissions("storeorder:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StoreOrder> list = storeOrderService.queryAll(params);

        return R.ok().put("data", list);
    }
}
