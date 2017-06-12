package com.idou.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;


/**
 * 产品信息
 *
 * @author ZhangSi
 * @email 917661718@qq.com
 * @date 2017-06-06 15:21:53
 */
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //产品ID
    private Long id;
    //产品名称
    @NotBlank(message = "产品名称不能为空")
    private String name;
    //产品型号表ID
    @Min(value = 1,message = "产品型号不能为空")
    private Integer typeId;
    //产品图片
    @NotBlank(message = "请上传产品图片")
    private String picture;
    //产品详情
    @NotBlank(message = "产品详情不能为空")
    private String details;
    //更新时间
    private Date updateTime;
    //创建时间
    private Date createTime;

    //类型名称
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 设置：产品ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：产品ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：产品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：产品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：产品型号表ID
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取：产品型号表ID
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置：产品图片
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取：产品图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置：产品详情
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 获取：产品详情
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置：更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
}
