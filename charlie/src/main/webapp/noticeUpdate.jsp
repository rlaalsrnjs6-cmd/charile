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
<title>공지사항 수정</title>
<style>
	/* 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	/* 수정 폼 컨테이너 (반응형 퍼센트 및 최대 너비 지정) */
	.up-wrap {
		width: 90%;
		max-width: 800px;
		margin: 3rem auto 6rem auto;
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 철칙 5: 메인 컬러 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.08); 
		padding: 3rem;
	}

	/* 페이지 타이틀 */
	.up-tit {
		font-size: 1.5rem;
		font-weight: 700;
		color: #333;
		margin-bottom: 2rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 입력 항목 행 (세로 배치 정렬) */
	.up-row {
		display: flex;
		flex-direction: column;
		margin-bottom: 1.8rem;
	}

	/* 라벨 (제목, 내용) */
	.up-lb {
		font-weight: 600;
		color: #4B2C20; 
		margin-bottom: 0.6rem;
		font-size: 1.05rem;
	}

	/* 인풋 및 텍스트에어리어 공통 스타일 */
	.up-in {
		width: 100%;
		padding: 0.8rem 1rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		font-family: inherit;
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	/* 포커스 시 서브 컬러 반응 (철칙 6 제한적 사용) */
	.up-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15); 
	}

	/* 내용 텍스트에어리어 전용 스타일 */
	.ta-lg {
		min-height: 20rem; 
		line-height: 1.6;
		resize: vertical; /* 세로 크기 조절 허용 */
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

	/* 버튼 공통 스타일 */
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

	/* 수정하기 버튼 (메인) */
	.btn-sub {
		background-color: #4B2C20;
		color: #fff;
	}

	.btn-sub:hover {
		background-color: #5C6BC0;
	}

	/* 취소 버튼 (서브) */
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

	/* 철칙 4: 태블릿 및 모바일 반응형 대응 */
	@media (max-width: 768px) {
		.up-wrap {
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
	
	<div class="up-wrap">
		<div class="up-tit">공지사항 수정</div>

		<form method="post" action="/charlie/NoticeUpdateController">
			
			<input type="hidden" name="post_num" value="${param.post_num }">
			
			<div class="up-row">
				<span class="up-lb">제목</span> 
				<input type="text" name="title" value="${param.title }" class="up-in">
			</div>
			
			<div class="up-row">
				<span class="up-lb">내용</span> 
				<textarea name="content" class="up-in ta-lg">${param.content}</textarea>
			</div>
			
			<div class="btn-grp">
				<button type="button" class="btn-can" onclick="history.back()">취소</button>
				<button type="submit" class="btn-sub">수정하기</button>
			</div>
			
		</form>
	</div>

<%@ include file="footer.jsp" %>
<script>
//수정 취소 버튼
const btn_can = document.querySelector(".btn-can");

//수정하기 버튼
const btn_sub = document.querySelector(".btn-sub");


//수정하기 누르면
btn_sub.addEventListener('click', (e)=>{
	//제목 및 내용 DOM
	const title_el = document.querySelector(".up-in");
	const content_el = document.querySelector(".ta-lg");
	
	//제목 input
	const title_val = document.querySelector(".up-in").value.trim();
	//내용 input
	const content_val = document.querySelector(".ta-lg").value.trim();
	
	title_el.style.borderColor = "";
    content_el.style.borderColor = "";
	
	if(title_val == "" || title_val.length == 0){
		alert("제목을 입력하세요.");
		e.preventDefault();
		title_el.focus();
		title_el.style.borderColor = "#4B2C20";
		return;
	}else if(content_val == "" || content_val.length == 0){
		alert("내용을 입력하세요.");
		e.preventDefault();
		content_el.focus();
		content_el.style.borderColor = "#4B2C20";
		return;
	}
	
	alert("수정이 완료되었습니다.");
	
})

//수정 취소 버튼
	btn_can.addEventListener('click',(e)=>{
		if (confirm("정말 수정을 취소하시겠습니까?")) {
            alert("취소되었습니다.");

           
        } else {
            e.preventDefault(); 
        }
	})
</script>
</body>
</html>