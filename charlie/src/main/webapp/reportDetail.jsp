<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>리포트 상세페이지</h1>
<table border="1">
    <tr>
        <th>제목</th>
        <td>${post.title}</td>
    </tr>
    <tr>
        <th>작성자</th>
        <td>${post.ename}</td>
    </tr>
    <tr>
        <th>첨부파일</th>
        <td>
            <c:choose>
                <c:when test="${post.url == 'no_file'}">
                    <span>첨부파일 없음</span> 
                </c:when>
                <c:otherwise>
                    <%-- 실제 다운로드 처리를 담당할 서블릿으로 연결 --%>
                    <a href="download.do?fileName=${post.url}">${post.url}</a>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th>내용</th>
        <td>${post.content}</td>
    </tr>
</table>
</body>
</html>