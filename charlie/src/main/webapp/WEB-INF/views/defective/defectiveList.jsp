<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>불량품 관리 목록</title>
<style>
	/* [1] 기본 초기화 및 .mat-all 설정 (철칙 1, 2, 20) */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	a {
		text-decoration: none;
		color: inherit;
	}

	.mat-all {
		min-height: 100vh;
		display: flex;
		flex-direction: column;
	}

	/* [2] 메인 컨테이너 (철칙 4, 5) */
	.def-all {
		width: 95vw;
		max-width: 1400px;
		margin: 3vh auto;
		flex: 1; /* 푸터 하단 고정 보조 */
	}

	/* [3] 상단 컨트롤 영역 (철칙 3, 5, 22) */
	.ctrl-box {
		display: flex;
		justify-content: flex-start;
		align-items: center;
		gap: 1vw;
		margin-bottom: 1.5vh;
		padding: 1rem;
		background-color: #f9f9f9;
		border-radius: 0.5rem;
		border-top: 3px solid #4B2C20;
	}

	.flt-fm {
		display: flex;
		align-items: center;
		gap: 0.5rem;
	}

	.c-sel {
		padding: 0.5rem;
		border: 1px solid #ddd;
		border-radius: 0.25rem;
		font-size: 1rem;
		height: 2.5rem;
		color: #333;
		min-width: 150px;
	}

	/* 버튼 스타일 (철칙 5, 6) */
	.btn-main {
		background-color: #4B2C20;
		color: #ffffff;
		border: none;
		height: 2.5rem;
		padding: 0 1.5rem;
		border-radius: 0.25rem;
		font-size: 1rem;
		font-weight: 700;
		cursor: pointer;
		transition: background-color 0.2s ease;
		display: inline-flex;
		align-items: center;
		justify-content: center;
	}

	.btn-main:hover {
		background-color: #5C6BC0;
	}

	.btn-wr {
		float: right;
		margin-top: 2vh;
	}

	/* [4] 테이블 스타일 (철칙 12, 13, 17) */
	.tb-wrap {
		width: 100%;
		overflow-x: auto;
	}

	.def-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
		border: 1px solid #eaeaea;
	}

	.def-tb th {
		background-color: #4B2C20;
		color: #ffffff;
		padding: 1rem 0.5rem;
		font-size: 1.05rem;
		font-weight: 700;
	}

	/* 철칙 12, 17: td 글씨 굵기 및 컬러 참고 로직과 동일화 */
	.def-tb td {
		padding: 1rem 0.5rem;
		border-bottom: 1px solid #eaeaea;
		font-weight: 700;
		color: #2c3e50;
		font-size: 0.95rem;
	}

	/* 철칙 13: a태그 밑줄 색상 및 거리 동일화 */
	.def-tb td a {
		text-decoration: underline;
		text-underline-position: under;
		text-decoration-color: #2c3e50;
	}

	.def-tb td a:hover {
		color: #5C6BC0;
		text-decoration-color: #5C6BC0;
	}

	.def-tb tbody tr:nth-child(even) { background-color: #fcfcfc; }
	.def-tb tbody tr:hover { background-color: #f1f3f5; }

	/* [5] 페이징 스타일 (철칙 9) */
	.page-active {
		color: #ffffff !important;
		background-color: #4B2C20 !important;
		font-weight: 900 !important;
	}

	/* [6] 모바일 반응형 (철칙 4) */
	@media (max-width: 768px) {
		.ctrl-box { flex-direction: column; align-items: stretch; }
		.c-sel, .btn-main { width: 100%; }
		.def-tb thead { display: none; }
		.def-tb, .def-tb tbody, .def-tb tr, .def-tb td { display: block; width: 100%; }
		.def-tb tr { margin-bottom: 1rem; border: 1px solid #ddd; padding: 0.5rem; border-radius: 0.5rem; }
		.def-tb td { display: flex; justify-content: space-between; text-align: right; border-bottom: 1px solid #eee; }
		.def-tb td::before { content: attr(data-label); font-weight: bold; color: #4B2C20; }
		.btn-wr { width: 100%; text-align: center; float: none; }
	}
</style>
</head>
<body>
<div class="mat-all">
	<%@ include file="/header.jsp"%>

	<div class="def-all">
		<div class="ctrl-box">
			<form class="flt-fm" method="get" action="defective" id="searchForm">
				<select class="c-sel" id="select" name="category">
					<option value="전체보기" ${category == '전체보기' ? 'selected' : ''}>전체보기</option>
					<option value="깨짐" ${category == '깨짐' ? 'selected' : ''}>깨짐</option>
					<option value="녹음" ${category == '녹음' ? 'selected' : ''}>녹음</option>
					<option value="이물질" ${category == '이물질' ? 'selected' : ''}>이물질</option>
				</select>
				<input type="hidden" name="mod" value="select">
				<button type="submit" class="btn-main">분류검색</button>
			</form>
		</div>
		
		<div class="tb-wrap">
			<table class="def-tb">
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
							<td data-label="불량번호">${d.defective_num}</td>
							<td data-label="불량카테고리">
								<a href="defective?defective_num=${d.defective_num}&mod=detail">${d.category}</a>
							</td>
							<td data-label="개수">${d.count}</td>
							<td data-label="불량조치방법">${d.action}</td>
							<td data-label="로트번호">${d.lot_num}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<a href="defective?mod=add" class="btn-main btn-wr">작성하기</a>
		
		<jsp:include page="/WEB-INF/views/paging.jsp" />
	</div>

	<%@ include file="/footer.jsp"%>
</div>

<script>
	/* 철칙 14, 22: 참고 로직의 JS 활용 및 셀렉트 UX 보완 */
	document.addEventListener('DOMContentLoaded', function() {
		const categorySelect = document.getElementById('select');
		
		// 검색 후 선택된 옵션 유지 로직은 서버사이드(JSTL)에서 처리됨 (철칙 18 대응)
		
		// 철칙 22 대응: 셀렉트 변경 시 자동 검색 혹은 검색 버튼 유도
		// 참고 로직에 페치 기능이 주석처리 되어있으므로, 철칙 8번에 의거 기존 코드는 건드리지 않고
		// 폼 제출 방식을 유지하되 UX 보완이 필요할 경우 여기서 처리합니다.
	});
</script>
</body>
</html>