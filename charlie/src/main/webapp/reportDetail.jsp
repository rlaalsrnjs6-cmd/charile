<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>리포트 상세페이지</h1>
<form>
<span>제목</span><span>${dto.title }</span> 
<span>내용</span><span>${dto.content }</span>
<span>작성일</span><span>${dto.write_time }</span>
<span>작성자</span><span>${dto.ename }</span>
</form>
</body>
</html>