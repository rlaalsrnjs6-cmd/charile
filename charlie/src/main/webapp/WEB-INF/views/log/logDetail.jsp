<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>입출고 상세정보</title>
<style>
	/* 1. 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	body {
		background-color: #f5f5f5;
		font-family: sans-serif;
	}

	/* 13. a태그 스타일 (밑줄 간격 및 굵기 참고) */
	a {
		text-decoration: none;
		color: inherit;
	}

	/* 메인 컨테이너 - 최소 높이 설정 (요청사항 반영) */
	.mat-all {
		min-height: 84vh; 
		display: flex;
		flex-direction: column;
		justify-content: center;
		padding: 2rem 0;
	}

	/* 4. 반응형 레이아웃 및 5. 메인 컬러 #4B2C20 적용 */
	.dt-wrap {
		width: 90%;
		max-width: 800px;
		margin: 0 auto;
		background-color: #fff;
		border-top: 3rem solid #4B2C20;
		border-radius: 4px;
		box-shadow: 0 4px 12px rgba(0,0,0,0.1); 
		padding: 2.5rem;
	}

	/* 메인 타이틀 */
	.dt-tit {
		display: block;
		font-weight: bold;
		color: #333;
		font-size: 1.8rem;
		margin-bottom: 1.5rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 항목 행 */
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

	/* 12. 테이블 내 글씨 굵기 반영 (font-weight: 700) */
	.dt-val {
		flex: 1;
		color: #333;
		font-size: 1rem;
		font-weight: 700;
	}

	/* 버튼 그룹 */
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
	.btn-up, .btn-del, .btn-list {
		display: inline-block;
		text-align: center;
		padding: 0.6rem 1.5rem;
		border-radius: 4px;
		font-size: 1rem;
		font-weight: 600;
		cursor: pointer;
		transition: all 0.3s ease;
		border: none;
	}

	/* 수정 버튼 (메인 컬러) */
	.btn-up {
		background-color: #4B2C20;
		color: #fff;
	}

	/* 삭제 및 목록 버튼 (테두리 스타일) */
	.btn-del, .btn-list {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20;
	}

	/* 6. 호버 시 서브 컬러 #5C6BC0 사용 */
	.btn-up:hover {
		background-color: #5C6BC0;
	}

	.btn-del:hover, .btn-list:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f4f6fa;
	}

	/* 모바일 환경 반응형 */
	@media (max-width: 768px) {
		.dt-wrap {
			width: 95%;
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
		.btn-up, .btn-del, .btn-list {
			width: 100%; 
			margin-bottom: 0.5rem;
		}
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="mat-all">
	<div class="dt-wrap">
		<span class="dt-tit">입출고 상세정보</span> 

		<div class="dt-row">
			<span class="dt-lb">log_번호</span> 
			<span class="dt-val">${map.list[0].log_num}</span>
		</div>
		
		<div class="dt-row">
			<span class="dt-lb">입출고날짜</span> 
			<span class="dt-val">${map.list[0].io_time}</span>
		</div>
		
		<div class="dt-row">
			<span class="dt-lb">구분</span> 
			<span class="dt-val">${map.list[0].io_type}</span>
		</div>
		
		<div class="dt-row">
			<span class="dt-lb">사용기한</span> 
			<span class="dt-val">${map.list[0].exp_date}</span>
		</div>
		
		<div class="dt-row">
			<span class="dt-lb">lot번호</span> 
			<span class="dt-val">${map.list[0].lot_num}</span>
		</div>

		<div class="btn-grp">
			<a href="log?mod=list" class="btn-list">목록으로</a>
			<a href="log?log_num=${map.list[0].log_num}&mod=up" class="btn-up">수정</a>
			<a href="log?log_num=${map.list[0].log_num}&mod=delete" class="btn-del">삭제</a>
		</div>
	</div>
</div>

<%@ include file="/footer.jsp" %>

<script>
	window.onload = function() {
		const btnDel = document.querySelector(".btn-del");
		
		// 7. 간략한 주석과 함께 이해하기 쉬운 로직 작성
		if (btnDel) {
			btnDel.addEventListener('click', (e) => {
				if(!confirm("정말로 해당 입출고 내역을 삭제하시겠습니까?")) {
					e.preventDefault();
				}
			});
		}
	}
</script>
</body>
</html>