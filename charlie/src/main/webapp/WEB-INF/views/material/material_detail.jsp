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
	<c:forEach var="row" items="${ materialInfo }">
	
<%-- 		material_num : ${ row.material_num } <br> --%>
<%-- 		material_type : ${ row.total_quantity } <br> --%>
<%-- 		material_status : ${ row.warehouse_num } <br> --%>
<%-- 		mdm_num : ${ row.mdm_num } <br> --%>
		
	</c:forEach>
	
	<hr>
	<a href="material?cmd=modify&material_num=${ materialInfo[0].material_num }">수정</a>
	<a href="material?cmd=delete&material_num=${ materialInfo[0].material_num }">삭제</a>
	
</body>
</html>