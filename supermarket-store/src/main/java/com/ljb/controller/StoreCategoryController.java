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
import com.ljb.entity.StoreCategory;
import com.ljb.service.StoreCategoryService;
import com.ljb.util.R;
import com.ljb.annotation.Desc;

/**
 * 商品分类Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
@RestController
@RequestMapping("storecategory")
@Desc(group="商品中心",name="商品分类",type=1,url="shop/storecategory.html")
public class StoreCategoryController extends AbstractController {
    @Autowired
    private StoreCategoryService storeCategoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storecategory:list")
    @Desc(name="商品分类列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(storeCategoryService.queryList(params)));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storecategory:info")
    @Desc(name="查看商品分类",type=2)
    public R info(@PathVariable("id") Integer id) {
        StoreCategory storeCategory = storeCategoryService.queryObject(id);

        return R.ok().put("storeCategory", storeCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storecategory:save")
    @Desc(name="添加商品分类",type=2)
    public R save(@RequestBody StoreCategory storeCategory) {
        storeCategoryService.save(storeCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storecategory:update")
    @Desc(name="修改商品分类",type=2)
    public R update(@RequestBody StoreCategory storeCategory) {
        storeCategoryService.update(storeCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storecategory:delete")
    @Desc(name="删除商品分类",type=2)
    public R delete(@RequestBody Integer[]ids) {
        storeCategoryService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="商品分类导出",type=2)
    @RequiresPermissions("storecategory:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StoreCategory> list = storeCategoryService.queryAll(params);

        return R.ok().put("data", list);
    }
}
