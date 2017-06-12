package com.idou.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-04-07 下午 4:03
 **/

public class Query extends LinkedHashMap<String, Object> {


    private static final long serialVersionUID = -3449195791364698432L;
    private int page;
    private int limit;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        this.page = Integer.valueOf(params.get("page").toString());
        this.limit = Integer.valueOf(params.get("limit").toString());

        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
