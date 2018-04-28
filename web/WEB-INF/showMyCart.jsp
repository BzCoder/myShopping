<%@ page import="java.util.ArrayList" %>
<%@ page import="databean.BookBean" %>
<%@ page import="constString.Const" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/26
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
</head>
<body>
<h1>我的购物车</h1>
    <a href="/LoginController">返回购物大厅</a>
<table border="1" style="border-collapse: collapse;width: 600px ;">
    <tr>
        <td>bookid</td>
        <td>书名</td>
        <td>价格</td>
        <td>出版社</td>
        <td>数量</td>
        <td>点击删除</td>
    </tr>
    <%
        ArrayList<BookBean> bookList = (ArrayList<BookBean>) request.getAttribute(Const.BOOK_LIST);
        for (BookBean book : bookList) {
    %>
    <form action="/ShoppingClServlet?type=update" method="post">
        <tr>
            <input type="hidden" value="<%=book.getId()%>" name="id">
            <td><%=book.getId()%></td>
            <td><%=book.getName()%></td>
            <td><%=book.getPrice()%></td>
            <td><%=book.getPublisher()%></td>
            <td><input type="number" name="book_count" value=<%= book.getNums()%>>本</td>
            <td><a href="/ShoppingClServlet?type=del&id=<%= book.getId()%>">删除</a></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan="6"><input type="submit" value="更新"/></td>
        </tr>
    </form>
    <tr>
        <td colspan="6">总价为${BOOK_TOTAL}</td>
    </tr>
    <tr>
        <td colspan="6"><a href="/GoMyOrder">提交订单</a> </td>
    </tr>
</table>
</body>
</html>
