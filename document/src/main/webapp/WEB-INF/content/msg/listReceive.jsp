<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/15
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>已收邮件列表</title>
    <script type="text/javascript">
        function deleteReceive(mid) {
            var theForm = document.getElementById("mForm");
            var actionStr = "msg_deleteReceive.action";
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
    <style>
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
</head>
<body>
<div id="formwrapper">
    <s:form id="mForm" action="msg_listReceive" method="post" theme="simple">
        <fieldset>
            <legend>信息查询</legend>
            <div>
                <label for="fromuser">发信人</label>
                <s:textfield name="findParams.fromuser" id="fromuser"/>
            </div>
            <div>
                <label for="cons">信息内容</label>
                <s:textfield name="findParams.cons" id="cons"/>
            </div>
            <div>
                <label for="attach">附件标题</label>
                <s:textfield name="findParams.attach" id="attach"/>
            </div>
            <div class="checks radios">
                <s:radio name="findParams.read" label="选择邮件类型"
                         list="readParams"
                         value="findParams.read"
                         listKey="key"
                         listValue="value"/>
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
                    <th>发件人</th>
                    <th>发送时间</th>
                    <th>操作</th>
                </tr>
                <s:iterator value="#mpager.datas">
                    <tr>
                        <td>${id}</td>
                        <s:set var="msgid" value="%{id}" />
                        <td>
                            <s:iterator value="receives">
                                <s:if test="read == true">
                                    <a href="/msg_loadReceive.action?mid=${msgid}">${title}</a>
                                </s:if>
                                <s:else>
                                    <strong><a href="/msg_loadReceive.action?mid=${msgid}">${title}</a></strong>
                                </s:else>
                            </s:iterator>
                        </td>
                        <td>${author.nickname}[${author.dep.name}]</td>
                        <td><s:date name="createDate"
                                    format="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                            <a href="#" onclick="deleteReceive(${id})">删除</a>
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