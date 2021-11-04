package cn.zzy.travel.dao;

import cn.zzy.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    //根据cid查询总记录数
    public int getTotalCount(int cid,String rname);



    //根据根据cid start pageSize 查询当前页面的数据
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);

    //根据id查询
    public Route findById(int rid);
}
