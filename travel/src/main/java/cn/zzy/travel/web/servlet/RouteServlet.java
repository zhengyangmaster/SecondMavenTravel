package cn.zzy.travel.web.servlet;

import cn.zzy.travel.domain.PageBean;
import cn.zzy.travel.domain.Route;
import cn.zzy.travel.domain.User;
import cn.zzy.travel.service.FavoriteService;
import cn.zzy.travel.service.RouteService;
import cn.zzy.travel.service.impl.FavoriteServicImpl;
import cn.zzy.travel.service.impl.RouteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService=new FavoriteServicImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前页面
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接受rname 线路名称
        String rname = request.getParameter("rname");
        //将获得的数据进行转码的到中文
        if (rname != null && rname.length() > 0) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }


        int cid = 0;
        //处理参数，将获取到的值进行处理
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currenPage = 0;
        //处理参数，将获取到的值进行处理
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currenPage = Integer.parseInt(currentPageStr);
        } else {
            //默认为第一页
            currenPage = 1;
        }
        int pageSize = 0;
        //处理参数，将获取到的值进行处理,每页显示的条数
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);

        } else {
            //默认每页显示5条
            pageSize = 5;
        }
        //查询页码数据，进行返回
        PageBean<Route> routePageBean = service.pageQuery(cid, currenPage, pageSize, rname);
        //将返回进行序列化
        writeValue(routePageBean, response);
    }


    //根据id查询一个旅游线路的详细信息
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、接受id
        String rid = request.getParameter("rid");
        //调用service方法查询route对象
        Route route = service.findOne(rid);
        //将数据序列化
        writeValue(route, response);


    }

    //判断是否为收藏线路
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取对应路线的rid
        String  rid = request.getParameter("rid");

        //判断当前登录的用户
        User user = (User) request.getSession().getAttribute("user");

        int uid;
        if (user==null){
            //用户没有登录
            uid=0;
        }else {
            //用户已经登录
            uid=user.getUid();
        }

        //调用Favorit方法进行查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        //将数据返回客户端
        writeValue(flag,response);


    }

    //添加收藏路线


    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //获取rid
        String rid = request.getParameter("rid");

        //获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");

        int uid;
        if (user==null){
            //用户没有登录
            return;

        }else {
            //用户已经登录
            uid=user.getUid();
        }

        //调用方法进行登录
        favoriteService.add(rid,uid);

    }

}