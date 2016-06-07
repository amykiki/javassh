 <%--
  Created by IntelliJ IDEA.
  User: Amysue
  Date: 2016/3/24
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/pager.css">
<script type="text/javascript">
    function submitPage(toPage) {
        var pageOffset = document.createElement("input");
        pageOffset.type = "hidden";
        pageOffset.name = "pageNum";
        pageOffset.value = toPage;
        var theForm = document.getElementById('${param.formId}');
        theForm.appendChild(pageOffset);
        theForm.submit();
    }
</script>
<fmt:parseNumber var="currentPage" type="number" value="${param.currentPage}" />
<fmt:parseNumber var="allPageNums" type="number" value="${param.allPageNums}" />
 <c:if test="${allPageNums >= 1}">
     <ul class="tsc_pagination tsc_paginationA tsc_paginationA05">
         <li><a href="#" onclick="submitPage(1)">首页</a></li>
         <c:if test="${currentPage > 1}">
             <li><a href="#" onclick="submitPage(${currentPage-1})">前一页</a></li>
         </c:if>
         <c:forEach var="i" begin="${param.begin}" end="${param.end}">
             <c:choose>
                 <c:when test="${i == currentPage}">
                     <li><a href="#" class="current">${i}</a></li>
                 </c:when>
                 <c:otherwise>
                     <li><a href="#" onclick="submitPage(${i})">${i}</a></li>
                 </c:otherwise>
             </c:choose>
         </c:forEach>
         <c:if test="${currentPage < allPageNums}">
             <li><a href="#" onclick="submitPage(${currentPage+1})">下一页</a></li>
         </c:if>
         <li><a href="#" onclick="submitPage(${allPageNums})">尾页</a></li>
     </ul>
 </c:if>

