package com.idou.config;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 公司信息配置
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-07 上午 9:45
 **/
@Component
@PropertySource("classpath:company.properties")
@ConfigurationProperties(prefix = "company")
public class CompanyProperties {
    /**
     * 公司地址
     */
    @NotBlank(message = "公司地址不能不空")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
