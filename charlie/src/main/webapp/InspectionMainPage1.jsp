<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<h2>🔍 점검 조회 페이지</h2>

<form method="get" action="inspection">

    이름: <input type="text" name="name">

    <br><br>

    상태:
    <input type="radio" name="status" value="all" checked> 전체
    <input type="radio" name="status" value="완료"> 완료
    <input type="radio" name="status" value="미완료"> 미완료

    <br><br>

    날짜: <input type="date" name="date">

    <br><br>

    <button type="submit">검색</button>

</form>

<hr>

<table border="1">
<tr>
    <th>No</th>
    <th>검사 시간</th>
    <th>이름</th>
    <th>결과</th>
</tr>

<c:choose>

<c:when test="${empty list}">
<tr>
    <td colspan="4" style="color:red; text-align:center;">
        ⚠ 검색 결과가 없습니다.
    </td>
</tr>
</c:when>

<c:otherwise>
<c:forEach var="dto" items="${list}">
<tr>
    <td>${dto.EMPNO}</td>
    <td>${dto.REGIST_TIME}</td>
    <td>${dto.PH_NUM}</td>
    <td>${dto.WASHED}</td>
</tr>
</c:forEach>
</c:otherwise>

</c:choose>

</table>

<br>

<c:forEach var="i" begin="1" end="5">
    <a href="inspection?page=${i}&status=${param.status}&date=${param.date}">
        ${i}
    </a>
</c:forEach>

<%@ include file="footer.jsp" %>