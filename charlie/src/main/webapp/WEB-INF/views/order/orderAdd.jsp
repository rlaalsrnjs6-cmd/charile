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
			<th>작업제목</th>
			<th>생산제목</th>
			<th>작업실시날짜</th>
			<th>일일목표수량</th>
			<th>사원번호</th>
			<th>상태</th>
		</tr>
		<tr>
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="work_order_title"></td>
			<td>
				<select name="prod_num">
       				 <c:forEach var="p" items="${pm}">
            			<option value="${p.prod_num}" selected>
               	 			${p.title}
             			</option>
        			</c:forEach>
    			</select>
    		</td>
			<td><input type="date" name="work_date"></td>
			<td><input type="text" name="daily_target"></td>
			<td>
				<select name="empno">
       				 <c:forEach var="e" items="${emp}">
            			<option value="${e.empno}" selected>
               	 			사원번호:${e.ename}(${e.empno})
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
		<input type="submit" value="작성">
	</form>
</body>
</html>