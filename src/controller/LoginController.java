package controller;

import constString.Const;
import databean.UserBean;
import service.BookService;
import service.LoginService;
import service.MyCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController", urlPatterns = "/LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        UserBean user = (UserBean) request.getSession().getAttribute(Const.USER_BEAN);
        if (user != null) {
            request.setAttribute(Const.ALL_BOOK, BookService.getAllBook());
            request.getRequestDispatcher("WEB-INF/hall.jsp").forward(request, response);
        } else {
            user = LoginService.checkUser(Integer.parseInt(id), password);
            if (user != null) {
                request.setAttribute(Const.ALL_BOOK, BookService.getAllBook());
                request.getSession().setAttribute(Const.CART_CONST, new MyCartService());
                request.getSession().setAttribute(Const.USER_BEAN, user);
                request.getRequestDispatcher("WEB-INF/hall.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        }

    }
}
