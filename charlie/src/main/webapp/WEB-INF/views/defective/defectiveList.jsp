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
			<th>불량번호</th>
			<th>불량카테고리</th>
			<th>개수</th>
			<th>불량조치방법</th>
			<th>로트번호</th>
		</tr>
		${map.list}
		<c:forEach var="d" items="${map.list}">
			<tr>
				<td>${d.defective_num}</td>
				<td><a href="http://localhost:8080/charlie/defective?defective_num=${d.defective_num}&mod=detail">${d.category}</td>
				<td>${d.count}</td>
				<td>${d.action}</td>
				<td>${d.lot_num}</td>
			</tr>
		</c:forEach>
	</table>
	<a href = "http://localhost:8080/charlie/defective?mod=add">작성</a>
	<jsp:include page="/WEB-INF/views/paging.jsp" />
	<%@ include file="/footer.jsp" %>
</body>
</html>