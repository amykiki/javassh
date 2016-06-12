<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/11
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>用户详情</title>
    <style>
        .wwFormTable input[radio] {
            height: 14px;
        }
        button a {
            color: #666666;
            display: inline-block;

        }
    </style>
</head>
<body>
<table class="list">
    <tr>
        <td>用户id</td>
        <td>${user.id}</td>
    </tr>
    <tr>
        <td>用户名称</td>
        <td>${user.username}</td>
    </tr>
    <tr>
        <td>用户昵称</td>
        <td>${user.nickname}</td>
    </tr>
    <tr>
        <td>用户邮箱</td>
        <td>${user.email}</td>
    </tr>
    <tr>
        <td>用户权限</td>
        <c:if test="${user.role.toString() == 'ADMIN'}">
            <td>管理员</td>
        </c:if>
        <c:if test="${user.role.toString() == 'NORMAL'}">
            <td>普通用户</td>
        </c:if>

    </tr>
    <tr>
        <td>用户部门</td>
        <td>${user.dep.name}</td>
    </tr>
</table>
<%--<button onclick="location.href='/user_updateSelfInput.action';">更新用户</button>--%>
</body>
</html>
