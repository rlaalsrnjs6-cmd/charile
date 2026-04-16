<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Mdm.MdmDTO"%> 
<%@ page import="fileLibrary.CommonDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mdm list</title>
</head>
<body>

<%@ include file="/header.jsp" %>

<h1>mdm list</h1>
<hr>
<form action="mdm?cmd=search" method="post">

<select name="search_select">
	<option value="search_all">전체</option>
	<option value="code">코드</option>
	<option value="name">명칭</option>
	<option value="unit">단위</option>
	<option value="type">타입</option>
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
				<th>수량</th>
				<th>단위</th>
				<th>타입</th>
				<th>가격</th>
				<th>입고날짜</th>
				<th>사용기한</th>
				<th>가용 여부</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ map.list }">
			<tr>
				<td>${ row.mdm_num }</td>
				<td>${ row.code }</td>
				<td>
				
					<a href="mdm?cmd=detail&mdm_num=${ row.mdm_num }">
						<c:if test="${ empty row.name }"> Null </c:if>
						<c:if test="${ not empty row.name }"> ${ row.name } </c:if>
					</a>
				</td>



				<td>${ row.quantity }</td>
				<td>${ row.unit }</td>
				<td>${ row.type }</td>
				<td>${ row.price }</td>
				<td>${ row.received_date }</td>
				<td>${ row.exp_date }</td>
				<td>${ row.canUse }</td>
			</tr>
		</c:forEach>
		
		</table>
		
	<jsp:include page="/WEB-INF/views/paging.jsp" />
	
	<hr>
	<a href="${servletName}?cmd=insertPage">등록페이지로</a>
	
	<%@ include file="/footer.jsp" %>
</body>
</html>