<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<h2>🔍 점검 조회 페이지</h2>

<form method="get" action="inspection">

이름: <input type="text" name="name" value="${param.name}">

<br><br>

상태:
<input type="radio" name="status" value="전체"
    ${param.status == '전체' || empty param.status ? 'checked' : ''}> 전체

<input type="radio" name="status" value="완료"
    ${param.status == '완료' ? 'checked' : ''}> 완료

<input type="radio" name="status" value="미완료"
    ${param.status == '미완료' ? 'checked' : ''}> 미완료

<br><br>

날짜: <input type="date" name="date" value="${param.date}">

<br><br>

<button type="submit">검색</button>

</form>

<hr>

<table border="1">
<tr>
    <th>NO</th>
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
    <td>${dto.empno}</td>
    <td>${dto.regist_time}</td>
    <td>${dto.ph_num}</td>
    <td>${dto.washed}</td>
</tr>
</c:forEach>
</c:otherwise>

</c:choose>

</table>

<%@ include file="footer.jsp" %>