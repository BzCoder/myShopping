<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/26
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎登录</title>
</head>
<body>
<h1>登录界面</h1>
<form action="/LoginController" method="post">
    <table border="1">
        <tr>
            <td>用户ID</td>
            <td><input type="text" name="id" id="id"></td>
        </tr>
        <tr>
            <td>密 码</td>
            <td><input type="text" name="password" id="password"></td>
        </tr>
        <tr>
            <td><input type="submit" name="登录"></td>
            <td><input type="button" value="清空"></td>
        </tr>
    </table>
</form>
</body>
</html>
