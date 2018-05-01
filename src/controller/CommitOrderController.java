package controller;

import constString.Const;
import databean.UserBean;
import service.MyCartService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CommitOrderController", urlPatterns = "/CommitOrderController")
public class CommitOrderController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            OrderService orderService = new OrderService();
            orderService.submitOrder((MyCartService) request.getSession().getAttribute(Const.CART_CONST),
                    (UserBean) request.getSession().getAttribute(Const.USER_BEAN));
            out.println("下单成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
        }
    }
}
