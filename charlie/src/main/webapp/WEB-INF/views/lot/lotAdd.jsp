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
<form method="post" action="lot">
	<table border=1>
		<tr>
			<th>lot당제품개수</th>
			<th>품질체크전후</th>
			<th>자재관리번호</th>
			<th>작업지시서</th>
		</tr>
		<tr>
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="lot_count"></td>
			<td><input type="text" name="qc_chk"></td>
			<td>
				<select name="material_num">
       				 <c:forEach var="m" items="${material}">
            			<option value="${m.material_num}" selected>
               	 			${m.material_num}
             			</option>
        			</c:forEach>
    			</select>
    		</td>
			<td>
				<select name="order_num">
       				 <c:forEach var="o" items="${order}">
            			<option value="${o.order_num}" selected>
               	 			${o.work_order_title}
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