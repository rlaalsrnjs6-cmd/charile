<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입출고 목록</title>
<style>
	/* [1] 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	a {
		text-decoration: none;
		color: inherit;
	}
	.atext {
		text-decoration: underline;
		color: inherit;
	}

	/* [2] 메인 컨테이너 */
	.log-wrap {
		width: 95vw;
		max-width: 1400px;
		margin: 3vh auto;
		min-height: 78vh;
	}

	/* [3] 공통 버튼 */
	.btn-main {
		background-color: #4B2C20; /* 메인 컬러 */
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
		background-color: #5C6BC0; /* 서브 컬러 (포인트로만 사용) */
	}

	.btn-wr {
		float: right;
		margin-top: 2vh;
	}

	/* [4] 테이블 레이아웃 */
	.tb-wrap {
		width: 100%;
		overflow-x: auto;
		border-top: 3px solid #4B2C20; /* MES 느낌의 묵직한 상단 보더 */
		border-radius: 0.5rem;
		background-color: #fff;
	}

	.log-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
/* 		border: 1px solid #eaeaea; */
	}

	.log-tb th, .log-tb td {
		padding: 1rem 0.5rem;
		border-bottom: 1px solid #eaeaea;
		white-space: nowrap;
	}

	.log-tb th {
		background-color: #4B2C20;
		color: #ffffff;
		font-size: 1.05rem;
		font-weight: 700;
	}

	/* 철칙 12 반영: 테이블 글자 굵기 적용 */
	.log-tb td {
		font-weight: 700;
		color: #2c3e50;
		font-size: 0.95rem;
	}

	.log-tb tbody tr:nth-child(even) { background-color: #fcfcfc; }
	.log-tb tbody tr:hover { background-color: #f1f3f5; }

	.log-tb td a:hover {
		color: #5C6BC0; /* 링크 호버 시 서브 컬러 사용 */
	}

	/* [5] 페이징 (paging.jsp에서 활용할 클래스) */
	.page-wrap {
		display: flex;
		justify-content: center;
		margin-top: 3vh;
		gap: 0.5vw;
	}
	
	.page-btn {
		padding: 0.5rem 0.8rem;
		color: #555;
		border-radius: 0.25rem;
		font-weight: bold;
		transition: 0.2s;
	}

	.page-btn:hover {
		background-color: #eaeaea;
	}

	/* 철칙 9 반영: 현재 페이지 활성화 표시 */
	.page-active {
		color: #ffffff;
		background-color: #4B2C20;
		font-weight: 900;
		box-shadow: 0 2px 4px rgba(0,0,0,0.2);
	}

	/* [6] 모바일 반응형 (카드형 레이아웃) */
	@media (max-width: 768px) {
		.log-tb, .log-tb tbody, .log-tb tr, .log-tb td {
			display: block;
			width: 100%;
		}

		.log-tb thead {
			display: none;
		}

		.log-tb tr {
			margin-bottom: 1rem;
			border: 1px solid #ddd;
			border-radius: 0.5rem;
			padding: 0.5rem;
			box-shadow: 0 2px 5px rgba(0,0,0,0.05);
		}

		.log-tb td {
			display: flex;
			justify-content: space-between;
			align-items: center;
			text-align: right;
			border-bottom: 1px solid #eee;
			padding: 0.8rem 0.5rem;
			white-space: normal;
		}

		.log-tb td:last-child {
			border-bottom: none;
		}

		.log-tb td::before {
			content: attr(data-label);
			font-weight: bold;
			color: #4B2C20;
			text-align: left;
			flex-shrink: 0;
			margin-right: 1rem;
		}

		.btn-wr {
			width: 100%;
			text-align: center;
			float: none;
			margin-top: 1.5rem;
		}
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="log-wrap">
	
	<div class="tb-wrap">
		<table class="log-tb">
			<thead>
				<tr>
					<th>log_번호</th>
					<th>입출고날짜</th>
					<th>구분</th>
					<th>사용기한</th>
					<th>lot번호</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="l" items="${map.list}">
					<tr>
						<td data-label="log_번호">${l.log_num}</td>
						<td data-label="입출고날짜"><a class="atext" href="http://localhost:8080/charlie/log?log_num=${l.log_num}&mod=detail">${l.io_time}</a></td>
						<td data-label="구분">${l.io_type}</td>
						<td data-label="사용기한">${l.exp_date}</td>
						<td data-label="lot번호">${l.lot_num}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<jsp:include page="/WEB-INF/views/paging.jsp" />

	<a class="btn-main btn-wr" href="http://localhost:8080/charlie/log?mod=add">작성하기</a>

</div>

<%@ include file="/footer.jsp" %>
</body>
</html>