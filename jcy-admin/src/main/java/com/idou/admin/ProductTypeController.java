package com.idou.admin;

import java.util.List;
import java.util.Map;

import com.idou.entity.SysMenuEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idou.entity.ProductTypeEntity;
import com.idou.service.ProductTypeService;
import com.idou.util.PageUtils;
import com.idou.util.Query;
import com.idou.util.R;


/**
 * 产品类型
 *
 * @author ZhangSi
 * @email 917661718@qq.com
 * @date 2017-06-06 15:21:53
 */
@RestController
@RequestMapping("/producttype")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("producttype:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ProductTypeEntity> productTypeList = productTypeService.queryList(query);
        int total = productTypeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(productTypeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("/select")
    public R select() {
        return R.ok().put("typeList", productTypeService.queryAll());
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("producttype:info")
    public R info(@PathVariable("id") Integer id) {
        ProductTypeEntity productType = productTypeService.queryObject(id);

        return R.ok().put("productType", productType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("producttype:save")
    public R save(@RequestBody ProductTypeEntity productType) {
        productTypeService.save(productType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("producttype:update")
    public R update(@RequestBody ProductTypeEntity productType) {
        productTypeService.update(productType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("producttype:delete")
    public R delete(@RequestBody Integer[] ids) {
        productTypeService.deleteBatch(ids);

        return R.ok();
    }

}
