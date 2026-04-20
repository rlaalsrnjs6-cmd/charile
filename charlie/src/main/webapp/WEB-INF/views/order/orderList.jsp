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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>작업지시서 목록</title>
<style>
	/* [1] 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	a {
		text-decoration: none;
		color: inherit;
		text-underline-offset: 4px;
	}

	body {
		font-family: 'Pretendard', sans-serif;
		background-color: #fcfcfc;
	}

	/* [2] 메인 컨테이너 (철칙 20 반영) */
	.mat-all {
		width: 95vw;
		max-width: 1400px;
		margin: 3vh auto;
		min-height: 80vh; 
	}

	/* [3] 상단 컨트롤 영역 (메인 컬러 반영) */
	.ctrl-box {
		display: flex;
		justify-content: space-between;
		align-items: center;
		flex-wrap: wrap;
		gap: 1vw;
		margin-bottom: 2vh;
		padding: 1rem;
		background-color: #f9f9f9;
		border-radius: 0.5rem;
		border-top: 3px solid #4B2C20;
	}

	.sch-fm {
		display: flex;
		align-items: center;
		gap: 0.5rem;
	}

	.c-sel, .c-in {
		padding: 0.5rem;
		border: 1px solid #ddd;
		border-radius: 0.25rem;
		font-size: 1rem;
		height: 2.5rem;
		color: #333;
	}

	.c-in {
		width: 20vw;
		min-width: 200px;
	}

	.c-sel:focus, .c-in:focus {
		outline: none;
		border-color: #5C6BC0;
	}

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
	}

	.btn-main:hover {
		background-color: #5C6BC0;
	}

	.btn-wr-area {
		overflow: hidden;
		margin-top: 2vh;
	}

	.btn-wr {
		float: right;
		line-height: 2.5rem;
		display: inline-block;
	}

	/* [4] 테이블 스타일 */
	.tb-wrap {
		width: 100%;
		overflow-x: auto;
	}

	.ord-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
		border: 1px solid #eaeaea;
	}

	.ord-tb th {
		background-color: #4B2C20;
		color: #ffffff;
		padding: 1rem 0.5rem;
		font-size: 1.05rem;
		font-weight: 700;
	}

	.ord-tb td {
		padding: 1rem 0.5rem;
		border-bottom: 1px solid #eaeaea;
		font-weight: 700; 
		color: #2c3e50;
		font-size: 0.95rem;
	}

	.ord-tb tbody tr:hover { background-color: #f1f3f5; }

	.ord-tb td a {
		text-decoration: underline;
	}

	.ord-tb td a:hover {
		color: #5C6BC0;
	}

	/* [5] 페이징 영역 */
	.page-wrap {
		display: flex;
		justify-content: center;
		margin-top: 2vh;
	}

	/* [6] 모바일 반응형 */
	@media (max-width: 768px) {
		.ctrl-box { flex-direction: column; align-items: stretch; }
		.sch-fm { width: 100%; flex-direction: column; }
		.c-in, .c-sel, .btn-main { width: 100%; }
		
		.ord-tb, .ord-tb tbody, .ord-tb tr, .ord-tb td { display: block; width: 100%; }
		.ord-tb thead { display: none; }
		.ord-tb tr { margin-bottom: 1rem; border: 1px solid #ddd; padding: 0.5rem; border-radius: 0.5rem; }
		.ord-tb td { display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #eee; padding: 0.8rem 0.5rem; }
		.ord-tb td::before { content: attr(data-label); font-weight: bold; color: #4B2C20; }
		
		.btn-wr { float: none; width: 100%; text-align: center; }
	}
</style>
</head>
<body>
	<% session.getAttribute("level"); %>
	<%@ include file="/header.jsp" %>

	<div class="mat-all">
		<div class="ctrl-box">
			<form class="sch-fm" action="order" method="get">
				<input type="hidden" name="mod" value="list">
				
				<select class="c-sel" name="time_filter">
					<option value="total" ${param.time_filter == 'total' ? 'selected' : ''}>전체</option>
					<option value="am" ${param.time_filter == 'am' ? 'selected' : ''}>오전</option>
					<option value="pm" ${param.time_filter == 'pm' ? 'selected' : ''}>오후</option>
				</select>
				
				<input type="text" class="c-in" name="search" placeholder="제목/날짜/작성자 검색" value="${param.search}">
				<button type="submit" class="btn-main">검색</button>
			</form>
		</div>

		<div class="tb-wrap">
			<table class="ord-tb">
				<thead>
					<tr>
						<th>작업지시번호</th>
						<th>작업실시날짜</th>
						<th>일일목표수량</th>
						<th>사원번호</th>
						<th>작업제목</th>
						<th>상태</th>
						<th>생산관리번호</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o" items="${map.list}">
						<tr>
							<td data-label="작업지시번호">${o.order_num}</td>
							<td data-label="작업실시날짜">${o.work_date}</td>
							<td data-label="일일목표수량">${o.daily_target}</td>
							<td data-label="사원번호">${o.empno}</td>
							<td data-label="작업제목">
								<a href="order?order_num=${o.order_num}&mod=detail">${o.work_order_title}</a>
							</td>
							<td data-label="상태">${o.status}</td>
							<td data-label="생산관리번호">
								<a href="PMDetailServlet?prod_num=${o.prod_num}">${o.prod_num}</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="btn-wr-area">
			<c:if test="${sessionScope.level < 3 }">
				<a href="order?mod=add" class="btn-main btn-wr">작성하기</a>
			</c:if>
		</div>

		<div class="page-wrap">
			<jsp:include page="/WEB-INF/views/paging.jsp" />
		</div>
	</div>

	<%@ include file="/footer.jsp" %>

	<script>
		/* 철칙 14번 반영: 필터가 선택되어 있다면 검색어 없어도 전송 허용 */
		document.addEventListener("DOMContentLoaded", function() {
			const form = document.querySelector(".sch-fm");
			const searchInput = document.getElementsByName("search")[0];
			const timeFilter = document.getElementsByName("time_filter")[0];

			form.addEventListener("submit", function(e) {
				const searchVal = searchInput.value.trim();
				const filterVal = timeFilter.value;

				// 필터가 '전체'이면서 검색어도 없는 경우에만 제출을 막음
				if (filterVal === "total" && searchVal === "") {
					alert("검색어를 입력하거나 필터를 선택해주세요.");
					searchInput.focus();
					e.preventDefault();
				}
			});
		});
	</script>
</body>
</html>