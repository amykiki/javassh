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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/resources/css/main.css">
    <style>
        #formwrapper {
            margin: 5px auto;
            padding: 10px;
        }
        #formwrapper .radio-div {
            display: block;
        }
        #formwrapper .radio-div label {
            float: left;
        }
        .button {
            text-align: center;
            width: auto;
        }

        .button input {
            width: auto;
        }

        .checks {
            width: 110px;
            display: inline-block;
        }
        .checks input {
            float: left;
            height: 17px;
        }
        .checks label {
            float: left;
            margin-left: 3px;
            line-height: 17px;
            text-align: left;
            vertical-align: middle;
            width: 90px;
        }
        .radios {
            width: 350px;
            margin-left: 80px;
        }
        .radios label {
            width: 50px;
        }
    </style>
    <script type="text/javascript">
        function changeRole(id, node) {
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/user/list", true);
            xhttp.onreadystatechange = function() {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    var ustr = xhttp.responseText;
                    var user = JSON.parse(ustr);
                    if (user.role == 'NORMAL') {
                        node.innerText = '普通用户';
                        node.className = "";
                    } else {
                        node.innerText = '管理员';
                        node.className = "redColor";
                    }
                }
            };
            xhttp.setRequestHeader("Accept", "application/json");
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("id=" + id + "&rolevalue="+node.innerText);
        }
    </script>
</head>
<body>
<div id="formwrapper">
    <sf:form id="uForm" action="/user/list/1" modelAttribute="uf" method="post">
        <fieldset>
            <legend>用户查询</legend>
            <div class="radio-div">
                <label for="name">用户名</label>
                <sf:input path="username" id="name"/>
            </div>
            <div class="radio-div">
                <label for="nickname">用户昵称</label>
                <sf:input path="nickname" id="nickname"/>
            </div>
            <div class="radio-div">
                <label for="depname">部门名称</label>
                <sf:input path="depname" id="depname"/>
            </div>
            <div class="checks radios">
                <label>选择权限</label>
                <c:forEach items="${roles}" var="r" varStatus="st">
                    <sf:radiobutton path="role" value="${r}" id="role${st.index}"/>
                    <label for="role${st.index}">${r}</label>
                </c:forEach>
            </div>
            <div class="button">
                <input type="submit" value="查询"/>
                <input type="reset" value="重置"/>
                <div class="clear-float"></div>
            </div>
        </fieldset>
        <c:if test="${pager.size >= 1}">
            <table class="list">
                <tr>
                    <th>id</th>
                    <th>用户名</th>
                    <th>用户密码</th>
                    <th>用户昵称</th>
                    <th>用户邮箱</th>
                    <th>用户权限</th>
                    <th>用户部门</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pager.list}" var="luser">
                    <tr>
                        <td>${luser.id}</td>
                        <td>${luser.username}</td>
                        <td>${luser.password}</td>
                        <td>${luser.nickname}</td>
                        <td>${luser.email}</td>
                        <c:if test="${luser.role.toString() == 'ADMIN'}">
                            <td><a href="javascript:void(0)"class="redColor" onclick="changeRole(${luser.id}, this);">管理员</a></td>
                        </c:if>
                        <c:if test="${luser.role.toString() == 'NORMAL'}">
                            <td><a href="javascript:void(0)" onclick="changeRole(${luser.id}, this);">普通用户</a></td>
                        </c:if>
                        <td>
                                ${luser.dep.name}
                        </td>
                        <td>
                            <a href="#">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <jsp:include page="/resources/inc/pager.jsp">
                <jsp:param name="formId" value="uForm"/>
                <jsp:param name="currentPage" value="${pager.pageNum}"/>
                <jsp:param name="allPageNums" value="${pager.pages}"/>
                <jsp:param name="begin" value="${pager.firstPage}"/>
                <jsp:param name="end" value="${pager.lastPage}"/>
            </jsp:include>
        </c:if>
        <c:if test="${pager.size < 1}">
            <div class="errorMessage" align="center">
                要查询的用户不存在
            </div>
        </c:if>

    </sf:form>
</div>
</body>
</html>
