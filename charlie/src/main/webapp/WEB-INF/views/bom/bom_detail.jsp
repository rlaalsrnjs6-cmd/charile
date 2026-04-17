<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Bom.BomDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail page</title>
</head>
<body>

	<h1>bom detail</h1>
	<hr>
	<a href="bom?cmd=list">뒤로</a> <br>
	<hr>
		제품넘버 : ${ bomDTO.target_mdm_num } <br>
		해당제품 : ${ bomDTO.target_name } <br>
		제품코드 : ${ bomDTO.target_code } <br><br>
		
		BOM넘버 :  ${ bomDTO.bom_num } <br>
		재료 :  ${ bomDTO.name } <br>
		BOM코드 :  ${ bomDTO.code } <br> <br>
<!-- 		재료타입 :  -->
<%-- 		<c:if test="${ bomDTO.type eq 'material' }"> 원재료 </c:if> --%>
<%-- 		<c:if test="${ bomDTO.type eq 'assemble' }"> 반제품 </c:if><br> --%>
		
		요구량 : ${ bomDTO.required_weight } ${ bomDTO.unit } <br>
		
	
	<hr>
	<a href="bom?cmd=modify&bom_num=${ bomDTO.bom_num }">수정</a>
	<a href="bom?cmd=delete&bom_num=${ bomDTO.bom_num }">삭제</a>
	
</body>
</html>