<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Material.MaterialDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Material List</title>
<style>
	/* [1] 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	/* 13번째 룰 반영: a태그 스타일은 아래 테이블 td 영역에서 제어하기 위해 여기선 상속만 받음 */
	a { color: inherit; } 

	/* 불필요한 기존 hr 태그 숨김 처리 */
	hr { display: none; }

	/* [2] 메인 컨테이너 (4번째 룰: vw, vh 사용 반응형) */
	.mat-all {
		width: 95vw;
		max-width: 1400px;
		margin: 3vh auto;
		min-height: 78.2vh;
	}

	/* [3] 상단 컨트롤 영역 (검색) */
	.ctrl-box {
		display: flex;
		justify-content: flex-start;
		align-items: center;
		margin-bottom: 1.5vh;
		padding: 1rem;
		background-color: #f9f9f9;
		border-radius: 0.5rem;
		border-top: 3px solid #4B2C20; /* 5번째 룰: 메인컬러 */
	}

	.sch-fm {
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
		min-width: 10vw;
	}

	/* 공통 버튼 스타일 */
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

	/* 6번째 룰: 서브컬러 hover에만 제한적 사용 */
	.btn-main:hover {
		background-color: #5C6BC0;
	}

	.btn-wr {
		display: inline-block;
		float: right;
		margin-top: 2vh;
		line-height: 2.5rem;
		text-decoration: none;
	}

	/* [4] 테이블 스타일 */
	.tb-wrap {
		width: 100%;
		overflow-x: auto;
	}

	.mat-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
		border: 1px solid #eaeaea;
	}

	.mat-tb th, .mat-tb td {
		padding: 1rem 0.5rem;
		border-bottom: 1px solid #eaeaea;
		white-space: nowrap; 
	}

	.mat-tb th {
		background-color: #4B2C20;
		color: #ffffff;
		font-size: 1.05rem;
		font-weight: 700;
	}

	/* 12번째 룰 반영: 테이블 td 글씨 굵기 적용 */
	.mat-tb td {
		font-weight: 700;
/* color: #2c3e50; */
		font-size: 0.95rem;
	}

	.mat-tb tbody tr:nth-child(even) { background-color: #fcfcfc; }
	.mat-tb tbody tr:hover { background-color: #f1f3f5; }

	/* 13번째 룰 반영: a태그 밑줄 및 간격 조정 */
	.mat-tb td a {
	
		text-decoration: underline;
		text-underline-offset: 5px; /* 글씨와 밑줄 사이의 거리 */
	}
	.mat-tb td a:hover {
		color: #5C6BC0;
		text-decoration-color: #5C6BC0;
	}

	/* [5] 페이징 스타일 (paging.jsp용 클래스) */
	.page-wrap {
		display: flex;
		justify-content: center;
		margin-top: 2vh;
		gap: 0.5vw;
	}
	
	.page-btn {
		padding: 0.5rem 0.8rem;
		color: #555;
		border-radius: 0.25rem;
		font-weight: bold;
		transition: 0.2s;
		text-decoration: none;
	}

	.page-btn:hover {
		background-color: #eaeaea;
	}

	/* 9번째 룰 반영: 현재 페이지 굵게 & 배경색 지정 (paging.jsp에서 사용) */
	.page-active {
		color: #ffffff;
		background-color: #4B2C20;
		font-weight: 900;
		box-shadow: 0 2px 4px rgba(0,0,0,0.2);
	}

	/* [6] 모바일 반응형 (카드형 레이아웃) */
	@media (max-width: 768px) {
		.ctrl-box { flex-direction: column; align-items: stretch; }
		.sch-fm { flex-wrap: wrap; justify-content: center; width: 100%; }
		.c-sel { width: 100%; }
		.btn-main { width: 100%; margin-top: 0.5rem; }

		.mat-tb, .mat-tb tbody, .mat-tb tr, .mat-tb td { display: block; width: 100%; }
		.mat-tb thead { display: none; }

		.mat-tb tr {
			margin-bottom: 1rem;
			border: 1px solid #ddd;
			border-radius: 0.5rem;
			padding: 0.5rem;
			box-shadow: 0 2px 5px rgba(0,0,0,0.05);
		}

		.mat-tb td {
			display: flex;
			justify-content: space-between;
			align-items: center;
			text-align: right;
			border-bottom: 1px solid #eee;
			padding: 0.8rem 0.5rem;
			white-space: normal;
		}

		.mat-tb td:last-child { border-bottom: none; }
		.mat-tb td::before {
			content: attr(data-label);
			font-weight: bold;
			color: #4B2C20;
			text-align: left;
			flex-shrink: 0;
			margin-right: 1rem;
		}

		.btn-wr { width: 100%; text-align: center; float: none; }
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="mat-all">
	<div class="ctrl-box">
		<form class="sch-fm" action="material?cmd=search" method="post" onsubmit="return validateSearch(this)">
			<select class="c-sel" name="selectName">
				<option value="" ${empty param.selectName ? 'selected' : ''}> --타입선택-- </option>
				<c:forEach var="item" items="${ map.select1 }"> 
					<option value="${item.type}" ${param.selectName == item.type ? 'selected' : ''}>
						<c:if test="${ item.type eq 'assemble' }">반제품</c:if>
						<c:if test="${ item.type eq 'equip' }">장비</c:if>
						<c:if test="${ item.type eq 'material' }">원재료</c:if>
						<c:if test="${ item.type eq 'product' }">제품</c:if>
					</option>
				</c:forEach>
			</select>
			
			<input class="btn-main" type="submit" value="검색">
		</form>
	</div>

	<div class="tb-wrap">
		<table class="mat-tb">
			<thead>
				<tr>
					<th>자재관리번호</th>
					<th>코드</th>
					<th>자재명</th>
					<th>총량</th>
					<th>가격</th>
<th>자재위치</th>
					<th>층 구분</th>
</tr>
			</thead>
			
			<tbody>
			<c:forEach var="row" items="${ map.list }">
				<tr>
					<td data-label="자재관리번호">${ row.material_num }</td>
					<td data-label="코드">${ row.code }</td>
					<td data-label="자재명">
						<a href="material?cmd=modify&material_num=${ row.material_num }">
							${ row.name }
						</a>
					</td>
					<td data-label="총량">${ row.total_quantity }${ row.unit }</td>
					<td data-label="가격">${ row.total_price }</td>
<%-- 				<td data-label="창고 넘버">${ row.warehouse_num }</td> --%>
					<td data-label="자재위치">${ row.wh_section }</td>
					<td data-label="층 구분">${ row.floor_level }</td>
<%-- 				<td data-label="기준관리 넘버">${ row.mdm_num }</td> --%>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<jsp:include page="/WEB-INF/views/paging.jsp" />
	
	<c:if test="${sessionScope.level < 3 }">
		<a class="btn-main btn-wr" href="material?cmd=insertPage">작성하기</a>
	</c:if>
</div>

<%@ include file="/footer.jsp" %>

<script>
	// 14번째 룰: JS 로직 (검색 유효성 검사)
	function validateSearch(form) {
		const selectBox = form.selectName.value;
		// 필요 시 타입 선택을 강제하려면 아래 로직을 사용할 수 있습니다.
		/*
		if (selectBox === "") {
			alert("타입을 선택해주세요.");
			form.selectName.focus();
			return false;
		}
		*/
		return true;
	}

	// 15번째 룰: 캘린더 날짜 방어 로직 (현재 페이지에는 달력이 없으나 추후 등록/수정 등에서 사용 가능하도록 유틸리티로 추가)
	function validateDateRange(startDateElementId, endDateElementId) {
		const startDate = document.getElementById(startDateElementId).value;
		const endDate = document.getElementById(endDateElementId).value;

		if (startDate && endDate) {
			if (new Date(startDate) > new Date(endDate)) {
				alert("사용기한은 입출고 날짜 이전이 될 수 없습니다. (입출고 날짜 이후로 설정해주세요)");
				document.getElementById(endDateElementId).value = ""; // 잘못된 날짜 초기화
				return false;
			}
		}
		return true;
	}
</script>
</body>
</html>