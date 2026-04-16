<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
    
<%@ page import="java.util.*"%>
<%@ page import="fileLibrary.CommonDTO"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>품질관리 목록</title>
<style>
	/* 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	a {
		text-decoration: none;
		color: inherit;
	}
	
	.a-title {
		text-decoration: underline;
		text-underline-offset: 5px;
	}

	hr {
		display: none;
	}

	/* 메인 컨테이너 */
	.qc-all {
		width: 95%;
		max-width: 1400px;
		margin: 3rem auto;
		min-height: 75vh;
		font-weight: 700; /* 테이블 글자 굵기 700 통일 */
	}

	/* 등록 버튼 스타일 (메인 컬러) */
	.btn-wr {
		background-color: #4B2C20;
		color: #fff;
		border: none;
		padding: 0.5rem 1.5rem;
		border-radius: 4px;
		font-size: 1rem;
		cursor: pointer;
		transition: background-color 0.3s;
		display: inline-block;
		float: right;
		margin-top: 1.5rem;
	}

	.btn-wr:hover {
		background-color: #5C6BC0; /* 서브 컬러 호버 */
	}

	/* 테이블 래퍼 및 기본 스타일 (PC/Tablet) */
	.tb-wrap {
		width: 100%;
	}

	.qc-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
	}

	/* 글자 줄바꿈 방지 및 패딩 */
	.qc-tb th, .qc-tb td {
		padding: 1rem 0.5rem;
		border-bottom: 1px solid #ddd;
		white-space: nowrap; 
	}

	.qc-tb th {
		background-color: #4B2C20;
		color: #fff;
		font-size: 1.05rem;
	}

	.qc-tb tbody tr:hover {
		background-color: #f9f9f9;
	}

	.qc-tb td a:hover {
		color: #5C6BC0;
		font-weight: 900;
	}

	/* 페이징 현재 페이지 강조 스타일 (paging.jsp 클래스에 맞게 선택자 수정 필요) */
	.page-active, .paging .active {
		color: #5C6BC0;
		font-weight: 900;
		text-decoration: underline;
		text-underline-offset: 4px;
	}

	/* 모바일 반응형: 카드형 레이아웃 */
	@media (max-width: 768px) {
		.qc-tb, .qc-tb tbody, .qc-tb tr, .qc-tb td {
			display: block;
			width: 100%;
		}

		/* 헤더 숨김 */
		.qc-tb thead {
			display: none;
		}

		/* 행(Row) 카드 스타일링 */
		.qc-tb tr {
			margin-bottom: 1rem;
			border: 1px solid #ddd;
			border-radius: 8px;
			padding: 0.5rem;
			box-shadow: 0 2px 5px rgba(0,0,0,0.05);
		}

		/* 셀(Cell) 양쪽 정렬 */
		.qc-tb td {
			display: flex;
			justify-content: space-between;
			align-items: center;
			text-align: right;
			border-bottom: 1px solid #eee;
			padding: 0.8rem 0.5rem;
			white-space: normal;
		}

		.qc-tb td:last-child {
			border-bottom: none;
		}

		/* 가짜 헤더(제목) 생성 */
		.qc-tb td::before {
			content: attr(data-label);
			font-weight: 900;
			color: #4B2C20;
			text-align: left;
			flex-shrink: 0;
			margin-right: 1rem;
		}

		.btn-wr {
			width: 100%;
			text-align: center;
			float: none;
		}
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<hr>
<div class="qc-all">
	<div class="tb-wrap">
		<table class="qc-tb">
			<thead>
				<tr>
					<th>QC번호</th>
					<th>로트번호</th>
					<th>QC부착날짜</th>
					<th>사원번호</th>
					<th>사원이름</th>
					<th>로트당제품개수</th>
					<th>QC체크</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="q" items="${map.list}">
					<tr>
						<td data-label="QC번호">${q.qc_num}</td>
						<td data-label="로트번호">
							<a href="http://localhost:8080/charlie/qc?qc_num=${q.qc_num}&mod=detail" class="a-title">${q.lot_num}</a>
						</td>
						<td data-label="QC부착날짜">${q.qc_date}</td>
						<td data-label="사원번호">${q.empno}</td>
						<td data-label="사원이름">${q.ename}</td>
						<td data-label="로트당제품개수">${q.lot_count}</td>
						<td data-label="QC체크">${q.qc_chk}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<jsp:include page="/WEB-INF/views/paging.jsp" />
	
	<hr>

	<a class="btn-wr" href="http://localhost:8080/charlie/qc?mod=add">작성</a>
</div>

<%@ include file="/footer.jsp" %>
</body>
</html>