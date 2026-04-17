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
	
	기준제품:
    <select name="target_mdm_num">
        <c:forEach var="item" items="${ list }">
        	<c:if test="${ item.type eq 'product' }">
            	<option value="${ item.mdm_num }"> ${ item.name } </option>
        	</c:if>
        </c:forEach>
    </select> <br>
    
    요구 재료: 
    <select name="mdm_num">
        <c:forEach var="item" items="${ list }">
        	<c:if test="${ item.type eq 'assemble' or item.type eq 'material' }">
            	<option value="${ item.mdm_num }">[ ${ item.code } ] : ${ item.name }</option>
        	</c:if>
        </c:forEach>
    </select> <br>
	
	요구량: <input type="text" name="required_weight" value="20000"> <br>
	<input type="hidden" name="cmd" value="insert">
	<input type="submit" value="등록">
	
</form>

	<a href="bom?cmd=list">리스트페이지</a>
</body>
</html>