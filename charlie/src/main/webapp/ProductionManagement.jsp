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

	<c:forEach var="i" items="${map.List1}">
	<tr>
		<td><c:if test="${i.target_quantity - i.currentCount == 0 }">완료</c:if>
		<c:if test="${i.target_quantity - i.currentCount != 0 }">진행중</c:if></td></td>
		<td><a href="${pageContext.request.contextPath}/PMDetailServlet?prod_num=${i.prod_num}">${i.title }</a></td>
		<td>목표: ${i.target_quantity}<br>
		실적: ${i.currentCount }
		/ 달성률: 
    <fmt:formatNumber value='${(i.currentCount * 100.0) / i.target_quantity}' pattern='0.0' />%
	</tr>
	</c:forEach>
	</table>
	
		<div>
	<c:if test="${map.startPage > 1}">
		<a href="./management?page=${map.startPage - 1}">[이전]</a>
	</c:if>

	<c:forEach var="p" begin="${map.startPage}" end="${map.endPage}">
		<a href="./management?page=${p}" class="${p == map.currentPage ? 'active' : ''}">[${p}]</a>
	</c:forEach>

	<c:if test="${map.endPage < map.totalPage}">
		<a href="./management?page=${map.endPage + 1}">[다음]</a>
	</c:if>
</div>

<a href="/charlie/ProductionManagementInsert.jsp">작성하기</a>

<!-- pageAll 닫기 div -->
</div>

<%@ include file="footer.jsp" %>
</body>
</html>