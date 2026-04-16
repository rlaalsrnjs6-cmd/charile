<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Machinery.MachineryDTO"%> 
<%@ page import="fileLibrary.CommonDTO"%> 

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
	
		machinery_num : ${ machineryDTO.machinery_num } <br>
		machinery_type : ${ machineryDTO.machinery_type } <br>
		machinery_status : ${ machineryDTO.machinery_status } <br>
		error_sign : ${ machineryDTO.error_sign } <br>
		m_action : ${ machineryDTO.m_action } <br>
		m_log_time : ${ machineryDTO.m_log_time } <br>
		mdm_num : ${ machineryDTO.mdm_num } <br>
		
	
	<hr>
	<a href="machinery?cmd=modify&machinery_num=${ machineryDTO.machinery_num }">수정</a>
	<a href="machinery?cmd=delete&machinery_num=${ machineryDTO.machinery_num }">삭제</a>
	
</body>
</html>