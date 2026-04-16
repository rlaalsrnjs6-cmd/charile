<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="fileLibrary.CommonDTO"%> 
<%@ page import="javax.servlet.http.HttpSession"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	/* 페이징 컨테이너 */
	.pg-wrap {
		display: flex;
		justify-content: center;
		align-items: center;
		gap: 0.5rem; /* 버튼 간격 */
		margin: 3vh auto; /* 상하 여백 반응형 */
		padding: 1rem;
		flex-wrap: wrap; /* 모바일 환경 줄바꿈 대응 */
		width: 100%;
	}

	/* 공통 버튼 스타일 */
	.pg-btn {
		display: inline-flex;
		justify-content: center;
		align-items: center;
		min-width: 2.5rem;
		height: 2.5rem;
		padding: 0 0.8rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		background-color: #fff;
		color: #333;
		font-size: 1rem;
		text-decoration: none;
		transition: all 0.3s ease;
	}

	/* 기본 버튼 hover (서브 컬러 제한적 사용) */
	a.pg-btn:hover {
		background-color: #f5f5f5;
		border-color: #5C6BC0;
		color: #5C6BC0;
	}

	/* 현재 페이지 활성화 버튼 (메인 컬러 적용) */
	.pg-btn.pg-active {
		background-color: #4B2C20;
		color: #fff;
		border-color: #4B2C20;
	}

	/* 활성화 버튼의 굵은 글씨 색상 상속 */
	.pg-btn.pg-active strong {
		font-weight: 700;
		color: inherit; 
	}

	/* 이전/다음 비활성화 상태 텍스트 */
	.pg-dis {
		background-color: #f9f9f9;
		color: #aaa;
		border-color: #eee;
		cursor: not-allowed;
	}

	/* 태블릿 및 모바일 반응형 처리 */
	@media (max-width: 768px) {
		.pg-btn {
			min-width: 2rem;
			height: 2rem;
			font-size: 0.9rem;
			padding: 0 0.5rem;
		}
	}
</style>
</head>
<body>


<%
	Map map = (Map) request.getAttribute("map");
	int total = (int) map.get("totalCount");
	
	CommonDTO commonDTO = (CommonDTO)map.get("commonDTO");
	int size = (int) commonDTO.getSize();
	int pageNum = (int) commonDTO.getPage();
	
	// 총 페이지 개수 ex: 16.. 17...
	int totalPage = (int) Math.ceil((double) total / size);
	
	// 기본값 5
	int section =  (int) commonDTO.getSection();
	
	// page [1-5] ~ [N-totalPage]  
	int end_section = (int) Math.ceil((double) pageNum / section ) * section ;
	int start_section = end_section - section + 1;
	
	if( end_section > totalPage ) {
		end_section = totalPage;
	}
%>

<c:set var="pageNum" value="${map.commonDTO.page}" />

<div class="pg-wrap">

	<c:if test="<%= start_section == 1 %>">
		<span class="pg-btn pg-dis">이전</span>
	</c:if>
	
	<c:if test="<%= start_section != 1 %>">
<%-- 		<a href="mdm2?cmd=list&page=<%= start_section-1 %>size=<%= size %>"> --%>
		<a class="pg-btn" href="${servletName}?cmd=list&page=<%= 
			(start_section - section < 1 ? 1 : start_section - section) %>
			&size=<%= size %>">
			이전
		</a>
	</c:if>
	
	
	<c:forEach var="i" begin="<%= start_section %>" end="<%= end_section %>">
		<c:if test="${ pageNum eq i }">
			<a class="pg-btn pg-active" href="${servletName}?cmd=list&page=${i}&size=<%= size %>">
				<strong>${i}</strong>
			</a>
		</c:if>
		
		<c:if test="${ pageNum ne i }">
			<a class="pg-btn" href="${servletName}?cmd=list&page=${i}&size=<%= size %>">
				${i}
			</a>
		</c:if>
	</c:forEach>
	
	
	<c:if test="<%= end_section == totalPage %>">
		<span class="pg-btn pg-dis">다음</span>
	</c:if>
	
	<c:if test="<%= end_section != totalPage %>">
		<a class="pg-btn" href="${servletName}?cmd=list&page=<%= end_section+1 %>&size=<%= size %>">
			다음
		</a>
	</c:if>

</div>

</body>
</html>