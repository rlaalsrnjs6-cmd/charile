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
<%@ include file="/header.jsp" %>
<table border=1>
		<tr>
			<th>lot번호</th>
			<th>lot당제품개수</th>
			<th>작업지시번호</th>
			<th>품질체크전후번호</th>
			<th>자재관리번호</th>
		</tr>
		
		<c:forEach var="l" items="${map.list}">
			<tr>
				<td>${l.lot_num}</td>
				<td><a href="http://localhost:8080/charlie/lot?lot_num=${l.lot_num}&mod=detail">${l.lot_count}</td>
				<td>${l.order_num}</td>
				<td>${l.qc_chk}</td>
				<td>${l.material_num}</td>
			</tr>
		</c:forEach>
	</table>
	<a href = "http://localhost:8080/charlie/lot?mod=add">작성</a>
	<jsp:include page="/WEB-INF/views/paging.jsp" />
	<%@ include file="/footer.jsp" %>
</body>
</html>