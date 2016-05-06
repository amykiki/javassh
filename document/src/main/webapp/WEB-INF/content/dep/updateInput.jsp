<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/6
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改部门名称</title>
</head>
<body>
<s:form action="dep_update" method="post">
    <s:textfield label="部门名称" name="dep.name"/>
    <s:hidden name="dep.id"/>
    <s:submit value="修改部门"/>
</s:form>

</body>
</html>
