package com.mju.generatepaper.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

public class PageParams extends Page implements Serializable {
    private static final long serialVersionUID = -1710273706052960025L;
    private int page;
    private int limit;
    private String sort;
    private String order;
    private Map<String, Object> requestMap;
    private String orderBy;

    public PageParams(Map map) {
        this.page = 1;
        this.limit = 20;
        this.requestMap = Maps.newHashMap();
        if (map == null) {
            map = Maps.newHashMap();
        }
        this.page = Integer.parseInt(((Map)map).getOrDefault("page", 1).toString());
        this.limit = Integer.parseInt(((Map)map).getOrDefault("limit", 20).toString());
        this.sort = (String)((Map)map).getOrDefault("sort", "");
        this.order = (String)((Map)map).getOrDefault("order", "");
        super.setCurrent((long)this.page);
        super.setSize((long)this.limit);
        ((Map)map).remove("page");
        ((Map)map).remove("limit");
        ((Map)map).remove("sort");
        ((Map)map).remove("order");
        this.requestMap.putAll((Map)map);
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Map<String, Object> getRequestMap() {
        return requestMap;
    }

    public void setRequestMap(Map<String, Object> requestMap) {
        this.requestMap = requestMap;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
