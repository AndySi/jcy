package com.idou.oss;

import com.idou.config.ConfigConstant;
import com.idou.constant.Constants;
import com.idou.entity.CloudStorageConfigVo;
import com.idou.service.SysConfigService;
import com.idou.util.SpringContextUtils;

/**
 * 文件上传Factory
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-06 上午 11:21
 **/

public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfigVo config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfigVo.class);

        if(config.getType() == Constants.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constants.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constants.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }
}
