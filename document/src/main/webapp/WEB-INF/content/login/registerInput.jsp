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
    <title>注册新用户</title>
    <style>
        .sselect {
            float: left;
        }
    </style>
</head>
<body>
<h2>注册用户</h2>
<s:actionerror/>
<s:form action="login_register" method="post">
    <s:textfield label="用户名称" name="cUser.username"/>
    <s:password label="用户密码" name="cUser.password"/>
    <s:password label="重复密码" name="repeatPWD"/>
    <s:textfield label="用户昵称" name="cUser.nickname"/>
    <s:textfield label="用户邮箱" name="cUser.email"/>
    <s:select name="depId" label="用户部门" value="depId"
              cssClass="sselect"
              list="allds" listKey="id" listValue="name" multiple="false"/>
    <s:submit value="注册用户"/>
    <s:reset value="重置"/>
</s:form>

</body>
</html>
