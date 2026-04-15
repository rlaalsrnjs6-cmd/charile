<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8;");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
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

	/* 폼 컨테이너 (반응형 대응) */
	.wr-wrap {
		width: 90%;
		max-width: 800px;
		margin: 3rem auto 6rem auto; 
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 포인트 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.3); 
		padding: 3rem;
	}

	/* 타이틀 */
	.wr-tit {
		font-size: 1.5rem;
		font-weight: 700;
		color: #333;
		margin-bottom: 2rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 입력 행 */
	.wr-row {
		display: flex;
		flex-direction: column;
		margin-bottom: 1.8rem;
	}

	/* 라벨 */
	.wr-lb {
		font-weight: 600;
		color: #4B2C20;
		margin-bottom: 0.6rem;
		font-size: 1.05rem;
	}

	/* 인풋 및 텍스트에어리어 공통 스타일 */
	.wr-in {
		width: 100%;
		padding: 0.8rem 1rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		font-family: inherit; /* textarea의 기본 폰트(굴림 등) 방지 */
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	/* 포커스 시 서브 컬러 반응 (UX 향상) */
	.wr-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15); 
	}

	/* 텍스트에어리어 전용 스타일 */
	.ta-lg {
		min-height: 20rem; /* 충분한 작성 공간 확보 */
		line-height: 1.6;
		resize: vertical; /* 사용자가 세로로만 크기를 조절할 수 있도록 허용 (UX) */
	}

	/* 버튼 그룹 */
	.btn-grp {
		display: flex;
		justify-content: flex-end;
		gap: 1rem;
		margin-top: 3rem;
		border-top: 1px solid #eee;
		padding-top: 2rem;
	}

	/* 버튼 공통 */
	.btn-sub, .btn-can {
		padding: 0.8rem 2.5rem;
		border-radius: 4px;
		font-size: 1rem;
		font-weight: 600;
		text-align: center;
		cursor: pointer;
		border: none;
		transition: all 0.2s;
	}

	/* 등록 버튼 */
	.btn-sub {
		background-color: #4B2C20;
		color: #fff;
	}

	.btn-sub:hover {
		background-color: #5C6BC0;
	}

	/* 취소 버튼 */
	.btn-can {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20;
	}

	.btn-can:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f8f9ff;
	}

	/* 태블릿/모바일 반응형 */
	@media (max-width: 768px) {
		.wr-wrap {
			width: 95%;
			padding: 2rem 1.5rem;
			margin: 2rem auto 4rem auto;
		}

		.btn-grp {
			flex-direction: column-reverse; 
			gap: 0.8rem;
		}

		.btn-sub, .btn-can {
			width: 100%;
			padding: 1rem; 
		}
	}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
	
	<div class="wr-wrap">
		<div class="wr-tit">공지사항 작성</div>

		<form method="post" action="/charlie/NoticeInsertController">
			
			<div class="wr-row">
				<span class="wr-lb">제목</span> 
				<input type="text" name="title" class="wr-in" placeholder="공지사항 제목을 입력하세요">
			</div>
			
			<div class="wr-row">
				<span class="wr-lb">내용</span> 
				<textarea name="content" class="wr-in ta-lg" placeholder="공지사항 내용을 상세히 입력하세요."></textarea>
			</div>
			
			<div class="btn-grp">
				<button type="button"  class="btn-can">작성 취소</button>
				<button type="submit" class="btn-sub">등록하기</button>
			</div>
			
		</form>
	</div>

<%@ include file="footer.jsp" %>
<script>
	const btn_can = document.querySelector(".btn-can");
	
	btn_can.addEventListener('click',()=>{
		if (confirm("정말 작성을 취소하시겠습니까?")) {
            alert("취소되었습니다.");
            location.href = "notice/controller";
           
        } else {
            e.preventDefault(); 
        }
	})
</script>
</body>
</html>