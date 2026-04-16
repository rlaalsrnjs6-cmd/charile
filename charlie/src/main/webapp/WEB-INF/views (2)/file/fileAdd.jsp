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
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="file_num"></td>
			<td><input type="text" name="url"></td>
			<td><input type="text" name="random_code"></td>
		</tr>
		<input type="submit" value="작성">
	</table>
</form>
</body>
</html>