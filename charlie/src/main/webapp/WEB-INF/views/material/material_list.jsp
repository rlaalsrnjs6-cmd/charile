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

<form action="material?cmd=search" method="post">
    <select name="selectName">
		<option value="" selected> 타입선택 </option>
		<c:forEach var="item" items="${ map.select1 }"> 
			<option>${ item.type }</option>
		</c:forEach>
	</select>
	
	<input type="submit" value="검색">
</form>

<table border="1px">
		
		<thead>
			<tr>
				<th>자재관리번호</th>
				<th>코드</th>
				<th>자재명</th>
				<th>총량</th>
				<th>가격</th>
<!-- 				<th>창고 넘버</th> -->
				<th>자재위치</th>
				<th>층 구분</th>
<!-- 				<th>기준관리 넘버</th> -->
			</tr>
		</thead>

		<c:forEach var="row" items="${ map.list }">
			<tr>
				<td>${ row.material_num }</td>
				<td>${ row.code }</td>
				<td>
					<a href="material?cmd=modify&material_num=${ row.material_num }">
						${ row.name }
					</a>
				</td>
				<td>${ row.total_quantity }${ row.unit }</td>
				<td>${ row.total_price }</td>
<%-- 				<td>${ row.warehouse_num }</td> --%>
				<td>${ row.wh_section }</td>
				<td>${ row.floor_level }</td>
<%-- 				<td>${ row.mdm_num }</td> --%>
			</tr>
		</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/views/paging.jsp" />
		<a href="material?cmd=insertPage">등록페이지로</a>
		
		<hr>
	<%@ include file="/footer.jsp" %>
</body>
</html>