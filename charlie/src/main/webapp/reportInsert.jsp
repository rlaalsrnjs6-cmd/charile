<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8;"); 
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 작성</title>
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

	/* 폼 컨테이너 (반응형: % 및 최대 너비 사용) */
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

	/* 라벨 (메인 컬러) */
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
		font-family: inherit; 
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
		min-height: 20rem; /* 리포트 작성을 위한 충분한 공간 */
		line-height: 1.6;
		resize: vertical; /* 세로 크기 조절만 허용 */
	}

	/* 파일 입력 영역 스타일 */
	.file-in {
		padding: 0.5rem 0;
		font-size: 1rem;
		cursor: pointer;
	}
	
	/* 파일 설명 텍스트 */
	.file-txt {
		font-size: 0.85rem;
		color: #e53935; /* 경고성 정보는 눈에 띄게 분리 */
		margin-top: 0.4rem;
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

	/* 등록 버튼 (메인 동작) */
	.btn-sub {
		background-color: #4B2C20;
		color: #fff;
	}

	.btn-sub:hover {
		background-color: #5C6BC0;
	}

	/* 취소 버튼 (서브 동작) */
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
		<div class="wr-tit">리포트 작성</div>

		<form method="post" action="insert.report" enctype="multipart/form-data">
			
			<div class="wr-row">
				<span class="wr-lb">제목</span> 
				<input type="text" name="title" class="wr-in" placeholder="제목을 입력하세요">
			</div>
			
			<div class="wr-row">
				<span class="wr-lb">내용</span> 
				<textarea name="content" class="wr-in ta-lg" placeholder="내용을 입력하세요"></textarea>
			</div>
			
			<div class="wr-row">
				<span class="wr-lb">첨부파일</span>
				<input type="file" name="file" class="file-in">
				<p class="file-txt">* 10MB 이하의 파일만 가능합니다.</p>
			</div>
			
			<div class="btn-grp">
				<button type="button" class="btn-can">취소</button>
				<button type="submit" class="btn-sub">리포트 등록</button>
			</div>
			
		</form>
	</div>

<%@ include file="footer.jsp" %>

<script>
	const btn_can = document.querySelector(".btn-can");
	const btn_sub = document.querySelector(".btn-sub");
	
	// 요소명 직접 타겟팅하여 안정성 확보
	const title_el = document.querySelector("input[name='title']");
	const content_el = document.querySelector("textarea[name='content']");
	const file_el = document.querySelector("input[name='file']");
	
	// 작성하기 버튼 이벤트 (유효성 검사)
	btn_sub.addEventListener('click', (e) => {
		const title_val = title_el.value.trim();
		const content_val = content_el.value.trim();
		
		// 초기화
		title_el.style.borderColor = "";
		content_el.style.borderColor = "";
		
		// 제목 빈칸 검사
		if (title_val === "" || title_val.length === 0) {
			alert("제목을 입력하세요.");
			e.preventDefault();
			title_el.focus();
			title_el.style.borderColor = "#4B2C20";
			return;
		} 
		// 내용 빈칸 검사
		else if (content_val === "" || content_val.length === 0) {
			alert("내용을 입력하세요.");
			e.preventDefault();
			content_el.focus();
			content_el.style.borderColor = "#4B2C20";
			return;
		}

		// 첨부파일 용량 검사 (10MB 제한 - UX 측면 추가)
		if (file_el.files.length > 0) {
			const fileSize = file_el.files[0].size;
			const maxSize = 10 * 1024 * 1024; // 10MB
			
			if (fileSize > maxSize) {
				alert("첨부파일은 10MB 이하만 등록 가능합니다.");
				e.preventDefault();
				return;
			}
		}
		
		alert("리포트 등록이 완료되었습니다.");
	});
	
	// 작성 취소 버튼 이벤트 (기존 history.back() 로직 적용)
	btn_can.addEventListener('click', (e) => {
		if (confirm("정말 작성을 취소하시겠습니까?")) {
			alert("취소되었습니다.");
			history.back(); // 원래 버튼에 있던 동작을 이관
		} else {
			e.preventDefault(); 
		}
	});
</script>
</body>
</html>