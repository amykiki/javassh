<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/11
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<sf:form method="post" modelAttribute="user">
    用户名:<sf:input path="username"/><sf:errors path="username"/><br/>
    密码：<sf:password path="password"/><sf:errors path="password"/><br/>
    昵称：<sf:input path="nickname"/><sf:errors path="nickname"/><br/>
    邮箱: <sf:input path="email"/><sf:errors path="email"/><br/>
    部门：
    <sf:select path="dep.id">
        <sf:option value="-1" label="--请选择---"/>
        <sf:options items="${deps}" itemValue="id" itemLabel="name"/>
    </sf:select>
    <sf:errors path="dep"/>
    <br/>
    <input type="submit" value="添加用户"/>
</sf:form>

</body>
</html>
