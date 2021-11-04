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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名与密码
       Map<String,String[]> map= request.getParameterMap();
       //封装User对象
        User user =new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service =new UserServiceImpl();
        User user1 = service.login(user);
        //判断用户对象是否为null
        ResultInfo info=new ResultInfo();
        if (user1==null){
            //用户名或者密码不存在提示
            info.setFlag(false);
            info.setErrorMsg("用户名密码错误，请更正！");
            //判断用户是否激活
        }if (user1!=null&& !"Y".equals(user1.getStatus())){
            //用户没有激活
            info.setFlag(false);
            info.setErrorMsg("没有激活请激活");

        }if (user1!=null&&"Y".equals(user1.getStatus())){
            //登录成功
            info.setFlag(true);
        }
        //响应数据
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
        request.getSession().setAttribute("user",user1);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
