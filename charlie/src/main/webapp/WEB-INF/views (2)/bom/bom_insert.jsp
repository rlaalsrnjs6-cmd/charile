<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Bom.BomDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert page</title>
</head>
<body>
<h1>bom insert</h1>
<hr>

<form method="post" action="bom">

	요구량: <input type="text" name="required_weight" value="20000"> <br>
	
	기준번호:
	
    <select name="mdm_num">
        <c:forEach var="item" items="${ list }">
            <option value="${ item.mdm_num }"> [품명: ${ item.name }] / [코드: ${ item.code }] / [단위 : ${ item.unit }]</option>
        </c:forEach>
    </select> <br>
	
	<input type="hidden" name="cmd" value="insert">
	<input type="submit" value="등록">
	
</form>
	<a href="bom?cmd=list">리스트페이지</a>
</body>
</html>