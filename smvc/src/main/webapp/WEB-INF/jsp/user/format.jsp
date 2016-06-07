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
    <title>Format测试</title>
</head>
<body>
<sf:form method="post" modelAttribute="fm">
    TotalCount:<sf:input path="totalCount"/><br/>
    DisCount：<sf:input path="disCount"/><br/>
    SumMoney：<sf:input path="sumMoney"/><br/>
    RegisterDate: <sf:input path="registerDate"/><br/>
    OrderDate：<sf:input path="OrderDate"/><br/>
    <input type="submit" value="Format测试"/>
</sf:form>

</body>
</html>
