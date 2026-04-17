<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Bom.BomDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
</head>
<body>

<h1>bom list</h1>
<hr>
select JS로 해당 제품의 필요 BOM 보여주기

<form action="bom?cmd=search" method="post">

<select name="search_select">
	<option value="search_all">전체</option>
	<option value="code">코드</option>
	<option value="name">명칭</option>
</select>

	<input name="search_content">
	<input type="submit" value="검색">
</form>


<form action="bom?cmd=search" method="post">

<table border="1px">
		<thead>
			<tr>
				
<!-- 				<th>bom번호</th> -->
				<th>재료명</th>
				<th>재료코드</th>
				<th>요구량</th>
				<th>단위</th>
<!-- 				<th>기준번호</th> -->
				<th>해당제품</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ map.list }">
			<tr>
<%-- 				<td>${ row.bom_num }</td> --%>
				<td><a href="bom?cmd=detail&bom_num=${ row.bom_num }">
					${ row.name }
				</a></td>
				<td>${ row.code }</td>
				<td>${ row.required_weight }</td>
				<td>${ row.unit }</td>
				<td>${ row.target_name }</td>
<%-- 				<td>${ row.mdm_num }</td> --%>
				
			</tr>
		</c:forEach>
		
		</table>
		
		<hr>
	<a href="bom?cmd=insertPage">등록페이지로</a>
</body>
</html>