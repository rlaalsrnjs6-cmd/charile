<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;"); %>
		
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
<div id="select">

	<span>제목</span> <span>${result[0].title }</span><br>
	<span>품목 명</span> <sapn>${result[0].mdmName}</sapn><br>
<%-- 	<span>품목 코드</span><input type="text" value="${result[0].code}"><br> --%>
	<span>목표 총량</span> <span>${result[0].target_quantity}</span><br>
	<span>목표 달성량</span> <span>${result[0].currentCount}</span><br>
	<span>남은 목표</span> <span>${result[0].remainCount}</span><br>
	<span>달성률</span><span><fmt:formatNumber value='${(result[0].currentCount * 100.0) / result[0].target_quantity}' pattern='0.0' />%</span><br>
	<span>시작일</span> <span>${result[0].work_start}</span><br>
	<span>마감일</span> <span>${result[0].work_end}</span><br>
<% 
	int level = (Integer)session.getAttribute("level");
	if(level < 3){
%>
	<button type="button" class="PMD-btn-update">수정하기</button>
	
	<form method="post" action="/charlie/PMDelete">
	<input type="hidden" name="prod_num" value="${result[0].prod_num }">
	<button type="submit">삭제</button>
	</form>
	<%} %>
	</div>


	<div id="update">
	<form method="post" action="PMDetailUpdateServlet" onsubmit="return validateForm()">
	<input type="hidden" name="prod_num" value="${result[0].prod_num }">
	<span>제목</span><input name="title" id="title" class="PMD-input" type="text" value="${result[0].title }" ><br>
<%-- 	<span>품목 명</span><input name="mdmName" class="PMD-input" type="text" value="${result[0].mdmName}" readonly><br> --%>
<%-- 	<span>품목 코드</span><input type="text" value="${result[0].code}"><br> --%>
	<span>목표 총량</span><input name="targetAll" id="value2"  class="PMD-input"type="text" value="${result[0].target_quantity}" ><br>
<%-- 	<span>목표 달성량</span><input name="currentTarget" class="PMD-input" type="text" value="${result[0].currentCount}" readonly><br> --%>
<%-- 	<span>남은 목표</span><input	name="remain" class="PMD-input" type="text" value="${result[0].remainCount}" readonly><br> --%>
<%-- 	<span>달성률</span><span><fmt:formatNumber value='${(result[0].currentCount * 100.0) / result[0].target_quantity}' pattern='0.0' />%</span><br> --%>
	<span>시작일</span><input name="startDate" class="PMD-input" type="date" value="${result[0].work_start}" ><br>
	<span>마감일</span><input name="endDate" class="PMD-input" type="date" value="${result[0].work_end}" ><br>
	<button type="button" class="PMD-btn-cancel">취소</button>
	<button type="submit" class="PMD-btn-save">저장하기</button>
	</form>
	</div>
	<a href="${pageContext.request.contextPath}/production/management">목록으로</a>
	
	
	<script>
	function validateForm(){
		const title = document.getElementById("title").value;
		const value2 = document.getElementById("value2").value;
		
		if(title.trim() ===""){
		alert("제목을 입력해주세요.");
		document.getElementById("title").focus();
		return false;
		}
		
		if (value2 === "" || value2 <= 0) {
	        alert("올바른 목표 수량을 입력해주세요.");
	        document.getElementById("value2").focus();
	        return false;
	    }
	}
	
	
	
		window.addEventListener('load', ()=>{
			//페이지 기본상태
			const select = document.querySelector("#select");
			
			//인풋창
			const update= document.querySelector("#update");
			update.style.display="none";
			
			//수정하기 버튼
			const PMD_btn_update = document.querySelector(".PMD-btn-update");
			
			
			//input들
			let PMD_input = document.querySelectorAll(".PMD-input");
			
			const PMD_btn_cancel = document.querySelector(".PMD-btn-cancel");

			
			const PMD_btn_save = document.querySelector(".PMD-btn-save");
			
			//업데이트 할 때 값이 없으면 전송 보류
			
			
			
			
			//수정하기 버튼 클릭이벤트
			PMD_btn_update.addEventListener('click', ()=>{
				update.style.display="block";
				select.style.display="none";

			})//수정하기 버튼 클릭이벤트 닫음
			
			//취소 버튼 클릭이벤트
			PMD_btn_cancel.addEventListener('click', ()=>{
				
				update.style.display="none";
				select.style.display="block";
				
				
			})
		})
	</script>
</body>
</html>