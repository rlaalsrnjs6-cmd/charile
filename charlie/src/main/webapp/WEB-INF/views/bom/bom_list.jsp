<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Bom.BomDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOM 목록</title>
<style>
	/* [1] 기본 초기화 (철칙 1, 2) */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	a {
		text-decoration: none;
		color: inherit;
	}

	hr {
		display: none; /* 스타일링을 위해 기존 hr 숨김 */
	}

	/* [2] 메인 컨테이너 및 하단 고정 (철칙 4, 20) */
	.mat-all {
		min-height: 100vh;
		display: flex;
		flex-direction: column;
	}

	.bom-all {
		width: 95vw;
		max-width: 1400px;
		margin: 3vh auto;
		flex: 1; /* 컨텐츠가 적어도 푸터를 하단으로 밀어냄 */
	}

	/* [3] 상단 컨트롤 영역 (철칙 5) */
	.ctrl-box {
		display: flex;
		justify-content: space-between;
		align-items: center;
		flex-wrap: wrap;
		gap: 1vw;
		margin-bottom: 1.5vh;
		padding: 1rem;
		background-color: #f9f9f9;
		border-radius: 0.5rem;
		border-top: 3px solid #4B2C20; /* 메인 컬러 */
	}

	.sch-fm {
		display: flex;
		align-items: center;
		gap: 0.5rem;
	}

	/* Select 및 Input (철칙 23: 폰트 블랙) */
	.c-sel {
		padding: 0.5rem;
		border: 1px solid #ddd;
		border-radius: 0.25rem;
		font-size: 1rem;
		height: 2.5rem;
		color: #000;
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
	}

	.btn-main:hover {
		background-color: #5C6BC0; /* 서브 컬러 */
	}

	/* 등록 버튼 위치 (철칙 21) */
	.btn-wr {
		display: inline-block;
		float: right;
		margin-top: 2vh;
		line-height: 2.5rem;
		text-align: center;
	}

	/* [4] 테이블 스타일 (철칙 12, 13, 23) */
	.tb-wrap {
		width: 100%;
		overflow-x: auto;
	}

	.bom-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
		border: 1px solid #eaeaea;
	}

	.bom-tb th {
		background-color: #4B2C20;
		color: #ffffff;
		padding: 1rem 0.5rem;
		font-size: 1.05rem;
		font-weight: 700;
	}

	.bom-tb td {
		padding: 1rem 0.5rem;
		border-bottom: 1px solid #eaeaea;
		white-space: nowrap;
		color: #000; /* 철칙 23 */
		font-weight: 700; /* 철칙 12 */
		font-size: 0.95rem;
	}

	/* 링크 스타일 및 간격 (철칙 13) */
	.bom-tb td a {
		text-decoration: underline;
		text-underline-offset: 5px; /* 글씨와 밑줄 사이 거리 */
	}

	.bom-tb td a:hover {
		color: #5C6BC0;
	}

	.bom-tb tbody tr:nth-child(even) { background-color: #fcfcfc; }
	.bom-tb tbody tr:hover { background-color: #f1f3f5; }

	/* [5] 페이징 (철칙 9) */
	/* paging.jsp 내부의 클래스와 연동된다고 가정 */
	.page-active {
		color: #ffffff !important; /* 여기는 예외적으로 폰트색 변경 가능(철칙 9) */
		background-color: #4B2C20;
		font-weight: 900;
	}

	/* [6] 모바일 반응형 (철칙 4) */
	@media (max-width: 768px) {
		.ctrl-box {
			flex-direction: column;
			align-items: stretch;
		}
		
		.c-sel { width: 100%; }
		.btn-main { width: 100%; }

		.bom-tb, .bom-tb tbody, .bom-tb tr, .bom-tb td {
			display: block;
			width: 100%;
		}

		.bom-tb thead { display: none; }

		.bom-tb tr {
			margin-bottom: 1rem;
			border: 1px solid #ddd;
			border-radius: 0.5rem;
			padding: 0.5rem;
		}

		.bom-tb td {
			display: flex;
			justify-content: space-between;
			align-items: center;
			text-align: right;
			border-bottom: 1px solid #eee;
			white-space: normal;
		}

		.bom-tb td::before {
			content: attr(data-label);
			font-weight: bold;
			color: #4B2C20;
			margin-right: 1rem;
		}

		.btn-wr {
			width: 100%;
			float: none;
		}
	}
</style>
</head>
<body>
<div class="mat-all">
	<%@ include file="/header.jsp" %>

	<div class="bom-all">
	<h1 style="margin-bottom:2vh; color:#4B2C20;">BOM관리</h1>
		<div class="ctrl-box">
			<form class="sch-fm" action="bom?cmd=search" method="post" id="schForm">
				<select class="c-sel" name="selectName">
					<option value="" ${empty param.selectName ? 'selected' : ''}> ---------------전체보기-------------- </option>
					<c:forEach var="item" items="${ list }">
						<c:if test="${ item.type eq 'product' }">
							<option value="${ item.name }" ${param.selectName == item.name ? 'selected' : ''}> ${ item.name } </option>
						</c:if>
					</c:forEach>
				</select> 
				<input class="btn-main" type="submit" value="검색">
			</form>
		</div>

		<div class="tb-wrap">
			<table class="bom-tb">
				<thead>
					<tr>
						<th>재료명</th>
						<th>재료코드</th>
						<th>요구량</th>
						<th>단위</th>
						<th>해당제품</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="row" items="${ map.list }">
					<tr>
						<td data-label="재료명">
							<a href="bom?cmd=detail&bom_num=${ row.bom_num }">
								${ row.name }
							</a>
						</td>
						<td data-label="재료코드">${ row.code }</td>
						<td data-label="요구량">${ row.required_weight }</td>
						<td data-label="단위">${ row.unit }</td>
						<td data-label="해당제품">${ row.target_name }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		
		<jsp:include page="/WEB-INF/views/paging.jsp" />
		
		<c:if test="${sessionScope.level < 3 }">
			<a class="btn-main btn-wr" href="bom?cmd=insertPage">작성하기</a>
		</c:if>
		
	</div>

	<%@ include file="/footer.jsp" %>
</div>

<script>
	/* 철칙 14: 참고 페이지 JS 로직 적용 */
	document.addEventListener("DOMContentLoaded", function() {
		const schForm = document.getElementById("schForm");
		
		/* 철칙 22: 셀렉트 변경 시 자동 검색 또는 초기화 대응 로직이 필요할 경우 여기에 추가 */
		// 현재는 기본 form submit으로 동작
	});
</script>
</body>
</html>