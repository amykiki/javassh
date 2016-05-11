<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/11
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>更新用户</title>
</head>
<body>
<s:form action="user_update" method="post">
    <s:textfield label="用户名称" name="cUser.username"/>
    <s:textfield label="用户昵称" name="cUser.nickname"/>
</s:form>

</body>
</html>
