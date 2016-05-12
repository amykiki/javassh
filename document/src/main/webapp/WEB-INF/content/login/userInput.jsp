<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/12
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<s:actionerror/>
<div class="errorMessage">
    <s:property value="authinfo"/>
</div>
<s:form action="login_user" method="post">
    <s:textfield label="用户名称" name="cUser.username"/>
    <s:password label="用户密码" name="cUser.password"/>
    <s:submit value="登录"/>
    <s:reset value="重置"/>
</s:form>

</body>
</html>
