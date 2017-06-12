package com.idou.admin;

import java.util.List;
import java.util.Map;

import com.idou.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idou.entity.ProductEntity;
import com.idou.service.ProductService;
import com.idou.util.PageUtils;
import com.idou.util.Query;
import com.idou.util.R;


/**
 * 产品
 *
 * @author ZhangSi
 * @email 917661718@qq.com
 * @date 2017-06-06 15:21:53
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ProductEntity> productList = productService.queryList(query);
        int total = productService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(productList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:info")
    public R info(@PathVariable("id") Long id) {
        ProductEntity product = productService.queryObject(id);

        return R.ok().put("product", product);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:save")
    public R save(@RequestBody ProductEntity product) {
        ValidatorUtils.validateEntity(product);
        productService.save(product);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:update")
    public R update(@RequestBody ProductEntity product) {
        productService.update(product);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:delete")
    public R delete(@RequestBody Long[] ids) {
        productService.deleteBatch(ids);

        return R.ok();
    }

}
