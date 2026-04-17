<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Bom.BomDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bom modify</title>
</head>
<body>
	<h1>bom modify</h1>
	<hr>
	<a href="bom?cmd=list">뒤로</a> <br>
	<hr>
	
	<form method="post" action="bom">
	
		<input type="hidden" name="cmd" value="update">
		<input type="hidden" name="bom_num" value="${ bomDTO.bom_num }">
		<input type="hidden" name="mdm_num" value="${ bomDTO.mdm_num }">
		<input type="hidden" name="target_mdm_num" value="${ bomDTO.target_mdm_num }">
		
    	재료명 : ${ bomDTO.name } <br>
    	재료코드 : ${ bomDTO.code } <br>
		
		BOM번호 : ${ bomDTO.bom_num } <br>
		요구량 : <input name="required_weight" value="${ bomDTO.required_weight }">${ bomDTO.unit } <br>
		요구 제품 : ${ bomDTO.target_name } <br>
		
		 <input type="submit" value="수정"> <br>
		</form>
		
	
	
</body>
</html>