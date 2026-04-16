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
	
		process_num : ${ processDTO.process_num } <br>
		name : ${ processDTO.name } <br>
		code : ${ processDTO.code } <br>
		img : <img src="${ processDTO.img_url }"> <br>
		process_content : ${ processDTO.process_content } <br>
		flow : ${ processDTO.flow } <br>
		mdm_num : ${ processDTO.mdm_num } <br>
		
	
	<hr>
	<a href="process?cmd=modify&process_num=${ processDTO.process_num }">수정</a>
	<a href="process?cmd=delete&process_num=${ processDTO.process_num }">삭제</a>
	
</body>
</html>