<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
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
<form method="post" action="defective">
	<table border=1>
		<tr>
			<th>불량카테고리</th>
			<th>개수</th>
			<th>불량조치방법</th>
			<th>qc체크</th>
		</tr>
		<tr>
			<input type="hidden" name="mod" value="add">
			<td>
				<select name="category">
            			<option value="깨짐" selected>
               	 			깨짐
             			</option>
            			<option value="녹음" selected>
               	 			녹음
             			</option>
            			<option value="이물질" selected>
               	 			이물질
             			</option>
    			</select>
    		</td>
			<td><input type="text" name="count"></td>
			<td>
				<select name="action">
            			<option value="폐기" selected>
               	 			폐기
             			</option>
            			<option value="통과" selected>
               	 			통과
             			</option>
    			</select>
    		</td>
			<td>
				<select name="lot_num">
       				 <c:forEach var="l" items="${lot}">
            			<option value="${l.lot_num}" selected>
               	 			${l.lot_num}${l.qc_chk}${l.lot_count}
             			</option>
        			</c:forEach>
    			</select>
    		</td>
		</tr>
	</table>
		<input type="submit" value="작성">
</form>
</body>
</html>