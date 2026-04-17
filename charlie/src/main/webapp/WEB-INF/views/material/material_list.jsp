<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Material.MaterialDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
</head>
<body>
<%@ include file="/header.jsp" %>
<h1>material list</h1>
<hr>

<table border="1px">
		
		<thead>
			<tr>
				<th>코드</th>
				<th>자재명</th>
				<th>총수량</th>
				<th>단위</th>
				<th>총가격</th>
				<th>섹션구분</th>
				<th>층구분</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ map.list }">
			<tr>
				<td>${ row.code }</td>
				<td><a href="${ row.material_num }">${ row.name }</a></td>
				<td>${ row.total_quantity }</td>
				<td>${ row.unit }</td>
				<td>${ row.total_price }</td>
				<td>${ row.wh_section }</td>
				<td>${ row.floor_level }</td>
			</tr>
		</c:forEach>
		</table>
		
		<hr>
	<%@ include file="/footer.jsp" %>
</body>
</html>