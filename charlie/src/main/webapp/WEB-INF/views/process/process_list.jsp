<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
<style>
	
	.process_img {
		width: 200px;
		height: 100px;
		background-size: cover;
	}
	
</style>
</head>
<body>

<h1>process list</h1>


<hr>

<form action="process?cmd=search" method="post">
   	 	<select name="selectName">
   	 		<option value="" selected> 전체보기 </option>
			<c:forEach var="join" items="${ joinList }"> 
				<option>${ join.name }</option>
			</c:forEach>
		</select>
 	<input type="submit" value="검색">
</form>

<hr>

<table border="1px">
		<thead>
			<tr>
				<th>NAME</th>
				<th>CODE</th>
				<th>PROCESS</th>
				<th>FLOW</th>
				<th>UPDATED MDM NUMBER</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ map.list }">
			<tr>
				<td>${ row.name }</td>
				<td>${ row.code }</td>
				<td><a href="process?cmd=detail&process_num=${ row.process_num }">
					${ row.process_content }
				</a></td>
				<td>${ row.flow }</td>
				
				<td>${ row.mdm_num }</td>
			</tr>
		</c:forEach>
		
		</table>
		
		<hr>
	<a href="process?cmd=insertPage">등록페이지로</a>
</body>
</html>