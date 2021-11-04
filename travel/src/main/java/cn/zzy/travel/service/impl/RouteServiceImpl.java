package cn.zzy.travel.service.impl;

import cn.zzy.travel.dao.FavoriteDao;
import cn.zzy.travel.dao.RouteDao;
import cn.zzy.travel.dao.RouteImgDao;
import cn.zzy.travel.dao.SellerDao;
import cn.zzy.travel.dao.impl.FavoriteDaoImpl;
import cn.zzy.travel.dao.impl.RouteDaoImpl;
import cn.zzy.travel.dao.impl.RouteImgDaoImpl;
import cn.zzy.travel.dao.impl.SellerDaoImpl;
import cn.zzy.travel.domain.PageBean;
import cn.zzy.travel.domain.Route;
import cn.zzy.travel.domain.RouteImg;
import cn.zzy.travel.domain.Seller;
import cn.zzy.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao=new RouteDaoImpl();
    private RouteImgDao imgDao=new RouteImgDaoImpl();
    private SellerDao sellerDao=new SellerDaoImpl();
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装PageBean并返回即可
        PageBean<Route> pageBean = new PageBean<>();
        //设置当前的页面的页数与每页能够展示数据的大小
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //设置总记录数
        int totalCount = dao.getTotalCount(cid,rname);
        pageBean.setTotalCount(totalCount);
        //设置当前页面显示的集合
        //开始的记录数=当前页码-1 *每页显示的条数
        int start=(currentPage - 1) * pageSize;
        List<Route> list=dao.findByPage(cid, start, pageSize,rname);
        pageBean.setList(list);
        //设置总页数=总记录数/每页显示的条数
        int totalPage=totalCount%pageSize==0 ? totalCount/pageSize : totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }


    //根据id查询线路的详情
    @Override
    public Route findOne(String rid) {
        //根据id先查询route表中route对象
        Route route = dao.findById(Integer.parseInt(rid));
        //根据route的id查询图片的集合
        List<RouteImg> imgs = imgDao.findByRid(route.getRid());
        //将集合设置到route的对象中
        route.setRouteImgList(imgs);

        //根据route的sid查询卖家的信息一个商家对应多个信息
        Seller sells = sellerDao.findBySellid(route.getSid());
        route.setSeller(sells);

        //查寻收藏次数
      int count=  favoriteDao.findCountByRid(route.getRid());
      route.setCount(count);

        return route;
    }
}
