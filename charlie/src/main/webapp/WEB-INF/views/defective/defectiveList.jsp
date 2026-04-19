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
	${map.list}
	<hr>
	<hr>
	<hr>
	<hr>
	<hr>
	<hr>
	<hr>
	${map.list}
	<%@ include file="/header.jsp"%>
	<select id="select" name="category">
		<option value="전체보기">전체보기</option>
		<option value="깨짐">깨짐</option>
		<option value="녹음">녹음</option>
		<option value="이물질">이물질</option>
	</select>
	<table border=1>
		<thead>
			<tr>
				<th>불량번호</th>
				<th>불량카테고리</th>
				<th>개수</th>
				<th>불량조치방법</th>
				<th>로트번호</th>
			</tr>
		</thead>
		<tbody id="defectiveTable">
			<c:forEach var="d" items="${map.list}">
				<tr>
					<td>${d.defective_num}</td>
					<td><a
						href="http://localhost:8080/charlie/defective?defective_num=${d.defective_num}&mod=detail">${d.category}</td>
					<td>${d.count}</td>
					<td>${d.action}</td>
					<td>${d.lot_num}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="http://localhost:8080/charlie/defective?mod=add">작성</a>
	<jsp:include page="/WEB-INF/views/paging.jsp" />
	<%@ include file="/footer.jsp"%>

	<script>
		document.querySelector('#select').addEventListener('change', function(){
			const category = this.value;
			fetch(`defective?mod=select&category=\${category}`)
			.then(response => response.text())
			.then(html => {
				document.querySelector('#defectiveTable').innerHTML = html;
			})
			.catch(error => console.error('Error:', error));
		})
		
	</script>
</body>
</html>