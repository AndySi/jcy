package com.idou.oss;

import com.aliyun.oss.OSSClient;
import com.idou.entity.CloudStorageConfigVo;
import com.idou.util.RRException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云存储
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-06 上午 11:21
 */
public class AliyunCloudStorageService extends CloudStorageService {
    private OSSClient client;

    public AliyunCloudStorageService(CloudStorageConfigVo vo) {
        this.vo = vo;

        //初始化
        init();
    }

    private void init() {
        client = new OSSClient(vo.getAliyunEndPoint(), vo.getAliyunAccessKeyId(),
                vo.getAliyunAccessKeySecret());
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(vo.getAliyunBucketName(), path, inputStream);
        } catch (Exception e) {
            throw new RRException("上传文件失败，请检查配置信息", e);
        }

        return vo.getAliyunDomain() + "/" + path;
    }

    @Override
    public String upload(byte[] data) {
        return upload(data, getPath(vo.getAliyunPrefix()));
    }

    @Override
    public String upload(InputStream inputStream) {
        return upload(inputStream, getPath(vo.getAliyunPrefix()));
    }
}
