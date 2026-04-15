<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세</title>
<style>
	/* 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	/* 상세페이지 컨테이너 (반응형: % 및 최대 너비 사용) */
	.dt-wrap {
		width: 90%;
		max-width: 900px;
		margin: 3rem auto;
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러를 포인트로 사용 */
		border-radius: 4px;
		box-shadow: 0 2px 10px rgba(0,0,0,0.3);
		padding: 2.5rem;
	}

	/* 각 항목 행 (Flexbox 레이아웃) */
	.dt-row {
		display: flex;
		border-bottom: 1px solid #eee;
		padding: 1.2rem 0;
		align-items: center;
	}

	/* 항목 라벨 */
	.dt-lb {
		width: 6rem;
		font-weight: bold;
		color: #4B2C20; /* 메인 컬러 적용 */
		font-size: 1.05rem;
	}
	.lb0{
			width: 7rem;
		font-weight: bold;
		color: black; /* 메인 컬러 적용 */
		font-size: 1.8rem;
	}
	

	/* 항목 값 */
	.dt-val {
		flex: 1;
		color: #333;
		font-size: 1rem;
		font-weight: 700;
	}

	/* 내용 영역 특화 디자인 */
	.cont-box {
		flex-direction: column;
		align-items: flex-start;
		border-bottom: none; /* 마지막 행이므로 밑줄 제거 */
	}

	.cont-box .dt-lb {
		margin-bottom: 1rem;
	}

	.cont-box .dt-val {
		width: 100%;
		min-height: 15rem; /* MES 보고서 스타일의 넓은 가독성 확보 */
		padding: 1.5rem;
		background-color: #f9f9f9;
		border-radius: 4px;
		line-height: 1.6;
		white-space: pre-wrap; /* DB에 저장된 띄어쓰기와 줄바꿈을 그대로 유지 (UX) */
	}

	/* 버튼 그룹 영역 */
	.btn-grp {
		display: flex;
		justify-content: flex-end;
		gap: 0.8rem;
		margin-top: 2rem;
		border-top: 1px solid #ddd;
		padding-top: 1.5rem;
	}

	/* 버튼 공통 스타일 */
	.btn-grp button {
		padding: 0.6rem 1.5rem;
		border: none;
		border-radius: 4px;
		font-size: 1rem;
		cursor: pointer;
		transition: all 0.3s;
	}

	/* 수정 버튼 (메인 동작) */
	.btn-up {
		background-color: #4B2C20;
		color: #fff;
	}

	/* 삭제 버튼 (서브 동작: 배경을 비우고 테두리만 설정하여 실수 방지) */
	.btn-del {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20 !important; /* 여기서는 테두리를 위해 예외적으로 선언하지 않고 아래처럼 덮어씌움 */
	}
	/* !important 철칙에 맞게 수정 */
	.btn-grp .btn-del {
		border: 1px solid #4B2C20; 
	}

	/* 서브 컬러(#5C6BC0)를 hover 액션에 제한적 사용 (UX: 반응성 제공) */
	.btn-up:hover {
		background-color: #5C6BC0;
	}

	.btn-grp .btn-del:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f4f6fa;
	}

	/* 태블릿 및 모바일 반응형 대응 */
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
		}

		.btn-grp form {
			width: 100%;
		}

		.btn-grp button {
			width: 100%; /* 모바일 환경 터치 영역 최적화 */
		}
	}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
	
	<div class="dt-wrap">
		<div class="dt-row">
			<span class="lb0">공지사항</span> 
		</div>
		<div class="dt-row">
			<span class="dt-lb">No</span> 
			<span class="dt-val">${list[0].post_num }</span>
		</div>
		
		<div class="dt-row">
			<span class="dt-lb">제목</span> 
			<span class="dt-val">${list[0].title }</span>
		</div>
		
		<div class="dt-row">
			<span class="dt-lb">작성날짜</span> 
			<span class="dt-val">${list[0].write_time }</span>
		</div>
		
		<div class="dt-row">
			<span class="dt-lb">작성자</span> 
			<span class="dt-val">관리자</span>
		</div>
		
		<div class="dt-row cont-box">
			<span class="dt-lb">내용</span> 
			<span class="dt-val">${list[0].content }</span>
		</div>
		
		<% 
			// 헤더에서 선언된 level 변수를 그대로 사용
			if(level == 1){ 
		%>
		<div class="btn-grp">
			<form method="post" action="/charlie/noticeUpdate.jsp" style="margin: 0;">
				<input type="hidden" name="post_num" value="${list[0].post_num }">
				<input type="hidden" name="title" value="${list[0].title }">
				<input type="hidden" name="content" value="${list[0].content }">
				<button type="submit" class="btn-up">수정하기</button>
			</form>
			
			<form method="post" action="/charlie/NoticeDeleteController" style="margin: 0;">
				<input type="hidden" name="post_num" value="${list[0].post_num }">
				<button type="submit" class="btn-del">삭제하기</button>
			</form>
		</div>
		<% } %>
		
	</div>

<%@ include file="footer.jsp" %>
<script>

		const btn_del = document.querySelector(".btn-del");
		
		btn_del.addEventListener('click', ()=>{
			if(confirm("정말로 해당 공지사항을 삭제하시겠습니까?")){
				alert("삭제되었습니다.");
			}else{
				alert("취소되었습니다.");
			}
			
			
		})

	
</script>
</body>
</html>