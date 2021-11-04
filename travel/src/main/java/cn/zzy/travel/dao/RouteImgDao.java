package cn.zzy.travel.dao;

import cn.zzy.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    public List<RouteImg> findByRid( int rid);
}
