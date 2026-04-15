<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 상세</title>
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

	/* 상세 컨테이너 (반응형: % 및 최대 너비 사용) */
	.dt-wrap {
		width: 90%;
		max-width: 900px;
		margin: 3rem auto;
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 포인트 */
		border-radius: 4px;
		box-shadow: 0 2px 10px rgba(0!important;,0,0,0.3);
		padding: 2.5rem;
	}

	/* 항목 행 (Flexbox) */
	.dt-row {
		display: flex;
		border-bottom: 1px solid #eee;
		padding: 1.2rem 0;
		align-items: center;
	}

	/* 메인 타이틀 */
	.lb0 {
		font-weight: bold;
		color: #333;
		font-size: 1.8rem;
	}

	/* 항목 라벨 */
	.dt-lb {
		width: 6rem;
		font-weight: bold;
		color: #4B2C20; /* 메인 컬러 */
		font-size: 1.05rem;
	}

	/* 항목 값 */
	.dt-val {
		flex: 1;
		color: #333;
		font-size: 1rem;
		font-weight: 700;
	}

	/* 첨부파일 링크 호버 (서브 컬러) */
	.dt-val a:hover {
		color: #5C6BC0;
		text-decoration: underline;
	}

	/* 내용 영역 특화 디자인 */
	.cont-box {
		flex-direction: column;
		align-items: flex-start;
		border-bottom: none;
	}

	.cont-box .dt-lb {
		margin-bottom: 1rem;
	}

	.cont-box .dt-val {
		width: 100%;
		min-height: 15rem; /* 보고서 내용 가독성 확보 */
		padding: 1.5rem;
		background-color: #f9f9f9;
		border-radius: 4px;
		line-height: 1.6;
		white-space: pre-wrap; /* 띄어쓰기, 줄바꿈 유지 */
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

	/* 버튼/링크 공통 스타일 */
	.btn-grp button, .btn-grp a {
		display: inline-block;
		text-align: center;
		padding: 0.6rem 1.5rem;
		border: none;
		border-radius: 4px;
		font-size: 1rem;
		cursor: pointer;
		transition: all 0.3s;
	}

	/* 목록/수정 버튼 (메인) */
	.btn-list, .btn-up {
		background-color: #4B2C20;
		color: #fff;
	}

	/* 삭제 버튼 (서브 동작: 배경 비우고 테두리만) */
	.btn-del {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20 !important;
	}

	/* 호버 액션 (서브 컬러 제한적 적용) */
	.btn-list:hover, .btn-up:hover {
		background-color: #5C6BC0;
	}

	.btn-del:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f4f6fa;
	}

	/* 모바일 반응형 대응 */
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
		.btn-grp form {
			width: 100%;
		}
		.btn-grp button, .btn-grp a {
			width: 100%; 
			margin-bottom: 0.5rem;
		}
	}
</style>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="dt-wrap">
	<div class="dt-row">
		<span class="lb0">리포트 상세페이지</span> 
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">제목</span> 
		<span class="dt-val">${dto.title}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">작성자</span> 
		<span class="dt-val">${dto.ename}</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">첨부파일</span> 
		<span class="dt-val">
			<c:choose>
				<c:when test="${dto.url == 'no_file'}">
					<span>첨부파일 없음</span> 
				</c:when>
				<c:otherwise>
					<%-- 실제 다운로드 처리를 담당할 서블릿으로 연결 --%>
					<a href="download.report?fileName=${dto.url}">${dto.url}</a>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
	
	<div class="dt-row cont-box">
		<span class="dt-lb">내용</span> 
		<span class="dt-val">${dto.content}</span>
	</div>

	<div class="btn-grp">
		<a href="${pageContext.request.contextPath}/select.report" class="btn-list">목록으로 돌아가기</a>
		
		<% 
			if(level != 1){
		%>
		<a href="updateForm.report?post_num=${dto.post_num }" class="btn-up">수정하기</a>
		
		<form method="post" action="delete.report" style="margin: 0;">
			<input type="hidden" name="postNum" value="${dto.post_num }">
			<button type="submit" class="btn-del">삭제하기</button>
		</form>
		<%} %>
	</div>
</div>

<%@ include file="footer.jsp" %>

<script>
	// level 권한에 따라 btn-del이 렌더링되지 않을 수 있으므로 null 체크 적용
	const btn_del = document.querySelector(".btn-del");
	
	if (btn_del) {
		btn_del.addEventListener('click', (e) => {
			if(confirm("정말로 해당 리포트를 삭제하시겠습니까?")){
				alert("삭제되었습니다.");
			} else {
				// 취소 시 form submit 이벤트 막기
				e.preventDefault();
				return;
			}
		});
	}
</script>
</body>
</html>