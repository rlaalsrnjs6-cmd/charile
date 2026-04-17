<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
log_번호 : ${map.list[0].log_num}<br>
입출고날짜 : ${map.list[0].io_time}<br>
구분 : ${map.list[0].io_type}<br>
lot번호 : ${map.list[0].lot_num}<br>
<a href="http://localhost:8080/charlie/log?log_num=${map.list[0].log_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/log?log_num=${map.list[0].log_num}&mod=delete">삭제</a>
</body>
</html>