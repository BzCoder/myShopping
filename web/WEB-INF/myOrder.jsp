<%@ page import="constString.Const" %>
<%@ page import="databean.UserBean" %>
<%@ page import="databean.BookBean" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/28
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<div style="text-align: center">
    <body>
    <h1>我的订单</h1>
    <h1>我的个人信息</h1>
    <table align="center" border="1">
        <tr>
            <td colspan="2">用户个人信息</td>
        </tr>
        <%
            UserBean bean = (UserBean) session.getAttribute(Const.USER_BEAN);
        %>
        <tr>
            <td>用户名</td>
            <td><%=bean.getName()%>
            </td>
        </tr>
        <tr>
            <td>电子邮件</td>
            <td><%=bean.getEmail()%>
            </td>
        </tr>
        <tr>
            <td>用户级别</td>
            <td><%=bean.getGrade()%>
            </td>
        </tr>
    </table>
    <hr>
    <table align="center" border="1" style="border-collapse: collapse;width: 600px ;">
        <tr>
            <td>bookid</td>
            <td>书名</td>
            <td>价格</td>
            <td>出版社</td>
            <td>数量</td>
        </tr>
        <%
            ArrayList<BookBean> bookList = (ArrayList<BookBean>) request.getAttribute(Const.ALL_BOOK);
            for (BookBean book : bookList) {
        %>
        <tr>
            <input type="hidden" value="<%=book.getId()%>" name="id">
            <td><%=book.getId()%>
            </td>
            <td><%=book.getName()%>
            </td>
            <td><%=book.getPrice()%>
            </td>
            <td><%=book.getPublisher()%>
            </td>
            <td><%=book.getNums()%>本</td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan="5" style="text-align: center">总价为${BOOK_TOTAL}</td>
        </tr>
    </table>
    <input type="button" onclick=location.href="/CommitOrderController" value="确认订单"/>
    </body>
</div>
</html>
