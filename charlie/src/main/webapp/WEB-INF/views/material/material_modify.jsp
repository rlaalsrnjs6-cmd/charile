<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Material.MaterialDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>material modify</title>
</head>
<body>
	<h1>material modify</h1>
	<hr>
	<a href="material?cmd=list">뒤로</a> <br>
	<hr>
	<form method="post" action="material">
	
		<input type="hidden" name="cmd" value="update">
		<input type="hidden" name="material_num" value="${ materialDTO.material_num }">
		<input type="hidden" name="mdm_num" value="${ materialDTO.mdm_num }">
		<input type="hidden" name="warehouse_num" value="${ materialDTO.warehouse_num }">
		
		자재관리번호 : ${ materialDTO.material_num } <br>
		
		이름 : ${ materialDTO.name } <br>
		코드 : ${ materialDTO.code } <br>
		
		섹션 : ${ materialDTO.wh_section } <br>
		재고량 : <input name="area_quantity" value="${ materialDTO.area_quantity }"> ${ materialDTO.unit } <br>
		
				
		 <input type="submit" value="수정"> <br>
		
		</form>
		
	
	
</body>
</html>