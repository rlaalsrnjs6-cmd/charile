<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
첨부파일 시퀀스 : ${file[0].file_num}<br>
파일url : ${file[0].url}<br>
관리코드 : ${file[0].random_code}<br>
업로드시간 : ${file[0].upload_time}<br>
<a href="http://localhost:8080/charlie/file?file_num=${file[0].file_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/file?file_num=${file[0].file_num}&mod=delete">삭제</a>
</body>
</html>