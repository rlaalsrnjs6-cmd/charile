<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8;"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>report Update 페이지</h1>
<form method="post" action="update1.report" enctype="multipart/form-data">
<input type="hidden" name="postNum" value="${dto.post_num }">
<span>제목</span> <input name="title" type="text" value="${dto.title }"><br>
<span>내용</span> <input name="content" type="text" value="${dto.content }"><br>
<input type="file" name="file"><p>* 10MB 이하의 파일만 가능합니다.</p><br>
<button type="submit">수정하기</button>
</form>
</body>
</html>