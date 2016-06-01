<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/6/1
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改密码</title>
</head>
<sf:form method="POST" action="pwd">
    旧密码：<input type="password" name="oldPwd">
    <c:if test="${not empty error}">
        ${error}
    </c:if>
    <br/>
    新密码：<input type="password" name="newPwd"><br/>
    确认密码：<input type="password" name="confirmPwd"><br/>
    <input type="submit" value="添加用户"/>
</sf:form>
</body>
</html>
