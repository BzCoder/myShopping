<%@ page import="java.util.List" %>
<%@ page import="databean.BookBean" %>
<%@ page import="constString.Const" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/26
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物大厅</title>
</head>
<body>
<h1>欢迎来到购物大厅</h1>
<table border="1">
    <tr>
        <td>书名</td>
        <td>价格</td>
        <td>出版社</td>
        <td>点击购买</td>
    </tr>
    <%
        List<BookBean> books = (List<BookBean>) request.getAttribute(Const.ALL_BOOK);
        for (BookBean book : books) {
    %>
    <tr>
        <td><%=book.getName()%></td>
        <td><%=book.getPrice()%></td>
        <td><%=book.getPublisher()%></td>
        <td><a href="ShoppingClServlet?type=add&id=<%=book.getId()+""%>"+>购买</a></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="4"><input type="button" value="查看购物车" onclick=""></td>
    </tr>

</table>
</body>
</html>
