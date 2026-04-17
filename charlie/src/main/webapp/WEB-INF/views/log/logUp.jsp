<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<form method="post" action="log">
	<table border=1>
		<tr>
			<th>입출고날짜</th>
			<th>구분</th>
			<th>lot번호</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="log_num" value="${map.list[0].log_num}">
			<td><input type="date" name="io_time" value="${map.list[0].io_time}"></td>
			<td>
    			<select name="io_type">
            			<option value="입고">
							입고
             			</option>
            			<option value="출고">
							출고
             			</option>
    			</select>
    		</td>
			<td>
				<select name="lot_num">
       				 <c:forEach var="l" items="${lot}">
            			<option value="${l.lot_num}" selected>
               	 			${l.lot_num}
             			</option>
        			</c:forEach>
    			</select>
    		</td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
</body>
</html>