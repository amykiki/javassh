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
    <title><s:property value="dep.name"/>详情</title>
</head>
<body>
<table class="list">
    <tr>
        <td>部门ID</td>
        <td>${dep.id}</td>
    </tr>
    <tr>
        <td>部门名称</td>
        <td><a href="dep_updateScopeInput.action?dep.id=${dep.id}">${dep.name}</a></td>
    </tr>
    <tr>
        <td colspan="2">可发文部门</td>
    </tr>
    <tr>
        <td colspan="2">
            <s:if test="%{#ds.size() > 0}">
                <s:iterator value="#ds">
                    ${name}&nbsp;
                </s:iterator>
            </s:if>
            <s:else>
                没有可发文部门，<a href="dep_updateScopeInput.action?dep.id=${dep.id}">添加可发文部门</a>
            </s:else>
        </td>
    </tr>
    <%--<s:debug/>--%>
</table>
</body>
</html>
