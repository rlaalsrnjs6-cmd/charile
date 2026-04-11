<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>process modify</title>
</head>
<body>
	<h1>process modify</h1>
	<hr>
	<a href="process?cmd=list">뒤로</a> <br>
	<hr>
	
	<form method="post" action="process">
	
		<input type="hidden" name="cmd" value="update">
		<input type="hidden" name="process_num" value="${ processInfo[0].process_num }">
		
		process_num : ${ processInfo[0].process_num } <br>
		process_content : <input name="process_content" value="${ processInfo[0].process_content }"> <br>
		flow : <input name="flow" value="${ processInfo[0].flow }"> <br>
		img_url : <input name="img_url" value="${ processInfo[0].img_url }"> <br>
		mdm_num : <input name="mdm_num" value="${ processInfo[0].mdm_num }"> <br>
		 <input type="submit" value="수정"> <br>
		
		</form>
		
	
	
</body>
</html>