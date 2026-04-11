<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Machinery.MachineryDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail page</title>
</head>
<body>

	<h1>Machinery detail</h1>
	<hr>
	<a href="machinery?cmd=list">뒤로</a> <br>
	<hr>
	<c:forEach var="row" items="${ machineryInfo }">
	
		machinery_num : ${ row.machinery_num } <br>
		machinery_type : ${ row.machinery_type } <br>
		machinery_status : ${ row.machinery_status } <br>
		error_sign : ${ row.error_sign } <br>
		m_action : ${ row.m_action } <br>
		m_log_time : ${ row.m_log_time } <br>
		mdm_num : ${ row.mdm_num } <br>
		
	</c:forEach>
	
	<hr>
	<a href="machinery?cmd=modify&machinery_num=${ machineryInfo[0].machinery_num }">수정</a>
	<a href="machinery?cmd=delete&machinery_num=${ machineryInfo[0].machinery_num }">삭제</a>
	
</body>
</html>