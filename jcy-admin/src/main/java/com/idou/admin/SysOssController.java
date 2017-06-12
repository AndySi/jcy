package com.idou.admin;

import com.alibaba.fastjson.JSON;
import com.idou.config.ConfigConstant;
import com.idou.constant.Constants;
import com.idou.entity.CloudStorageConfigVo;
import com.idou.entity.SysOssEntity;
import com.idou.oss.OSSFactory;
import com.idou.service.SysConfigService;
import com.idou.service.SysOssService;
import com.idou.util.PageUtils;
import com.idou.util.Query;
import com.idou.util.R;
import com.idou.util.RRException;
import com.idou.validator.ValidatorUtils;
import com.idou.validator.group.AliyunGroup;
import com.idou.validator.group.QcloudGroup;
import com.idou.validator.group.QiniuGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OSS文件上传
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-06 上午 11:09
 **/
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:oss:all")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SysOssEntity> sysOssList = sysOssService.queryList(query);
        int total = sysOssService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysOssList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 云存储配置信息
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfigVo vo = sysConfigService.getConfigObject(KEY, CloudStorageConfigVo.class);
        return R.ok().put("config", vo);
    }


    /**
     * 保存云存储配置信息
     */
    @RequestMapping("/saveConfig")
    @RequiresPermissions("sys:oss:all")
    public R saveConfig(@RequestBody CloudStorageConfigVo config){
        //校验类型
        ValidatorUtils.validateEntity(config);

        if(config.getType() == Constants.CloudService.QINIU.getValue()){
            //校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        }else if(config.getType() == Constants.CloudService.ALIYUN.getValue()){
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        }else if(config.getType() == Constants.CloudService.QCLOUD.getValue()){
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }

        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));

        return R.ok();
    }


    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    @RequiresPermissions("sys:oss:all")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        //上传文件
        String url = OSSFactory.build().upload(file.getBytes());

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);

        return R.ok().put("url", url);
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:oss:all")
    public R delete(@RequestBody Long[] ids){
        sysOssService.deleteBatch(ids);

        return R.ok();
    }
}
