<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Mdm.MdmDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
</head>
<body>

<h1>mdm 리스트</h1>
<hr>



<form action="mdm?cmd=search" method="post">

<select name="search_select">
	<option value="search1">전체</option>
	<option value="search2">코드</option>
	<option value="search3">명칭</option>
	<option value="search4">단위</option>
	<option value="search5">타입</option>
</select>

	<input name="search_content">
	<input type="submit" value="검색">
</form>
<hr>

<table border="1px">
		<thead>
			<tr>
				<th>관리번호</th>
				<th>관리코드</th>
				<th>명칭</th>
				<th>단위</th>
				<th>타입</th>
				<th>가격</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ list }">
			<tr>
				<td>${ row.mdm_num }</td>
				<td>${ row.code }</td>
				<td>
				
					<a href="mdm?cmd=detail&mdm_num=${ row.mdm_num }">
						<c:if test="${ empty row.name }"> Null </c:if>
						<c:if test="${ not empty row.name }"> ${ row.name } </c:if>
					</a>
				</td>
				<td>${ row.unit }</td>
				<td>${ row.type }</td>
				<td>${ row.price }</td>
			</tr>
		</c:forEach>
		
		</table>
		
		<hr>
	<a href="mdm?cmd=insertPage">등록페이지로</a>
</body>
</html>