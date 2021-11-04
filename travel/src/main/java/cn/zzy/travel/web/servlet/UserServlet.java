package cn.zzy.travel.web.servlet;

import cn.zzy.travel.domain.ResultInfo;
import cn.zzy.travel.domain.User;
import cn.zzy.travel.service.UserService;
import cn.zzy.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    //对应相对的方法注册方法
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //检验验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //判断验证码
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");

            response.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.getWriter().write(json);
            return;
        }
        //保证验证码只使用一次
        session.removeAttribute("CHECKCODE_SERVER");


        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用方法进行注册
        UserService userService = new UserServiceImpl();
        boolean flag = userService.regist(user);
        //对注册结果进行判断
        ResultInfo info = new ResultInfo();
        if (flag) {
            info.setFlag(true);

        } else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");

        }
        /*response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);*/
        String s = writeValueAsString(info);
        response.getWriter().write(s);

    }

    //登录方法
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名与密码
        Map<String, String[]> map = request.getParameterMap();
        //封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceImpl();
        User user1 = service.login(user);
        //判断用户对象是否为null
        ResultInfo info = new ResultInfo();
        if (user1 == null) {
            //用户名或者密码不存在提示
            info.setFlag(false);
            info.setErrorMsg("用户名密码错误，请更正！");
            //判断用户是否激活
        }
        if (user1 != null && !"Y".equals(user1.getStatus())) {
            //用户没有激活
            info.setFlag(false);
            info.setErrorMsg("没有激活请激活");

        }
        if (user1 != null && "Y".equals(user1.getStatus())) {
            //登录成功
            info.setFlag(true);
            request.getSession().setAttribute("user", user1);
        }
        //响应数据
       /* ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);*/
        writeValue(info,response);


    }

    //从session中查找对象
    public void findone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");
    /*    ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), user);*/
        writeValue(user,response);
    }

    //退出登录
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        request.getSession().invalidate();
        //跳转到登录界面

        response.sendRedirect(request.getContextPath() + "/login.html");
    }
    //激活用户
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            //调用Service进行激活
            UserService userService = new UserServiceImpl();
            boolean flag = userService.active(code);
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            } else {
                msg = "激活失败，请问问你自己干了什么？";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);

        }

    }
}