package cn.zzy.travel.web.servlet;

import cn.zzy.travel.domain.Category;
import cn.zzy.travel.service.CategoryService;
import cn.zzy.travel.service.impl.CategoryImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service=new CategoryImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、调用查找方法查找所有的路线信息
       List<Category> all=service.findAll();
       //2、将信息封装为json
    /*    ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),all);*/
        writeValue(all,response);
    }


    public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("category的find方法");
    }
}
