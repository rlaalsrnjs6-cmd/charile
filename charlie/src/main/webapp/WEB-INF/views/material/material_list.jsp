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
mdm 정보 가져오기
<hr>

<table border="1px">
		<thead>
			<tr>
				<th>material_num</th>
				<th>total_quantity</th>
				<th>warehouse_num</th>
				<th>mdm_num</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ list }">
			<tr>
			
				<td>
					<a href="material?cmd=detail&material_num=${ row.material_num }">
						${ row.material_num }
					</a>
				</td>
				<td>${ row.total_quantity }</td>
				<td>${ row.warehouse_num }</td>
				<td>${ row.mdm_num }</td>
			</tr>
		</c:forEach>
		
		</table>
		
		<hr>
	<a href="material?cmd=insertPage">등록페이지로</a>
	<%@ include file="/footer.jsp" %>
</body>
</html>