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
	
	${ bomInfo[0] }
	<form method="post" action="bom">
	
		<input type="hidden" name="cmd" value="update">
		<input type="hidden" name="bom_num" value="${ bomInfo[0].bom_num }">
		
		bom번호 : ${ bomInfo[0].bom_num } <br>
		요구량 : <input name="required_weight" value="${ bomInfo[0].required_weight }"> <br>
		이름 : <input name="mdm_num" value="${ bomInfo[0].mdm_num }"> <br>
		 <input type="submit" value="수정"> <br>
		
		</form>
		
	
	
</body>
</html>