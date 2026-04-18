 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Material.MaterialDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert page</title>
</head>
<body>
<h1>material insert</h1>
<hr>

<form method="post" action="material">

	
	섹션/층구분 :
	<select name="warehouse_num">
		<c:forEach var="item" items="${map.whList}">
			<option value="${item.warehouse_num}" selected>
          		  ${item.wh_section} / ${item.floor_level}
       	 	 </option>
		</c:forEach>
    </select>
    <br>
    
	품목 : 
	<select name="mdm_num">
		<c:forEach var="item" items="${map.mdmList}">
			<option value="${item.mdm_num}" selected>
          		  ${item.code} / ${item.name}
       	 	 </option>
		</c:forEach>
    </select>
	<br>
	<input type="hidden" name="cmd" value="insert">
	<input type="submit" value="등록">
	
</form>
	<a href="material?cmd=list">리스트페이지</a>
</body>
</html>