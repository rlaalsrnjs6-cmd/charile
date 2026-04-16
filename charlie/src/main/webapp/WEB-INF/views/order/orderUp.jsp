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
<form method="post" action="order">
	<table border=1>
		<tr>
			<th>제목</th>
			<th>작업시작일자</th>
			<th>일일목표수량</th>
			<th>사원번호</th>
			<th>상태</th>
		</tr>
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="order_num" value="${map.list[0].order_num}">
			<input type="hidden" name="work_date" value="${map.list[0].work_date}">
			<td><input type="text" name="work_order_title" value="${map.list[0].work_order_title}"></td>
			<td>${map.list[0].work_date}</td>
			<td><input type="text" name="daily_target" value="${map.list[0].daily_target}"></td>
			<td>
				<select name="empno">
       				 <c:forEach var="e" items="${emp}">
            			<option value="${e.empno}">
               	 			사원이름:${e.ename}, 사원번호:${e.empno}
             			</option>
        			</c:forEach>
    			</select>
    		</td>
			<td>
    			<select name="status">
            			<option value="Y">
							Y
             			</option>
            			<option value="N">
							N
             			</option>
    			</select>
    		</td>
		</tr>
	</table>
		<input type="submit" value="수정">
	</form>
</body>
</html>