<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/12
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<s:form action="user_updatePwd" method="POST">
    <s:password label="原始密码" name="oldPwd"/>
    <s:password label="新密码" name="cUser.password"/>
    <s:submit value="修改密码"/>
    <s:reset value="重置"/>
</s:form>

</body>
</html>
