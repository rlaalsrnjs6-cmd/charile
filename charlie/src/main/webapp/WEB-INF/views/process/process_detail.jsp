<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail page</title>
</head>
<body>

	<h1>Process detail</h1>
	<hr>
	<a href="process?cmd=list">뒤로</a> <br>
	<hr>
	<c:forEach var="row" items="${ processInfo }">
	
		process_num : ${ row.process_num } <br>
		process_content : ${ row.process_content } <br>
		flow : ${ row.flow } <br>
		img_url : ${ row.img_url } <br>
		mdm_num : ${ row.mdm_num } <br>
		
	</c:forEach>
	
	<hr>
	<a href="process?cmd=modify&process_num=${ processInfo[0].process_num }">수정</a>
	<a href="process?cmd=delete&process_num=${ processInfo[0].process_num }">삭제</a>
	
</body>
</html>