<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Mdm.MdmDTO"%> 
<%@ page import="fileLibrary.CommonDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mdm list</title>
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

	hr {
		display: none;
	}

	/* 메인 컨테이너 */
	.mdm-all {
		width: 95%;
		max-width: 1400px;
		margin: 3rem auto;
		min-height: 75vh;
		font-weight: 700;
	}

	/* 검색 폼 */
	.sch-fm {
		display: flex;
		justify-content: flex-end;
		align-items: center;
		gap: 0.5rem;
		margin-bottom: 1.5rem;
	}

	.sch-sel {
		padding: 0.5rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		height: 100%;
	}

	.sch-in {
		width: 20vw;
		min-width: 180px;
		padding: 0.5rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
	}

	/* 검색/등록 버튼 공통 스타일 (메인 컬러) */
	.btn-sch, .btn-wr {
		background-color: #4B2C20;
		color: #fff;
		border: none;
		padding: 0.5rem 1.5rem;
		border-radius: 4px;
		font-size: 1rem;
		cursor: pointer;
		transition: background-color 0.3s;
	}

	.btn-sch:hover, .btn-wr:hover {
		background-color: #5C6BC0;
	}

	.btn-wr {
		display: inline-block;
		float: right;
		margin-top: 1.5rem;
	}

	/* 테이블 기본 스타일 (PC/Tablet) */
	.tb-wrap {
		width: 100%;
	}

	.mdm-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
	}

	/* 글자가 아래로 떨어지지 않도록 nowrap 적용 */
	.mdm-tb th, .mdm-tb td {
		padding: 1rem 0.5rem;
		border-bottom: 1px solid #ddd;
		white-space: nowrap; 
	}

	.mdm-tb th {
		background-color: #4B2C20;
		color: #fff;
		font-size: 1.05rem;
		font-weight: 700;
	}

	.mdm-tb tbody tr:hover {
		background-color: #f9f9f9;
	}

	.mdm-tb td a:hover {
		color: #5C6BC0;
		font-weight: bold;
	}

	/* 모바일 반응형: 카드형(Card) 레이아웃 적용 */
	@media (max-width: 768px) {
		.sch-fm {
			flex-wrap: wrap;
			justify-content: center;
		}
		
		.sch-sel, .sch-in {
			width: 48%;
			min-width: unset;
		}
		
		.btn-sch {
			width: 100%;
		}

		/* 테이블 요소를 블록 요소로 변환 */
		.mdm-tb, .mdm-tb tbody, .mdm-tb tr, .mdm-tb td {
			display: block;
			width: 100%;
		}

		/* 기존 테이블 헤더 숨김 */
		.mdm-tb thead {
			display: none;
		}

		/* 각 행(Row)을 하나의 카드처럼 스타일링 */
		.mdm-tb tr {
			margin-bottom: 1rem;
			border: 1px solid #ddd;
			border-radius: 8px; /* 카드 느낌 강조 */
			padding: 0.5rem;
			box-shadow: 0 2px 5px rgba(0,0,0,0.05);
		}

		/* 내부 셀을 Flex로 배치하여 제목과 값을 양쪽 정렬 */
		.mdm-tb td {
			display: flex;
			justify-content: space-between;
			align-items: center;
			text-align: right;
			border-bottom: 1px solid #eee;
			padding: 0.8rem 0.5rem;
			white-space: normal; /* 모바일 카드는 내용이 길면 줄바꿈 허용 */
		}

		.mdm-tb td:last-child {
			border-bottom: none;
		}

		/* data-label 속성을 읽어와서 좌측에 가짜 헤더(제목) 생성 */
		.mdm-tb td::before {
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
		}
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<form action="mdm?cmd=search" method="post">
    <select name="selectName">
		<option value="" selected> 전체보기 </option>
		<c:forEach var="item" items="${ map.select1 }"> 
			<option>${ item.type }</option>
		</c:forEach>
	</select>
	
    <select name="selectChk">
    	<option value="" selected> 확인상태 </option>
		<c:forEach var="item" items="${ map.select2 }">  
			<option>${ item.canUse }</option>
		</c:forEach>
	</select>
	
	<input type="submit" value="검색">
</form>

<hr>

<div class="mdm-all">
	<form class="sch-fm" action="mdm?cmd=search" method="post">
		<select class="sch-sel" name="search_select">
			<option value="search_all">전체</option>
			<option value="code">코드</option>
			<option value="name">명칭</option>
			<option value="unit">단위</option>
			<option value="type">타입</option>
		</select>

		<input class="sch-in" name="search_content" placeholder="검색어를 입력하세요">
		<input class="btn-sch" type="submit" value="검색">
	</form>
	
	<hr>
	
	<div class="tb-wrap">
		<table class="mdm-tb">
			<thead>
				<tr>
					<th>관리번호</th>
					<th>관리코드</th>
					<th>명칭</th>
					<th>수량</th>
					<th>단위</th>
					<th>타입</th>
					<th>가격</th>
					<th>가용 여부</th>
				</tr>
			</thead>

			<tbody>
			<c:forEach var="row" items="${ map.list }">
				<tr>
					<td data-label="관리번호">${ row.mdm_num }</td>
					<td data-label="관리코드">${ row.code }</td>
					<td data-label="명칭">
						<a href="mdm?cmd=detail&mdm_num=${ row.mdm_num }">
							<c:if test="${ empty row.name }"> Null </c:if>
							<c:if test="${ not empty row.name }"> ${ row.name } </c:if>
						</a>
					</td>
					<td data-label="수량">${ row.quantity }</td>
					<td data-label="단위">${ row.unit }</td>
					<td data-label="타입">${ row.type }</td>
					<td data-label="가격">${ row.price }</td>
					<td data-label="가용 여부">${ row.canUse }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
			
	<jsp:include page="/WEB-INF/views/paging.jsp" />
		
	<hr>
	
	<a class="btn-wr" href="${servletName}?cmd=insertPage">등록하기</a>
</div>

<%@ include file="/footer.jsp" %>
</body>
</html>