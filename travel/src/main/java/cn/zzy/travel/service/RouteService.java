package cn.zzy.travel.service;

import cn.zzy.travel.domain.PageBean;
import cn.zzy.travel.domain.Route;

public interface RouteService {
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

    Route findOne(String rid);
}
