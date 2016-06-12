<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/6/10
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<shiro:authenticated>
    <h3>欢迎${lguser.nickname}登录系统</h3>
    <h5><a href="/logout">退出</a></h5>
</shiro:authenticated>
<shiro:notAuthenticated>
    <h3>欢迎访问Spring-MVC系统，请<a href="/login">登陆</a></h3>
</shiro:notAuthenticated>

</body>
</html>
