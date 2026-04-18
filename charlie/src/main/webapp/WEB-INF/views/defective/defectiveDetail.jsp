<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
불량번호 : ${map.list[0].defective_num}<br>
불량카테고리 : ${map.list[0].category}<br>
개수 : ${map.list[0].count}<br>
불량조치방법 : ${map.list[0].action}<br>
번호 : ${map.list[0].lot_num}<br>
<a href="http://localhost:8080/charlie/defective?defective_num=${map.list[0].defective_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/defective?defective_num=${map.list[0].defective_num}&mod=delete">삭제</a>
</body>
</html>