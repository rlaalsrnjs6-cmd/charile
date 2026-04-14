<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Mdm.MdmDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail page</title>
</head>
<body>

	<h1>mdm detail</h1>
	
	${ mdmDTO }
	<hr>
	<a href="mdm?cmd=list">뒤로</a> <br>
	<hr>
	
		관리번호 : ${ mdmDTO.mdm_num } <br>
		코드 : ${ mdmDTO.code } <br>
		이름 : ${ mdmDTO.name } <br>
		수량 : ${ mdmDTO.quantity } <br>
		단위 : ${ mdmDTO.unit } <br>
		타입 : ${ mdmDTO.type } <br>
		가격 : ${ mdmDTO.price } <br>
		입고날짜 : ${ mdmDTO.received_date } 
		<br>
		가용 여부 : 
		<c:if test="${ mdmDTO.can_use eq 'Y'}">가능</c:if>
		<c:if test="${ mdmDTO.can_use eq 'N'}">불가능</c:if>
		
	
	<hr>
	<a href="mdm?cmd=modify&mdm_num=${ mdmDTO.mdm_num }">수정</a>
	<a href="mdm?cmd=delete&mdm_num=${ mdmDTO.mdm_num }">삭제</a>
	
</body>
</html>