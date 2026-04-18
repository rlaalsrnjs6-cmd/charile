<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Material.MaterialDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail page</title>
</head>
<body>

	<h1>Material detail</h1>
	<hr>
	<a href="material?cmd=list">뒤로</a> <br>
	<hr>
		<table border="1px">
		
		<thead>
			<tr>
				<th>자재관리번호</th>
				<th>코드</th>
				<th>자재명</th>
				<th>자재량</th>
				<th>자재위치</th>
				<th>층 구분</th>
				<th>기준관리번호</th>
				<th>창고번호</th>
				</tr>
		</thead>
		
		<tr>
			<td>${ materialDTO.material_num }</td>
			<td>${ materialDTO.code }</td>
			<td>${ materialDTO.name }</td>
			<td>${ materialDTO.area_quantity } ${ materialDTO.unit }</td>
			<td>${ materialDTO.wh_section }</td>
			<td>${ materialDTO.floor_level }</td>
			<td>${ materialDTO.mdm_num }</td>
			<td>${ materialDTO.warehouse_num }</td>
		</tr>
		
	</table>	
	
	<hr>
	
	<a href="material?cmd=delete&material_num=${ materialDTO.material_num }">삭제</a>
	
</body>
</html>