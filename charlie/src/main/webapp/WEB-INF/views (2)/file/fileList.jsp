<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1>
		<tr>
			<th>첨부파일 시퀀스</th>
			<th>파일url</th>
			<th>관리코드</th>
			<th>업로드시간</th>
		</tr>
		
		<c:forEach var="f" items="${file}">
			<tr>
				<td>${f.file_num}</td>
				<td><a href="http://localhost:8080/charlie/file?file_num=${f.file_num}&mod=detail">${f.url}</td>
				<td>${f.random_code}</td>
				<td>${f.upload_time}</td>
			</tr>
		</c:forEach>
	</table>
	<a href = "http://localhost:8080/charlie/file?mod=add">작성</a>
</body>
</html>