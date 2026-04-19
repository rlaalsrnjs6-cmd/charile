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
<form action="warehouse?cmd=search" method="post">
    <select name="selectName">
		<option value="" selected> 전체보기 </option>
		<c:forEach var="item" items="${ map.select1 }"> 
			<option>${ item.wh_section }</option>
		</c:forEach>
	</select>
	
    <select name="selectChk">
    	<option value="" selected> 확인상태 </option>
		<c:forEach var="item" items="${ map.select2 }">  
			<option>${ item.wh_status_chk }</option>
		</c:forEach>
	</select>
	
	<input type="submit" value="검색">
</form>
<hr>

<table border="1px">
		<thead>
			<tr>
				<th>확인날짜</th>
				<th>섹션</th>
				<th>온도</th>
				<th>습도</th>
				<th>층구분</th>
				<th>확인</th>
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
		
		<jsp:include page="/WEB-INF/views/paging.jsp" />
		
		<hr>
	<a href="warehouse?cmd=insertPage">등록페이지로</a>
	
	<%@ include file="/footer.jsp" %>
</body>
</html>