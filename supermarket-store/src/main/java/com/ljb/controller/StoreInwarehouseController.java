package com.ljb.controller;

import java.util.List;
import java.util.Map;

import com.ljb.entity.StoreGoods;
import com.ljb.service.StoreGoodsService;
import com.ljb.util.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ljb.entity.StoreInwarehouse;
import com.ljb.service.StoreInwarehouseService;
import com.ljb.util.R;
import com.ljb.annotation.Desc;

/**
 * 商品入库Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
@RestController
@RequestMapping("storeinwarehouse")
@Desc(group="商品中心",name="商品入库",type=1,url="shop/storeinwarehouse.html")
public class StoreInwarehouseController extends AbstractController {
    @Autowired
    private StoreInwarehouseService storeInwarehouseService;

    @Autowired
    private StoreGoodsService storeGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storeinwarehouse:list")
    @Desc(name="商品入库列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(storeInwarehouseService.queryList(params)));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storeinwarehouse:info")
    @Desc(name="查看商品入库",type=2)
    public R info(@PathVariable("id") Integer id) {
        StoreInwarehouse storeInwarehouse = storeInwarehouseService.queryObject(id);

        return R.ok().put("storeInwarehouse", storeInwarehouse);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storeinwarehouse:save")
    @Desc(name="添加商品入库",type=2)
    public R save(@RequestBody StoreInwarehouse storeInwarehouse) {
        storeInwarehouseService.save(storeInwarehouse);
        StoreGoods storeGoods=storeGoodsService.queryObject(storeInwarehouse.getGoodsId());
        storeGoods.setNumber(storeGoods.getNumber()+storeInwarehouse.getNumber());
        storeGoodsService.update(storeGoods);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storeinwarehouse:update")
    @Desc(name="修改商品入库",type=2)
    public R update(@RequestBody StoreInwarehouse storeInwarehouse) {
        StoreInwarehouse storeInwarehouse1=new StoreInwarehouse();
        BeanUtils.copyProperties(storeInwarehouse, storeInwarehouse1);
        storeInwarehouse1.setNumber(storeInwarehouse.getNumber());
        storeInwarehouseService.update(storeInwarehouse);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storeinwarehouse:delete")
    @Desc(name="删除商品入库",type=2)
    public R delete(@RequestBody Integer[]ids) {
        storeInwarehouseService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="商品入库导出",type=2)
    @RequiresPermissions("storeinwarehouse:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StoreInwarehouse> list = storeInwarehouseService.queryAll(params);

        return R.ok().put("data", list);
    }
}
