<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
log_번호 : ${log[0].log_num}<br>
입출고날짜 : ${log[0].io_time}<br>
구분 : ${log[0].io_type}<br>
lot번호 : ${log[0].lot_num}<br>
mdm번호 : ${log[0].mdm_num}<br>
<a href="http://localhost:8080/charlie/log?log_num=${log[0].log_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/log?log_num=${log[0].log_num}&mod=delete">삭제</a>
</body>
</html>