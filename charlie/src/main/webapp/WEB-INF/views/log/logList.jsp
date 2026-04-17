<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <%@ include file="/header.jsp" %> --%>
<table border=1>
		<tr>
			<th>log_번호</th>
			<th>입출고날짜</th>
			<th>구분</th>
			<th>lot번호</th>
		</tr>
		
		<c:forEach var="l" items="${map.list}">
			<tr>
				<td>${l.log_num}</td>
				<td><a href="http://localhost:8080/charlie/log?log_num=${l.log_num}&mod=detail">${l.io_time}</td>
				<td>${l.io_type}</td>
				<td>${l.lot_num}</td>
			</tr>
		</c:forEach>
	</table>
	<a href = "http://localhost:8080/charlie/log?mod=add">작성</a>
	<jsp:include page="/WEB-INF/views/paging.jsp" />
<%-- 	<%@ include file="/footer.jsp" %> --%>
</body>
</html>