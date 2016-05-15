<%@ page import="doc.enums.Role" %><%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/3/21
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/xml; charset=UTF-8">
    <title><decorator:title/></title>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/css/main.css">
    <script type="text/javascript">
        function dropDownNav() {
            document.getElementById("myDropdown").classList.toggle("show");
        }
        window.onclick = function (event) {
            if (!event.target.matches('.dropbtn')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                var i;
                for (i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }

                }
            }
        }
    </script>
    <decorator:head/>
</head>
<body onload="<decorator:getProperty property='body.onload'/>">
<c:set var="ADMIN" value="<%=Role.ADMIN%>"/>
<c:set var="NORMAL" value="<%=Role.NORMAL%>"/>

<h3>Amy's 公文管理系统</h3>
<div id="nav">
    <div id="headright">
        <s:if test="#session.lguser == null">
            <a href="<%=request.getContextPath()%>/login_userInput.action">用户登录</a>
            <a href="<%=request.getContextPath()%>/login_registerInput.action">用户注册</a>
        </s:if>
        <s:else>
            欢迎[
                <a href="<%=request.getContextPath()%>/user_showSelf.action">${lguser.nickname}</a>
                ]登录文档管理系统
                <a href="<%=request.getContextPath()%>/user_updatePwdInput.action">修改密码</a>
                <a href="<%=request.getContextPath()%>/login_logout.action">注销</a>
        </s:else>
    </div>
    <s:if test="#session.lguser != null">
        <ul>
            <s:set value="#context['struts.actionMapping'].name" var="actionType"/>
            <li class="dropdown">
                <a class="dropbtn" onclick="dropDownNav()" href="javascript:void(0)">全部功能</a>
                <div id="myDropdown" class="dropdown-content">
                    <s:if test="#session.lguser.role.toString() == 'ADMIN'">
                        <a href="<%=request.getContextPath()%>/dep_list.action">部门管理</a>
                        <a href="<%=request.getContextPath()%>/user_list.action">用户管理</a>
                    </s:if>
                    <a href="<%=request.getContextPath()%>/msg_listReceive.action">私人邮件</a>
                    <a href="#">公文信息</a>
                </div>
            </li>
            <s:if test="#session.lguser.role.toString() == 'ADMIN'">
                <s:if test="%{#actionType.contains('dep')}">
                    <li>
                        <a href="<%=request.getContextPath()%>/dep_list.action">部门列表</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/dep_addInput.action">添加部门</a>
                    </li>
                </s:if>
                <s:elseif test="%{#actionType.contains('user')}">
                    <li>
                        <a href="<%=request.getContextPath()%>/user_list.action">用户列表</a>
                    </li>
                </s:elseif>
            </s:if>
            <s:if test="%{#actionType.contains('msg')}">
                <li>
                    <a href="<%=request.getContextPath()%>/msg_listReceive.action">已收邮件</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/msg_listSend.action">已发邮件</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/msg_addInput.action">添加邮件</a>
                </li>
            </s:if>
                <%--<s:property value="#actionType"/>--%>
            <div class="clear-float"></div>
        </ul>
    </s:if>
    <hr/>
</div>
<decorator:body/>
<div align="center">
    CopyRight 2016-2018
</div>
</body>
</html>
