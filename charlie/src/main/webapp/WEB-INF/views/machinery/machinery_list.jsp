<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Machinery.MachineryDTO"%> 
<%@ page import="fileLibrary.CommonDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
</head>
<body>

<h1>machinery list</h1>
<hr>
<hr>

	<form action="machinery?cmd=search" method="post">
    <select name="selectName">
    		<option value="" selected> 전체보기 </option>
        <c:forEach var="item" items="${ map.select1 }">
            	<option value="${ item.name }"> ${ item.name } </option>
        </c:forEach>
    </select> 
    <input type="submit" value="검색">
    </form>

<table border="1px">
		<thead>
			<tr>
				<th>등록 번호</th>
				<th>해당 장비</th>
				<th>장비 타입</th>
				<th>장비 상태</th>
				<th>에러 내용</th>
				<th>조치 내용</th>
				<th>등록 시간</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ map.list }">
			<tr>
				<td>${ row.machinery_num }</td>
				<td><a href="machinery?cmd=detail&machinery_num=${ row.machinery_num }">
					${ row.mdm_num } : ${ row.name }
				</a></td>
				<td>${ row.machinery_type }</td>
				<td>${ row.machinery_status }</td>
				<td>
					${ row.error_sign }
				</td>
				<td>${ row.m_action }</td>
				<td>${ row.m_log_time }</td>
				
			</tr>
		</c:forEach>
		
		</table>
		
		<jsp:include page="/WEB-INF/views/paging.jsp" />
		
		<hr>
	<a href="machinery?cmd=insertPage">등록페이지로</a>
</body>
</html>