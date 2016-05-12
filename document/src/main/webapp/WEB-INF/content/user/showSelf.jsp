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
        <td>${lguser.id}</td>
    </tr>
    <tr>
        <td>用户名称</td>
        <td>${lguser.username}</td>
    </tr>
    <tr>
        <td>用户昵称</td>
        <td>${lguser.nickname}</td>
    </tr>
    <tr>
        <td>用户邮箱</td>
        <td>${lguser.email}</td>
    </tr>
    <tr>
        <td>用户权限</td>
        <s:if test="#session.lguser.role.toString() == 'ADMIN'">
            <td>管理员</td>
        </s:if>
        <s:else>
            <td>普通用户</td>
        </s:else>

    </tr>
    <tr>
        <td>用户部门</td>
        <td>${lguser.dep.name}</td>
    </tr>
</table>
<button onclick="location.href='/user_updateSelfInput.action';">更新用户</button>
<s:debug/>
</body>
</html>
