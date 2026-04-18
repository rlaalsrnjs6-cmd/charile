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
<title>Insert title here</title>
</head>
<body>
	<%-- 	<%@ include file="header.jsp"%> --%>

	<!-- 	전체 -->
	<div id="main-all">
		<!-- 	대시보드 -->
		<div class="dashboard">
			<div class="notice">
				<span>공정라인 정기점검 예정일</span><br>
				<c:forEach var="i" items="${map.dn}">
					<c:choose>
						<c:when test="${i.dsStatus == 1 }">
							<span>${i.dsLine }: ${i.dsDate }일</span>
							<span>점검</span>
							<br>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>


			<div class="machine">
				<span>장비 제어 및 상태</span>
				<c:forEach var="line" items="${map.ls}" varStatus="status">
					<label class="swith"> <span>${line.lineName}</span>
						<div class="line-status" id="status-text-${status.index}">
							<c:choose>
								<c:when test="${line.lineStatus == 0}">
									<span>정지</span>
								</c:when>
								<c:otherwise>
									<span>가동 중</span>
								</c:otherwise>
							</c:choose>
						</div> <input type="checkbox" name="${line.lineName}" class="line-btn"
						data-index="${status.index}"
						${line.lineStatus == 1 ? 'checked' : ''}
						onchange="updateStatus(this)">
					</label>
					<br>
				</c:forEach>
			</div>

			<div class="personal-hygiene"></div>

			<div class="warehouse">
				<span>실시간 보관고 모니터링</span><br>
				<c:forEach var="i" items="${map.wh }">
					<span>(${i.whSection})</span>
					<span>🌡️</span>
					<span>${i.temperature}°C</span>
					<%-- 온도 기준: 안정(10~15), 주의(16~19), 경고(20 이상 또는 5 이하) --%>
					<c:choose>
						<%-- 1. 경고 (가장 위험한 상황을 먼저 체크) --%>
						<c:when test="${i.temperature >= 20.0 || i.temperature <= 5.0}">
							<span class="status-red"> [경고🚨]: 즉시 점검 필요!</span>
						</c:when>

						<%-- 2. 주의 (안정 범위를 벗어났지만 치명적이지는 않은 상태) --%>
						<c:when
							test="${(i.temperature > 15.0 && i.temperature < 20.0) || (i.temperature > 5.0 && i.temperature < 10.0)}">
							<span class="status-yellow">[경고:고온⚠️]</span>
						</c:when>

						<%-- 3. 안정 (최적 상태) --%>
						<c:otherwise>
							<span class="status-green">[정상]</span>
						</c:otherwise>
					</c:choose>
					<span>💧</span>
					<span>${i.humidity}%</span>
					<br>
				</c:forEach>
			</div>
		</div>

	</div>


	<script>
	
		function updateStatus(cb){
			//해당 DOM(인풋)에 대한 name값
			const lineName= cb.name;
			
			//해당 체크박스의 체크 상태 
			const isChecked = cb.checked;
			
			//체크되면 1, 아니면 0
			const statusValue = isChecked ? 1 : 0;
			
			//data-index값 가져옴 해당 돔의 정보박스 안에서 data-index속성의 값을 가져옴
			const index = cb.dataset.index;
			console.log("지금 클릭한 인덱스 번호:", index);
			const msg = isChecked ? "가동하시겠습니까?" : "정지하시겠습니까?";
			
			if(!confirm(lineName + "을(를)" + msg)){
				cb.checked = !isChecked;
				return;
			}
			
			fetch('/charlie/LineUpdate', {
			    method: 'POST',
			    headers: {
			        'Content-Type': 'application/x-www-form-urlencoded',
			    },
			    body: new URLSearchParams({
			        lineName: lineName,
			        lineStatus: statusValue
			    }) // <--- 객체 닫기
			}) // <--- fetch 닫기
			.then(response => {
			    if (response.ok) {
			        console.log("서버 응답 성공!"); 
			        
			        console.log("인덱스==" + `${index}`);
// 			        const statusDiv = document.getElementById(`status-text-${index}`);
			        const statusDiv = document.getElementById("status-text-" + index);
			        statusDiv.innerHTML = isChecked ? "<span>가동 중</span>" : "<span>정지</span>";
			        console.log("기계상태 DB업데이트 성공");
			    } else {
			        alert("기계 상태 변경 실패");
			        cb.checked = !isChecked;
			    }
			})
			.catch(error => {
			    console.error("에러 발생했음", error);
			    cb.checked = !isChecked;
			});

		}//updateStatus(cb) 닫음
	</script>
</body>
</html>