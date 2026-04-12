<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Mdm.MdmDTO"%> 
<%@ page import="fileLibrary.TestDTO"%> 
<%@ page import="javax.servlet.http.HttpSession"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>페이징 include</h1>
<!-- paging -->

<% 

	Map map = (Map) request.getAttribute("map");
	int total = (int) map.get("totalCount");
	
 	TestDTO testDTO = (TestDTO)map.get("testDTO");
 	int size = (int) testDTO.getSize();
 	int pageNum = (int) testDTO.getPage();
 	
 	// 총 페이지 개수 ex: 16.. 17...
 	int totalPage = (int) Math.ceil((double) total / size);
 	
 	// 기본값 5
 	int section =  (int) testDTO.getSection();
 	
 	// page [1-5] ~ [N-totalPage]  
 	int end_section = (int) Math.ceil((double) pageNum / section ) * section ;
 	int start_section = end_section - section + 1;
 	
 	if( end_section > totalPage ) {
 		end_section = totalPage;
 	}
%>

<c:set var="pageNum" value="${map.testDTO.page}" />

	<c:if test="<%= start_section == 1 %>">
		이전
	</c:if>
	<c:if test="<%= start_section != 1 %>">
<%-- 		<a href="mdm2?cmd=list&page=<%= start_section-1 %>size=<%= size %>"> --%>
		<a href="${servletName}?cmd=list&page=<%= 
			(start_section - section < 1 ? 1 : start_section - section) %>
			&size=<%= size %>">
			이전
		</a>
	</c:if>
	
	
	<c:forEach var="i" begin="<%= start_section %>" end="<%= end_section %>">
		
		<a href="${servletName}?cmd=list&page=${i}&size=<%= size %>">
			<c:if test="${ pageNum eq i }">
				<strong>${i}</strong>
			</c:if>
		</a>
		
		<a href="${servletName}?cmd=list&page=${i}&size=<%= size %>">
			<c:if test="${ pageNum ne i }">
				${i}
			</c:if>
		</a>
	</c:forEach>
	
	<c:if test="<%= end_section == totalPage %>">
		다음
	</c:if>
	<c:if test="<%= end_section != totalPage %>">
		<a href="${servletName}?cmd=list&page=<%= end_section+1 %>&size=<%= size %>">
			다음
		</a>
	</c:if>
</body>
</html>