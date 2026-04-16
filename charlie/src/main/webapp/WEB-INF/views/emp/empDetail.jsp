<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 상세 정보</title>
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

	/* 상세 컨테이너 (반응형: vw, vh, max-width 활용) */
	.dt-wrap {
		width: 90vw;
		max-width: 800px;
		margin: 5vh auto;
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 포인트 */
		border-radius: 4px;
		box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
		padding: 2.5rem;
	}

	/* 메인 타이틀 */
	.lb0 {
		display: block;
		font-weight: bold;
		color: #333;
		font-size: 1.8rem;
		margin-bottom: 1.5rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 항목 행 (Flexbox 활용) */
	.dt-row {
		display: flex;
		border-bottom: 1px solid #eee;
		padding: 1.2rem 0;
		align-items: center;
	}

	/* 항목 라벨 (메인 컬러 적용) */
	.dt-lb {
		width: 8rem;
		font-weight: bold;
		color: #4B2C20; 
		font-size: 1.05rem;
	}

	/* 항목 값 */
	.dt-val {
		flex: 1;
		color: #333;
		font-size: 1rem;
		font-weight: 700;
	}

	/* 버튼 그룹 영역 */
	.btn-grp {
		display: flex;
		justify-content: flex-end;
		align-items: center;
		gap: 0.8rem;
		margin-top: 2rem;
		border-top: 1px solid #ddd;
		padding-top: 1.5rem;
	}

	/* 버튼 공통 스타일 */
	.btn-list, .btn-up {
		display: inline-block;
		text-align: center;
		padding: 0.6rem 1.5rem;
		border-radius: 4px;
		font-size: 1rem;
		font-weight: 600;
		cursor: pointer;
		transition: all 0.3s ease;
	}

	/* 수정 버튼 (메인 컬러) */
	.btn-up {
		background-color: #4B2C20;
		color: #fff;
		border: 1px solid #4B2C20;
	}

	/* 목록 버튼 (서브 동작: 배경은 비우고 테두리만 메인 컬러) */
	.btn-list {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20;
	}

	/* 호버 액션 (서브 컬러 제한적 적용) */
	.btn-up:hover {
		background-color: #5C6BC0;
		border-color: #5C6BC0;
	}

	.btn-list:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f4f6fa;
	}

	/* 모바일 반응형 대응 */
	@media (max-width: 768px) {
		.dt-wrap {
			width: 95vw;
			padding: 1.5rem;
		}
		.dt-row {
			flex-direction: column;
			align-items: flex-start;
		}
		.dt-lb {
			margin-bottom: 0.5rem;
			color: #888;
			font-size: 0.9rem;
		}
		.btn-grp {
			flex-direction: column;
			width: 100%;
		}
		.btn-list, .btn-up {
			width: 100%; 
			margin-bottom: 0.5rem;
		}
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="dt-wrap">
	<span class="lb0">사원 상세정보</span> 

	<div class="dt-row">
		<span class="dt-lb">사원번호</span> 
		<span class="dt-val">${map.list[0].empno}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">사원이름</span> 
		<span class="dt-val">${map.list[0].ename}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">사원등급</span> 
		<span class="dt-val">${map.list[0].emp_level}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">전화번호</span> 
		<span class="dt-val">${map.list[0].tel}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">급여</span> 
		<span class="dt-val">${map.list[0].sal}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">주소</span> 
		<span class="dt-val">${map.list[0].addr}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">생년월일</span> 
		<span class="dt-val">${map.list[0].birthday}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">이메일</span> 
		<span class="dt-val">${map.list[0].email}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">상태</span> 
		<span class="dt-val">${map.list[0].status}</span>
	</div>

	<div class="btn-grp">
		<a href="http://localhost:8080/charlie/emp" class="btn-list">목록으로</a>
		<a href="http://localhost:8080/charlie/emp?empno=${map.list[0].empno}&mod=up" class="btn-up">수정</a>
	</div>
</div>

<%@ include file="/footer.jsp" %>
</body>
</html>