<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/14
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>发送信件</title>
    <style>
        .content {
            height: 90px;
            width: 300px;
        }
    </style>
</head>
<body>
<div id="formwrapper">
    <s:form action="msg_add" method="post" enctype="multipart/form-data" theme="simple">
        <fieldset>
            <legend>发送信件</legend>
            <div>
                <label>标题</label>
                <s:textfield name="cMsg.title"/>
            </div>
            <div>
                <div>收信人</div>
                <s:iterator value="#susers">
                    <s:checkbox fieldValue="%{id}" name="sendToIds" id="sendTo_%{id}"/>
                    <label for="sendTo_%{id}">${nickname}</label>
                </s:iterator>
            </div>
            <div class="content">

            </div>
        </fieldset>
    </s:form>
</div>

</body>
</html>
