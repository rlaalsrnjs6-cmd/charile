<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
lot번호 : ${lot[0].lot_num}<br>
lot당제품개수 : ${lot[0].lot_count}<br>
작업지시번호 : ${lot[0].order_num}<br>
품질체크전후번호 : ${lot[0].qc_chk}<br>
자재관리번호 : ${lot[0].material_num}<br>
mdm번호 : ${lot[0].mdm_num}<br>
<a href="http://localhost:8080/charlie/lot?lot_num=${lot[0].lot_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/lot?lot_num=${lot[0].lot_num}&mod=delete">삭제</a>
</body>
</html>