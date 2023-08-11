package com.hjq.web.servlet;

import com.hjq.domain.Category;
import com.hjq.service.CategoryService;
import com.hjq.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  11:28
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{

    private CategoryService service = new CategoryServiceImpl();

    /**
     * 查询所有分类数据
     * @param request
     * @param response
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) {
        //1.调用service查询所有;
        List<Category> all = service.findAll();
        //2.序列化json返回
        try {
            writeValue(all,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
