<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = ""
    %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/header.jsp" %>
<form method="post" action="defective">
	<table border=1>
		<tr>
			<th>불량번호</th>
			<th>불량카테고리</th>
			<th>개수</th>
			<th>qc번호</th>
			<th>불량조치방법</th>
			<th>mdm번호</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="defective_num"></td>
			<td><input type="text" name="category"></td>
			<td><input type="text" name="count"></td>
			<td><input type="text" name="qc_num"></td>
			<td><input type="text" name="action"></td>
			<td><input type="text" name="mdm_num"></td>
		</tr>
		<input type="submit" value="작성">
	</table>
</form>
<%@ include file="/footer.jsp" %>
</body>
</html>