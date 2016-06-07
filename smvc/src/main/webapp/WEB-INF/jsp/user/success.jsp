<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/6/2
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Sucess Page</title>
</head>
<body>
啦啦啦啦<br/>

TotalCount:<spring:bind path="fm.totalCount">${status.value}</spring:bind><br/>
DisCount：<spring:bind path="fm.disCount">${status.value}</spring:bind><br/>
SumMoney：<spring:bind path="fm.sumMoney">${status.value}</spring:bind><br/>
RegisterDate:<spring:bind path="fm.registerDate">${status.value}</spring:bind><br/>
OrderDate：<spring:bind path="fm.orderDate">${status.value}</spring:bind><br/>
</body>
</html>
