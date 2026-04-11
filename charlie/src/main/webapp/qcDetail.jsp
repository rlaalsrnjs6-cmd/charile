<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
qc번호 : ${qc[0].qc_num}<br>
로트번호 : ${qc[0].lot_num}<br>
qc부착날짜 : ${qc[0].qc_date}<br>
사원번호 : ${qc[0].empno}<br>
<a href="http://localhost:8080/charlie/qc?qc_num=${qc[0].qc_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/qc?qc_num=${qc[0].qc_num}&mod=delete">삭제</a>
</body>
</html>