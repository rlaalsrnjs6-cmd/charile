<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Mdm.MdmDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mdm modify</title>
</head>
<body>
	<h1>mdm modify</h1>
	<hr>
	<a href="mdm?cmd=list">뒤로</a> <br>
	<hr>
	
	<form method="post" action="mdm">
	
		<input type="hidden" name="mdm_num" value="${ mdmInfo.get(0).mdm_num }">
		<input type="hidden" name="cmd" value="update">
		
		관리번호 : ${ mdmInfo.get(0).mdm_num } <br>
		코드 : <input name="code" value="${ mdmInfo.get(0).code }"> <br>
		이름 : <input name="name" value="${ mdmInfo.get(0).name }"> <br>
		단위 : <select name="unit">
					<option value="g" >g</option>
					<c:if test="${ mdmInfo.get(0).mdm_num eq g}"> 
						<option value="g" selected>g</option> 
					</c:if>
					
					<option value="L" >L</option>
					<c:if test="${ mdmInfo.get(0).mdm_num eq L}"> 
						<option value="L" selected>L</option> 
					</c:if>
					
					<option value="ea">정</option>
					<c:if test="${ mdmInfo.get(0).mdm_num eq ea}"> 
						<option value="정" selected>ea</option> 
					</c:if>
				</select> <br>
		타입 : <input name="type" value="${ mdmInfo.get(0).type }"> <br>
		가격 : <input name="price" value="${ mdmInfo.get(0).price }"> <br>
		<hr>
		 <input type="submit" value="수정"> <br>
		
		</form>
		
	
	
</body>
</html>