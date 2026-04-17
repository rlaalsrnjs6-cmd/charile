<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
작업지시번호 : ${map.list[0].order_num}<br>
제목 : ${map.list[0].title}<br>
작업실시날짜 : ${map.list[0].work_date}<br>
생산관리번호 : ${map.list[0].prod_num}<br>
목표수량 : ${map.list[0].target_quantity}<br>
사원번호 : ${map.list[0].empno}<br>
mdm번호 : ${map.list[0].mdm_num}<br>
상태 : ${map.list[0].status}<br>
<a href="http://localhost:8080/charlie/order?order_num=${map.list[0].order_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/order?order_num=${map.list[0].order_num}&mod=delete">삭제</a>
</body>
</html>