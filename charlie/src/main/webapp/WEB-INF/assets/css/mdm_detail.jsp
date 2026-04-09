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

	<h1>detail page</h1>
	<hr>
	<a href="mdm?cmd=list">뒤로</a> <br>
	<hr>
	<c:forEach var="row" items="${ mdmInfo }">
	
		관리번호 : ${ row.mdm_num } <br>
		코드 : ${ row.code } <br>
		이름 : ${ row.name } <br>
		단위 : ${ row.unit } <br>
		타입 : ${ row.type } <br>
		가격 : ${ row.price } <br>
		
	</c:forEach>
	
	<hr>
	<a href="mdm?cmd=modify&mdm_num=${ mdmInfo.get(0).mdm_num }">수정</a>
	<a href="mdm?cmd=delete&mdm_num=${ mdmInfo.get(0).mdm_num }">삭제</a>
	
</body>
</html>