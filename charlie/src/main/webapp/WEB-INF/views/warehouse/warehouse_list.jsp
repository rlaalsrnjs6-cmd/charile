<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Warehouse.WarehouseDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
</head>
<body>

<h1>warehouse list</h1>


<hr>
<!-- <select> -->
<%-- 	<c:forEach var="join" items="${ joinList }">  --%>
<%-- 		<option>${ join.name }</option> --%>
<%-- 	</c:forEach> --%>
<!-- </select> -->
<!-- <hr> -->

<table border="1px">
		<thead>
			<tr>
				<th>확인날짜</th>
				<th>섹션</th>
				<th>온도</th>
				<th>습도</th>
				<th>층구분</th>
				<th>정상체크</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ map.list }">
			<tr>
				<td>${ row.wh_chk_date }</td>
				<td>
					<a href="warehouse?cmd=detail&warehouse_num=${ row.warehouse_num }">
						${ row.wh_section }
					</a>
				</td>
				<td>${ row.temperature }</td>
				<td>${ row.humidity }</td>
				<td>${ row.floor_level }</td>
				<td>${ row.wh_status_chk }</td>
				
			</tr>
		</c:forEach>
		
		</table>
		
		<hr>
	<a href="warehouse?cmd=insertPage">등록페이지로</a>
</body>
</html>