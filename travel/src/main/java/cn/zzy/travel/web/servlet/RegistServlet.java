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

@WebServlet("/registServlet")
public class RegistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //检验验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //判断验证码
        if (checkcode_server==null ||!checkcode_server.equalsIgnoreCase(check)){
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
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用方法进行注册
        UserService userService = new UserServiceImpl();
        boolean flag=userService.regist(user);
        //对注册结果进行判断
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);

        }else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");

        }
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.getWriter().write(json);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
