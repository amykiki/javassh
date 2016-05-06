<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/6
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>设置发文部门</title>
    <script type="text/javascript">
        function selectAllRight() {
            var list = document.getElementById("rightIds");
            for (var i = 1; i < list.options.length; i++) {
                list.options[i].selected = true;
            }
        }
    </script>
</head>
<body>
部门名称：<s:text name="dep.name"/>
<s:form action="dep_updateScope" method="post">
    <s:optiontransferselect
            list="#nods"
            listKey="id" listValue="name"
            name="noids"
            headerKey="headerKey"
            headerValue="---未选择的可发文部门---"
            multiple="true"
            cssStyle="width:180px;height:250px;"
            doubleList="#ds"
            doubleListKey="id" doubleListValue="name"
            doubleName="dscopeIds"
            doubleId="rightIds"
            doubleHeaderKey="doubleHeaderKey"
            doubleHeaderValue="---已选择的可发文部门---"
            doubleMultiple="true"
            doubleCssStyle="width:180px;height:250px;"
            addToLeftLabel="移除部门"
            addToRightLabel="添加部门"
            addAllToLeftLabel="移除全部部门"
            addAllToRightLabel="添加全部部门"
            allowSelectAll="false"
    />
    <s:hidden name="dep.id"/>
    <s:submit value="设置发文部门" onclick="selectAllRight()"/>
</s:form>

</body>
</html>
