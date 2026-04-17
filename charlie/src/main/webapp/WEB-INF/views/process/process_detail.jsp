<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail page</title>

<style>
	.process-img {
		width: 600px;
		height: 500px;
		
		background-size: cover;
	}
</style>

</head>
<body>

	<h1>Process detail</h1>
	<hr>
	<a href="process?cmd=list">뒤로</a> <br>
	<hr>
	
	<table border="1px">
	<thead>
		<tr>
			<th>PROCESSNUM</th>
			<th>NAME</th>
			<th>CODE</th>
			<th>IMAGE</th>
			<th>PROCESS</th>
			<th>FLOW</th>
			<th>UPDATED MDM NUMBER</th>
		</tr>
	</thead>
	
	<tbody>
		<tr>
			<td> ${ processDTO.process_num }</td>
			<td>  ${ processDTO.name } </td>
			<td>  ${ processDTO.code } </td>
			<td> 
				<div class="process-img" 
					style="background-image : url('${ processDTO.img_url }')">
				</div> 
			</td>
			<td> ${ processDTO.process_content } </td>
			<td> ${ processDTO.flow } </td>
			<td> ${ processDTO.mdm_num } </td>
		</tr>
	</tbody>
	</table>
	
	<hr>
	<a href="process?cmd=modify&process_num=${ processDTO.process_num }">수정</a>
	<a href="process?cmd=delete&process_num=${ processDTO.process_num }">삭제</a>
	
</body>
</html>