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
	<c:forEach var="row" items="${ bomInfo }">
	
		관리번호 : ${ row.bom_num } <br>
		요구량 : ${ row.required_weight } <br>
		이름 : ${ row.mdm_num } <br>
		
	</c:forEach>
	
	<hr>
	<a href="bom?cmd=modify&bom_num=${ bomInfo.get(0).bom_num }">수정</a>
	<a href="bom?cmd=delete&bom_num=${ bomInfo.get(0).bom_num }">삭제</a>
	
</body>
</html>