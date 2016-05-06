<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/5
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/xml; charset=UTF-8">
    <title>部门列表</title>
</head>
<body>
<table class="list">
    <tr>
        <th>部门id</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>
    <s:iterator value="#ds">
        <tr>
            <td>${id}</td>
            <td><a href="dep_show.action?dep.id=${id}">${name}</a></td>
            <td>
                <a href="dep_updateInput.action?dep.id=${id}">更新</a>
                <a href="dep_delete.action?dep.id=${id}">删除</a>
                <a href="dep_updateScopeInput.action?dep.id=${id}">设置可发文部门</a>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
