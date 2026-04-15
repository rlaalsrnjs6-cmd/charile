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
<form method="post" action="qc">
	<table border=1>
		<tr>
			<th>qc번호</th>
			<th>로트번호</th>
			<th>lot당 제품 개수</th>
			<th>체크전후</th>
			<th>사원번호</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="qc_num" value="${map.list[0].qc_num}">
			<input type="hidden" name="lot_num" value="${map.list[0].lot_num}">
			<input type="hidden" name="qc_date" value="${map.list[0].qc_date}">
			<td>${map.list[0].qc_num}</td>
			<td>${map.list[0].lot_num}</td>
			<td><input name="lot_count" value="${lot[0].lot_count}"></td>
			<td><input name="qc_chk" value="${lot[0].qc_chk}"></td>
			<td>
				<select name="empno">
       				 <c:forEach var="e" items="${emp}">
            			<option value="${e.empno}" selected>
               	 			사원이름:${e.ename}, 사원번호:${e.empno}
             			</option>
        			</c:forEach>
    			</select>
    		</td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
	<script>
			
	</script>
</body>
</html>