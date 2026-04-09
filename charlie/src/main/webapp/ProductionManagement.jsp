<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;"); %>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./assets/css/ProductionManagement.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div id="pageAll">
	<table border="1">
	<tr>
	<th>상태</th>
	<th>제목</th>
	<th>진행 현황</th>
	</tr>
	<c:forEach var="i" items="${map.List1}" varStatus="loop">
	<tr>
		<td><c:if test="${i.target_quantity - map.List2[loop.index].currentCount ==0 }">완료</c:if>
		<c:if test="${i.target_quantity - map.List2[loop.index].currentCount != 0 }">진행중</c:if></td></td>
		<td>${i.title }</td>
		<td>목표: ${i.target_quantity}<br>
		실적: ${map.List2[loop.index].currentCount }
		/ 달성률: 
    <fmt:formatNumber value="${(map.List2[loop.index].currentCount * 100.0) / i.target_quantity}" pattern="0.0" />%
	</tr>
	</c:forEach>
	
	
	
	
	
<%-- 	<c:forEach var="i" items="${map.List2}"> --%>
<!-- 	<tr> -->
<%-- 	<c:if test="${i.key == 'map.List2'}"> --%>
<!-- 	<td>/보류/</td> -->
<%-- 	</c:if> --%>
	
<%-- 	<c:if test="${i.key == 'List1'}"> --%>
<%-- 		<td>${i.title}</td> --%>
<%-- 	</c:if> --%>
	
<%-- 	<c:if test="${i.key == 'List2'}">	 --%>
<!-- 		<td>/보류/</td> -->
<%-- 	</c:if> --%>
<!-- 	</tr> -->
<%-- 	</c:forEach> --%>
	</table>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>