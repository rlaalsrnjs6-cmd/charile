<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
창고넘버 : ${warehouse[0].warehouse_num}<br>
섹션 : ${warehouse[0].section}<br>
층구분 : ${warehouse[0].floor_level}<br>
온도 : ${warehouse[0].temperature}<br>
습도 : ${warehouse[0].humidity}<br>
정상체크 : ${warehouse[0].wh_status_chk}<br>
<a href="http://localhost:8080/charlie/warehouse?warehouse_num=${warehouse[0].warehouse_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/warehouse?warehouse_num=${warehouse[0].warehouse_num}&mod=delete">삭제</a>
</body>
</html>