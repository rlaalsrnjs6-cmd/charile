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
기준 번호의 unit 가져오기 / 제품 이름 / 
<form action="bom?cmd=search" method="post">

<table border="1px">
		<thead>
			<tr>
				<th>bom번호</th>
				<th>요구량</th>
				<th>기준번호</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ list }">
			<tr>
				<td>
					<a href="bom?cmd=detail&bom_num=${ row.bom_num }">
						${ row.bom_num }
					</a>
				</td>
				
				<td>${ row.required_weight }</td>
				<td>${ row.mdm_num }</td>
			</tr>
		</c:forEach>
		
		</table>
		
		<hr>
	<a href="bom?cmd=insertPage">등록페이지로</a>
</body>
</html>