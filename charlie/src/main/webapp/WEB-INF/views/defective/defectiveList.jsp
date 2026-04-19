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
	<form method="get" action="defective">
		<select id="select" name="category">
			<option value="전체보기"${category == '전체보기' ? 'selected' : ''}>전체보기</option>
			<option value="깨짐"${category == '깨짐' ? 'selected' : ''}>깨짐</option>
			<option value="녹음"${category == '녹음' ? 'selected' : ''}>녹음</option>
			<option value="이물질"${category == '이물질' ? 'selected' : ''}>이물질</option>
		</select>
			<input type="hidden" name="mod" value="select">
			<input type="submit" value="검색">
	</form>
	
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
		// 		document.querySelector('#select').addEventListener('change', function(){
		// 			const category = this.value;
		// 			const url = `defective?mod=select&category=\${category}`;
		// 			fetch(url)
		// 			.then(response => response.text())
		// 			.then(html => {
		// 				document.querySelector('#tableDiv').innerHTML = html;
		// 				const displayUrl = `defective?mod=select&category=\${encodeURIComponent(category)}`;
		// 	            history.pushState({ category: category }, '', displayUrl);
		// 			})
		// 			.catch(error => console.error('Error:', error));
		// 		})

		// 		function loadPage(pageNum) {
		//     const category = document.querySelector('#select').value;
		//     const url = "defective?mod=select&page=" + pageNum + "&category=" + encodeURIComponent(category);

		//     fetch(url)
		//         .then(response => response.text())
		//         .then(html => {
		//             document.querySelector('#tableDiv').innerHTML = html;

		//             // 주소창 업데이트 (페이지 번호 포함)
		//             const displayUrl = `defective?mod=select&page=${pageNum}&category=\${encodeURIComponent(category)}`;
		//             history.pushState({ page: pageNum, category: category }, '', displayUrl);
		//             console.log("클릭된 페이지 번호: " + pageNum);
		//         });
		// }
	</script>
</body>
</html>