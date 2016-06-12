<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/6/12
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/resources/inc/inc.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
欢迎访问Spring-MVC系统
<div id="formwrapper">
    <sf:form action="/login" method="post" id="Login" modelAttribute="user">
        <fieldset>
            <legend>用户登录</legend>
            <div>
                <label for="name">用户名</label>
                <sf:input id="name" path="username"/>
                <br/>
            </div>
            <div>
                <label for="password">密码</label>
                <sf:password path="password" id="password"/>
                <br/>
            </div>
            <div class="errorMessage">
                ${error}
            </div>
            <div class="button">
                <input id="but1" type="submit" value="提交"/><input id = "but2" type="reset" value="重置"/>
                <div class="clear-float"></div>
            </div>
        </fieldset>
    </sf:form>
</div>
</body>
</html>
