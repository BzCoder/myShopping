package controller;

import constString.Const;
import service.MyCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
*增删改查
 */
@WebServlet(name = "ShoppingClServlet", urlPatterns = "/ShoppingClServlet")
public class ShoppingClServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        System.out.println("书号" + id);
        MyCartService myCart = (MyCartService) request.getSession().getAttribute(Const.CART_CONST);
        if ("del".equals(type)) {
            myCart.delBook(id);
        } else if ("add".equals(type)) {
            myCart.addBook(id);
        } else if ("update".equals(type)) {
            System.out.println("更新");
            String update_book_count[] = request.getParameterValues("book_count");
            String update_ids[] = request.getParameterValues("id");
            for (int i = 0; i < update_ids.length; i++) {
                myCart.updateBook(update_ids[i], update_book_count[i]);
            }
        }
        response.sendRedirect("/GoMyCartServlet");

    }
}
