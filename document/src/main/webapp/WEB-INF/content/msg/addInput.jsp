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
            float: none;
            width: inherit;
        }
        .content textarea {
            height: 350px;
            width: 758px;
            overflow-x: hidden;
            overflow-y: scroll;
        }
        .checks {
            width: 80px;
            display: inline-block;
        }
        .checkLists {
            width: inherit;
            margin: 0 auto;
            max-height: 200px;
            overflow-x: hidden;
            overflow-y: scroll;
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
            width: 60px;
        }
        #formwrapper {
            margin: 5px auto;
            padding: 10px;
            width: 780px;
        }
        #formwrapper fieldset {
            width: inherit;
        }
        .sendtousers {
            border: 1px solid #a4cdf2;
            padding: 1px 4px;
        }
        .title label {
            text-align: left;
            width: 25px;
        }
        .title input {
            width: 350px;
        }
        .uploadInput {
            width: 350px;
        }
    </style>
    <script type="text/javascript">
        var fileIndex = 0;
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

        function addAttachs() {
            var ac = document.getElementById("attachContainer");
            var fileId = "uploadfile" + (fileIndex++);
            var fileObj = document.createElement("input");
            fileObj.type = "file";
            fileObj.name = "atts";
            fileObj.setAttribute("class", "uploadInput");
            var delObj = document.createElement("input");
            delObj.type = "button";
            delObj.value = "移除";
            delObj.setAttribute("onclick", "removeAttach(this)");
            var divObj = document.createElement("div");
            divObj.id = fileId;
            divObj.appendChild(fileObj);
            divObj.appendChild(delObj);
            ac.appendChild(divObj);
//            var node = "<div><input type='file' name='atts' value='选择文件'/><input type='button' value='移除' onclick='removeAttach(this)'></div>";
//            ac.innerHTML = ac.innerHTML + node;
        }
        function removeAttach(obj) {
            var ac = document.getElementById("attachContainer");
            ac.removeChild(obj.parentNode);
        }
    </script>
    <script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
</head>
<body>
<s:actionerror/>
<div id="formwrapper">
    <s:form action="msg_add" method="post" enctype="multipart/form-data" theme="simple">
        <fieldset>
            <legend>发送信件</legend>
            <div class="title">
                <label>标题</label>
                <s:textfield name="cMsg.title"/>
                <s:fielderror><s:param>cMsg.title</s:param></s:fielderror>
            </div>
            <div class="sendtousers">
                <span>收信人</span>
                <s:fielderror><s:param>sendToIds</s:param></s:fielderror>
                <s:if test="#susers.size() <=0">
                    <span>没有可以发送的用户</span>
                </s:if>
                <s:else>
                    <div class="checkLists">
                        <s:set value="" name="depName"/>
                        <s:iterator value="#susers">
                            <s:if test="#depName != dep.name">
                                <s:set value="dep.name" name="depName"/>
                                <h3>${dep.name}</h3>
                            </s:if>
                            <div class="checks">
                                <s:checkbox fieldValue="%{id}" name="sendToIds" value="%{id in sendToIds}" id="sendTo_%{id}"/>
                                <label for="sendTo_${id}">${nickname}</label>
                            </div>
                        </s:iterator>
                    </div>
                    <div class="button">
                            <input type="button" value="全选" onclick="checkAll(this)"/>
                            <input type="button" value="取消全选" onclick="unCheckAll(this)"/>
                        <div class="clear-float"></div>
                    </div>
                </s:else>
            </div>
            <div>
                <input type="button" value="添加附件" id="addAttach" name="file" onclick="addAttachs()">
                <s:fielderror><s:param>file</s:param></s:fielderror>
                <div id="attachContainer">
                </div>
            </div>
            <div>正文</div>
            <div class="content">
                <s:textarea name="cMsg.content" id="mytextarea"/>
                <script>
                    CKEDITOR.replace('mytextarea');
                </script>
            </div>
            <s:if test="#susers.size() > 0">
                <div class="button">
                    <input type="submit" value="添加"/>
                    <input type="reset" value="重置"/>
                    <div class="clear-float"></div>
                </div>
            </s:if>
            <%--<s:debug/>--%>
        </fieldset>
    </s:form>
</div>

</body>
</html>
