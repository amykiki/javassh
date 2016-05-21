<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/15
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>已发邮件列表</title>
    <script type="text/javascript">
        function deleteSend(mid) {
            var theForm = document.getElementById("mForm");
            var actionStr = "msg_deleteSend.action";
            theForm.action = actionStr;
            var delId = document.createElement("input");
            delId.type = "hidden";
            delId.name = "mid";
            delId.value = mid;
            theForm.appendChild(delId);

            var pageOffset = document.createElement("input");
            pageOffset.type = "hidden";
            pageOffset.name = "pageOffset";
            pageOffset.value = ${mpager.toPage};
            theForm.appendChild(pageOffset);
            theForm.submit();
        }
    </script>
</head>
<body>
<div id="formwrapper">
    <s:form id="mForm" action="msg_listSend" method="post" theme="simple">
        <fieldset>
            <legend>信息查询</legend>
            <div>
                <label for="touser">收信人</label>
                <s:textfield name="findParams.touser" id="touser"/>
            </div>
            <div>
                <label for="cons">信息内容</label>
                <s:textfield name="findParams.cons" id="cons"/>
            </div>
            <div>
                <label for="attach">附件标题</label>
                <s:textfield name="findParams.attach" id="attach"/>
            </div>
            <div class="button">
                <input type="submit" value="查询"/>
                <input type="reset" value="重置"/>
                <div class="clear-float"></div>
            </div>
        </fieldset>

        <s:if test="%{#mpager.allPageNums >= 1}">
                <table class="list">
                    <tr>
                        <th>消息id</th>
                        <th>消息标题</th>
                        <th>发送时间</th>
                        <th>操作</th>
                    </tr>
                    <s:iterator value="#mpager.datas">
                        <tr>
                            <td>${id}</td>
                            <td><a href="/msg_loadSend.action?mid=${id}">${title}</a></td>
                            <td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>
                                <a href="#" onclick="deleteSend(${id})">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
                <s:include value="/inc/pager.jsp">
                    <s:param name="formId">mForm</s:param>
                    <s:param name="currentPage" value="#mpager.toPage"/>
                    <s:param name="allPageNums" value="#mpager.allPageNums"/>
                    <s:param name="begin" value="#mpager.begin"/>
                    <s:param name="end" value="#mpager.end"/>
                </s:include>
            </s:if>
    </s:form>
</div>

</body>
</html>
