<%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/5/21
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>信息详情</title>
    <style>
        #formwrapper {
            margin: 5px auto;
            padding: 10px;
            width: 780px;
        }

        #formwrapper fieldset {
            width: inherit;
        }

        .title {
            text-align: center;
            font-size: 14px;
        }
    </style>
    <script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        var editor = CKEDITOR.inline( 'editable', {
            removePlugins: 'toolbar'
        } );
    </script>
</head>
<body>
<div id="formwrapper">
    <s:form action="msg_add" method="post" enctype="multipart/form-data"
            theme="simple">
        <fieldset>
            <legend>信息详情</legend>
            <div class="title">
                <strong><s:text name="cMsg.title"/></strong>
            </div>
            <s:if test="%{#type == 'send'}">
                <div class="sendtousers">
                    <span>收信人:</span>
                    <s:iterator value="cMsg.receives" status="rowStatus">
                    <span>
                        <s:property value="user.nickname"/>
                        [<s:property value="user.dep.name"/>]
                        <s:if test="!#rowStatus.last">, </s:if>
                    </span>
                    </s:iterator>
                </div>
            </s:if>
            <s:else>
                <div class="sendtousers">
                    <span>发信人:</span>
                    <span>${cMsg.author.nickname}[${cMsg.author.dep.name}]</span>
                </div>
            </s:else>
            <s:if test="cMsg.attachments.size() > 0">
                <div>
                    <span>附件</span>
                    <s:iterator value="cMsg.attachments">
                        [<a href="<%=request.getContextPath()%>/upload/attachments/${newName}"><s:property value="oldName"/></a>]
                        &nbsp;
                    </s:iterator>
                </div>
            </s:if>
            <div class="content">
                <s:textarea name="cMsg.content" id="mytextarea" disabled="true"/>
                <script>
                    CKEDITOR.replace('mytextarea');
                </script>
            </div>
        </fieldset>
    </s:form>
</div>

</body>
</html>
