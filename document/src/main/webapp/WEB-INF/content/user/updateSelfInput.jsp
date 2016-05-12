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
    <title>更新用户</title>
    <style>
        .wwFormTable input[radio] {
            height: 14px;
        }

        .sradio {
            height: 14px;
            vertical-align: middle;
            float: left;
        }

        .sselect {
            float: left;
        }
    </style>
</head>
<body>
<s:actionerror/>
<s:form action="user_updateSelf" method="post">
    <s:textfield label="用户名称" name="cUser.username"/>
    <s:textfield label="用户昵称" name="cUser.nickname"/>
    <s:textfield label="用户邮箱" name="cUser.email"/>
    <s:submit value="修改用户"/>
    <s:reset value="重置"/>
</s:form>

</body>
</html>
