<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="file">
	<table border=1>
		<tr>
			<th>첨부파일 시퀀스</th>
			<th>파일url</th>
			<th>관리코드</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="file_num" value="${file[0].file_num}">
			<td>${file[0].file_num}</td>
			<td><input type="text" name="url" value="${file[0].url}"></td>
			<td><input type="text" name="random_code" value="${file[0].random_code}"></td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
</body>
</html>