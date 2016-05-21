<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/9
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/xml; charset=UTF-8">
    <title>用户列表</title>
    <style>
        #formwrapper {
            margin: 5px auto;
            padding: 10px;
        }
        .button {
            text-align: center;
            width: auto;
        }

        .button input {
            width: auto;
        }

        .wwFormTable {
            margin: 0 auto;
            text-align: left;
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
            text-align: left;
            vertical-align: middle;
            width: 90px;
        }
        .radios {
            width: 350px;
        }
        .radios label {
            width: 50px;
        }
    </style>
    <script type="text/javascript">
        function checkAll(ele) {
            var cbs = document.getElementsByTagName('input');
            for (var i = 0; i < cbs.length; i++) {
                if (cbs[i].type == 'checkbox') {
                    cbs[i].checked = true;
                }
            }
        }
        function unCheckAll(ele) {
            var cbs = document.getElementsByTagName('input');
            for (var i = 0; i < cbs.length; i++) {
                if (cbs[i].type == 'checkbox') {
                    cbs[i].checked = false;
                }
            }
        }
        function modifyUser(uid, methodName, paramValue) {
            var theForm = document.getElementById("uForm");
            var actionStr = "user_";
            actionStr = actionStr.concat(methodName, ".action");
            theForm.action = actionStr;
            var delId = document.createElement("input");
            delId.type = "hidden";
            delId.name = "uid";
            delId.value = uid;
            theForm.appendChild(delId);
            if (methodName == "updateRole") {
                var roleType = document.createElement("input");
                roleType.type = "hidden";
                roleType.name = "role";
                roleType.value = paramValue;
                theForm.appendChild(roleType);
            }
            var pageOffset = document.createElement("input");
            pageOffset.type = "hidden";
            pageOffset.name = "pageOffset";
            pageOffset.value = ${fusers.toPage};
            theForm.appendChild(pageOffset);
            theForm.submit();
        }
    </script>
</head>
<body>
<div id="formwrapper">
    <s:form id="uForm" action="user_list" method="post" cssClass="wwFormTable" theme="simple">
        <fieldset>
            <legend>用户查询</legend>
            <div class="radio-div">
                <label for="name">用户名</label>
                <s:textfield name="findParams.username" label="用户名" id="name"/>
            </div>
            <div class="radio-div">
                <label for="nickname">用户昵称</label>
                <s:textfield name="findParams.nickname" label="用户昵称" id="nickname"/>
            </div>
            <div>
                <s:iterator value="allds">
                    <div class="checks">
                        <s:checkbox fieldValue="%{id}" value="%{id in findParams.deps}" name="findParams.deps" id="dep_%{id}"/>
                        <label for="dep_${id}">${name}</label>
                    </div>
                </s:iterator>
            </div>
            <div class="checks radios">
                <s:set value="@doc.enums.Role@NORMAL" name="normal"/>
                <s:set value="@doc.enums.Role@ADMIN" name="admin"/>
                <s:radio name="findParams.role" label="选择权限"
                         list="roleParams"
                         value="findParams.role"
                         listKey="key"
                         listValue="value"/>
            </div>
            <div class="button">
                <input type="button" value="全选" onclick="checkAll(this)"/>
                <input type="button" value="取消全选" onclick="unCheckAll(this)"/>
                <input type="submit" value="查询"/>
                <input type="reset" value="重置"/>
                <div class="clear-float"></div>
            </div>
        </fieldset>
        <s:if test="%{#fusers.allPageNums >= 1}">
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
                <s:iterator value="#fusers.datas">
                    <tr>
                        <td>${id}</td>
                        <td>${username}</td>
                        <td>${password}</td>
                        <td>${nickname}</td>
                        <td>${email}</td>
                        <s:if test="%{role.toString() == 'ADMIN'}">
                            <td><a href="#" onclick="modifyUser(${id}, 'updateRole', '${role}')" class="redColor">管理员</a></td>
                        </s:if>
                        <s:else>
                            <td><a href="#" onclick="modifyUser(${id}, 'updateRole', '${role}')">普通用户</a></td>
                        </s:else>
                        <td>
                                ${dep.name}
                        </td>
                        <td>
                            <a href="/user_updateInput.action?uid=${id}">编辑</a>
                            <a href="#" onclick="modifyUser(${id}, 'deleted', '')">删除</a>
                        </td>
                    </tr>
                </s:iterator>
            </table>
            <s:include value="/inc/pager.jsp">
                <s:param name="formId">uForm</s:param>
                <s:param name="currentPage" value="#fusers.toPage"/>
                <s:param name="allPageNums" value="#fusers.allPageNums"/>
                <s:param name="begin" value="#fusers.begin"/>
                <s:param name="end" value="#fusers.end"/>
            </s:include>
        </s:if>
        <s:else>
            <div class="errorMessage" align="center">
                要查询的用户不存在
            </div>
        </s:else>

    </s:form>
    <%--<s:debug/>--%>
</div>

</body>
</html>
