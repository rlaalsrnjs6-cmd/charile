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
	<h1>report insert  페이지</h1>
	<form method="post" action="insert.report" enctype="multipart/form-data">
	<span>제목</span><input type="text" name="title" placeholder="제목을 입력하세요"><br>
	<span>내용</span><input type="text" name="content" placeholder="내용을 입력하세요"><br>
	<input type="file" name="file"><p>* 10MB 이하의 파일만 가능합니다.</p><br>
	<button type="submit">리포트 등록</button>
                <button type="button" onclick="history.back()">취소</button>
	</form>
</body>
</html>