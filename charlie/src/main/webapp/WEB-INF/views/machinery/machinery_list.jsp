<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Machinery.MachineryDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
</head>
<body>

<h1>machinery list</h1>
<hr>
mdm 정보 가져오기
<hr>

<table border="1px">
		<thead>
			<tr>
				<th>machinery_num</th>
				<th>machinery_type</th>
				<th>machinery_status</th>
				<th>error_sign</th>
				<th>m_action</th>
				<th>m_log_time</th>
				<th>mdm_num</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ list }">
			<tr>
				<td>
					${ row.machinery_num }
				</td>
				<td>${ row.machinery_type }</td>
				<td>${ row.machinery_status }</td>
				
				<td>
					<a href="machinery?cmd=detail&machinery_num=${ row.machinery_num }">
						${ row.error_sign }
					</a>
				</td>
				<td>${ row.m_action }</td>
				<td>${ row.m_log_time }</td>
				<td>${ row.mdm_num }</td>
			</tr>
		</c:forEach>
		
		</table>
		
		<hr>
	<a href="machinery?cmd=insertPage">등록페이지로</a>
</body>
</html>