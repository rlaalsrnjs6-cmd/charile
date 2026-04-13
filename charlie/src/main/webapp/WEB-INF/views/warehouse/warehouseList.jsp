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
<table border=1>
		<tr>
			<th>창고넘버</th>
			<th>섹션</th>
			<th>층구분</th>
			<th>온도</th>
			<th>습도</th>
			<th>정상체크</th>
		</tr>
		
		<c:forEach var="w" items="${warehouse}">
			<tr>
				<td>${w.warehouse_num}</td>
				<td><a href="http://localhost:8080/charlie/warehouse?warehouse_num=${w.warehouse_num}&mod=detail">${w.section}</td>
				<td>${w.floor_level}</td>
				<td>${w.temperature}</td>
				<td>${w.humidity}</td>
				<td>${w.wh_status_chk}</td>
			</tr>
		</c:forEach>
	</table>
	<a href = "http://localhost:8080/charlie/warehouse?mod=add">작성</a>
</body>
</html>