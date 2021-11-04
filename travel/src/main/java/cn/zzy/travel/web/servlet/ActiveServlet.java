package cn.zzy.travel.web.servlet;

import cn.zzy.travel.service.UserService;
import cn.zzy.travel.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ActiveServlet")
public class ActiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //获取激活码
        String code = request.getParameter("code");
        if (code !=null){
            //调用Service进行激活
            UserService userService = new UserServiceImpl();
           boolean flag= userService.active(code);
            String msg=null;
            if (flag){
                //激活成功
                msg= "激活成功，请<a href='login.html'>登录</a>";
            }else {
                msg="激活失败，请问问你自己干了什么？";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
