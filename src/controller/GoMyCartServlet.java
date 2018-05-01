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

@WebServlet(name = "GoMyCartServlet",urlPatterns = "/GoMyCartServlet")
public class GoMyCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        MyCartService myCart = (MyCartService) request.getSession().getAttribute(Const.CART_CONST);
        request.setAttribute(Const.BOOK_LIST, myCart.showMyCart());
        request.setAttribute(Const.BOOK_TOTAL, myCart.getMyCartSum());
        request.getRequestDispatcher("WEB-INF/showMyCart.jsp").forward(request, response);

    }
}
