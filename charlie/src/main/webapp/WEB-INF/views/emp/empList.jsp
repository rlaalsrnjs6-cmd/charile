<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 조회</title>
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
	.a-name{
	text-decoration: underline;
	}
	
	/* 전체 래퍼 */
	.emp-all {
		width: 95%;
		max-width: 1400px;
		margin: 3rem auto;
		min-height: 75vh;
		font-weight: 700;
	}

	/* 테이블 감싸개 */
	.tb-wrap {
		width: 100%;
		overflow-x: auto; /* 내용이 길어질 경우 가로 스크롤 허용 */
	}

	/* 테이블 기본 스타일 */
	.emp-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
		font-size: 1rem;
	}

	.emp-tb th, .emp-tb td {
		padding: 1rem 0.5rem;
		border-bottom: 1px solid #ddd;
		white-space: nowrap; 
	}

	/* 메인 컬러 적용 */
	.emp-tb th {
		background-color: #4B2C20;
		color: #fff;
		font-size: 1.05rem;
		font-weight: 700;
	}

	.emp-tb tbody tr:hover {
		background-color: #f9f9f9;
	}

	/* 링크 호버 시 서브 컬러 적용 */
	.emp-tb td a:hover {
		color: #5C6BC0;
		font-weight: bold;
	}

	/* 페이징 컨테이너 */
	.pg-wrap {
		margin-top: 2rem;
		text-align: center;
		display: flex;
		justify-content: center;
		align-items: center;
		gap: 0.5rem;
	}

	/* 페이징 숫자/버튼 기본 스타일 */
	.pg-wrap a, .pg-wrap span {
		padding: 0.4rem 0.8rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		transition: all 0.3s ease;
	}

	.pg-wrap a:hover {
		background-color: #f0f0f0;
	}

	/* 현재 페이징 위치 (서브 컬러 적용, 굵게) 
	   paging.jsp에서 사용하는 태그(b, strong)나 클래스(.active, .on)에 맞게 작동하도록 세팅 */
	.pg-wrap b, .pg-wrap strong, .pg-wrap .active {
/* 		padding: 0.4rem 0.8rem; */
/* 		border: 1px solid #5C6BC0; */
/* 		background-color: #5C6BC0; */
		color: #fff;
		font-weight: 900;
		border-radius: 4px;
	}

	/* 모바일 반응형 (카드 형태) */
	@media (max-width: 768px) {
		.emp-tb, .emp-tb tbody, .emp-tb tr, .emp-tb td {
			display: block;
			width: 100%;
		}

		.emp-tb thead {
			display: none;
		}

		.emp-tb tr {
			margin-bottom: 1rem;
			border: 1px solid #ddd;
			border-radius: 8px;
			padding: 0.5rem;
			box-shadow: 0 2px 5px rgba(0,0,0,0.05);
		}

		.emp-tb td {
			display: flex;
			justify-content: space-between;
			align-items: center;
			text-align: right;
			border-bottom: 1px solid #eee;
			padding: 0.8rem 0.5rem;
			white-space: normal;
		}

		.emp-tb td:last-child {
			border-bottom: none;
		}

		.emp-tb td::before {
			content: attr(data-label);
			font-weight: bold;
			color: #4B2C20;
			text-align: left;
			flex-shrink: 0;
			margin-right: 1rem;
		}
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="emp-all">
	<div class="tb-wrap">
		<table class="emp-tb">
			<thead>
				<tr>
					<th>사원번호</th>
					<th>이름</th>
					<th>id</th>
					<th>pw</th>
					<th>사원등급</th>
					<th>전화번호</th>
					<th>급여</th>
					<th>주소</th>
					<th>상태</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach var="e" items="${map.list}">
				<tr>
					<td data-label="사원번호">${e.empno}</td>
					<td data-label="이름"><a class="a-name" href="http://localhost:8080/charlie/emp?empno=${e.empno}&mod=detail">${e.ename}</a></td>
					<td data-label="id">${e.id}</td>
					<td data-label="pw">${e.pw}</td>
					<td data-label="사원등급">${e.emp_level}</td>
					<td data-label="전화번호">${e.tel}</td>
					<td data-label="급여">${e.sal}</td>
					<td data-label="주소">${e.addr}</td>
					<td data-label="상태">${e.status}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="pg-wrap">
		<jsp:include page="/WEB-INF/views/paging.jsp" />
	</div>
</div>

<%@ include file="/footer.jsp" %>
</body>
</html>