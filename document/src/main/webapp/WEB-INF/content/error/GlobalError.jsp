<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/11
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>Global Error</title>
</head>
<body>
<h2>Global Error Occured</h2>
<h3 class="errorMessage">
    <s:property value="exception"/>
    <s:actionerror/>
    <div class="errorMessage">
        <s:property value="authinfo"/>
    </div>
</h3>
</body>
</html>
