<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8;");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- 대시보드 전용 맵으로 재정의 --%>
<c:set var="map" value="${dashData}" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MES 대시보드</title>
<style>
	/* [1] 폰트 및 기본 리셋 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	body {
		font-family: 'Noto Sans KR', sans-serif;
		background-color: #f4f6f9;
		color: #333;
		font-size: 1rem; 
	}

	/* [2] 메인 레이아웃 (반응형 Grid) */
	#main-all {
		width: 96vw;
		min-height: 100vh;
		padding: 2vw;
	}

	    .top-head {
        font-size: 2rem;
        font-weight: bold;
        color: var(--c-main);
        text-align: center;
        margin-bottom: 2rem;
        padding-bottom: 1rem;
        border-bottom: 2px solid var(--c-main);
    }
	.dashboard {
		display: grid;
		grid-template-columns: 1fr 1fr;
		gap: 2vw;
		max-width: 1400px;
		margin: 0 auto;
	}

	@media screen and (max-width: 1024px) {
		.dashboard { grid-template-columns: 1fr; }
	}

	/* [3] 각 공통 카드 UI */
	.notice, .machine, .personal-hygiene, .warehouse {
		background-color: #ffffff;
		border-radius: 0.8rem;
		padding: 1.8rem;
		box-shadow: 0 4px 10px rgba(0, 0, 0, 0.04);
		border-top: 4px solid #4B2C20;
		transition: transform 0.2s ease, box-shadow 0.2s ease;
	}

	.notice:hover, .machine:hover, .personal-hygiene:hover, .warehouse:hover {
		transform: translateY(-3px);
		box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
	}

	/* 카드 내 타이틀 공통 스타일 */
	.notice > span:first-child, 
	.machine > span:first-child, 
	.personal-hygiene > span:first-child,
	.warehouse > span:first-child {
		display: flex;
		align-items: center;
		font-size: 1.3rem;
		font-weight: bold;
		color: #4B2C20;
		margin-bottom: 1.5rem;
		padding-bottom: 0.8rem;
		border-bottom: 2px solid #eaeaea;
		width: 100%;
	}

	/* 타이틀 옆 데코 바 (#5C6BC0) */
	.notice > span:first-child::before, 
	.machine > span:first-child::before, 
	.personal-hygiene > span:first-child::before,
	.warehouse > span:first-child::before {
		content: '';
		display: inline-block;
		width: 0.35rem;
		height: 1.2rem;
		background-color: #5C6BC0;
		margin-right: 0.6rem;
		border-radius: 0.2rem;
	}

	.notice span:not(:first-child) {
		font-weight: 700;
		color: #2c3e50;
		line-height: 1.8;
		font-size: 1.05rem;
	}

	/* [4] 장비 제어 및 상태 */
	.swith {
		display: flex;
		align-items: center;
		justify-content: space-between;
		background: #f8f9fa;
		padding: 1rem 1.2rem;
		border-radius: 0.5rem;
		margin-bottom: 0.8rem;
		cursor: pointer;
		border: 1px solid #f0f0f0;
		transition: all 0.2s ease;
	}

	.swith:hover {
		background: #ffffff;
		border-color: #e0e0e0;
		box-shadow: 0 2px 6px rgba(0,0,0,0.05);
	}

	.swith > span:first-child {
		font-weight: 700;
		font-size: 1.1rem;
		width: 25%;
	}

	.line-status {
		display: flex;
		align-items: center;
		width: 40%;
		font-weight: bold;
		color: #888;
	}

	.line-status::before {
		content: '';
		display: inline-block;
		width: 0.8rem;
		height: 0.8rem;
		border-radius: 50%;
		background-color: #ccc; 
		margin-right: 0.6rem;
		transition: background-color 0.3s;
	}

	.swith:has(.line-btn:checked) .line-status::before {
		background-color: #4caf50; 
		box-shadow: 0 0 8px rgba(76, 175, 80, 0.4);
	}
	.swith:has(.line-btn:checked) .line-status {
		color: #4caf50;
	}

	.line-btn {
		appearance: none;
		-webkit-appearance: none;
		width: 3.5rem;
		height: 1.8rem;
		background: #e0e0e0;
		border-radius: 2rem;
		position: relative;
		cursor: pointer;
		outline: none;
		transition: background 0.3s;
	}

	.line-btn::after {
		content: 'OFF';
		position: absolute;
		top: 50%;
		right: 0.5rem;
		transform: translateY(-50%);
		font-size: 0.65rem;
		font-weight: bold;
		color: #fff;
	}

	.line-btn::before {
		content: '';
		position: absolute;
		top: 0.2rem;
		left: 0.2rem;
		width: 1.4rem;
		height: 1.4rem;
		background: white;
		border-radius: 50%;
		transition: all 0.3s;
		box-shadow: 0 2px 4px rgba(0,0,0,0.2);
	}

	.line-btn:checked { background: #5C6BC0; }
	.line-btn:checked::after { content: 'ON'; left: 0.6rem; }
	.line-btn:checked::before { left: calc(100% - 1.6rem); }

	/* [5] 일일 작업 체크리스트 테이블 */
	.personal-hygiene { overflow-x: auto; }
	
	/* 작업자 수 표시 텍스트 */
	.worker-count {
		font-size: 1.05rem;
		color: #5C6BC0;
		margin-left: 0.8rem;
		font-weight: 700;
	}

	table {
		width: 100%;
		border-collapse: separate;
		border-spacing: 0;
		text-align: center;
		border: 1px solid #eaeaea;
		border-radius: 0.5rem;
		overflow: hidden;
	}

	th {
		background-color: #4B2C20; 
		color: #ffffff;
		padding: 1rem 0.8rem;
		font-size: 0.95rem;
		letter-spacing: 0.05rem;
	}

	td {
		padding: 1rem 0.8rem;
		border-bottom: 1px solid #eaeaea;
		font-size: 0.95rem;
		font-weight: 700;
		color: #2c3e50;
	}

	tr:nth-child(even) { background-color: #fcfcfc; }
	tr:hover { background-color: #f1f3f5; }

	/* [6] 실시간 보관고 모니터링 */
	/* 개별 보관고 항목을 Flex 박스로 감싸서 간격 조절 */
	.wh-item {
		display: flex;
		align-items: center;
		flex-wrap: wrap;
		padding: 0.8rem 0;
		border-bottom: 1px dashed #eaeaea;
		gap: 1rem; /* 요소들 사이 넉넉한 띄어쓰기 */
	}
	
	.wh-item:last-child {
		border-bottom: none; /* 마지막 줄은 선 제거 */
	}

	.wh-item span { 
		font-size: 1rem; 
		font-weight: 700; 
		color: #444; 
	}

	/* !important 없이 우선순위를 높이기 위해 .warehouse 하위 선택자 명시 */
	.warehouse span.status-red, 
	.warehouse span.status-yellow, 
	.warehouse span.status-green {
		padding: 0.3rem 0.8rem;
		border-radius: 1rem;
		font-size: 0.85rem;
		margin-left: auto; /* 배지를 오른쪽으로 살짝 밀어줌 */
		box-shadow: 0 1px 3px rgba(0,0,0,0.1);
	}

	.warehouse span.status-red { background-color: #ffebee; color: #c62828; border: 1px solid #ef9a9a; }
	.warehouse span.status-yellow { background-color: #fff8e1; color: #f57f17; border: 1px solid #ffe082; }
	.warehouse span.status-green { background-color: #e8f5e9; color: #2e7d32; border: 1px solid #a5d6a7; }

	/* [9] 페이징 처리용 CSS */
	.page-wrap {
		display: flex;
		justify-content: center;
		margin-top: 1.5rem;
		gap: 0.5rem;
	}
	
	.page-btn {
		padding: 0.5rem 0.8rem;
		color: #555;
		text-decoration: none;
		border-radius: 0.25rem;
		transition: 0.2s;
		font-weight: bold;
	}

	.page-btn:hover { background-color: #eaeaea; }
	.page-active { color: #ffffff; background-color: #4B2C20; }
</style>
</head>
<body>
	<%-- 	<%@ include file="header.jsp"%> --%>

	<div id="main-all">
	 <div class="top-head">대시보드</div>
		<div class="dashboard">
			
			<div class="notice">
				<span>라인 점검 및 설비</span>
				<c:forEach var="i" items="${map.dn}">
					<c:choose>
						<c:when test="${i.dsStatus == 1 }">
							<span>[${i.dsLine }]</span>
							<span>${i.dsDate }일 점검 예정</span>
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
						</div> 
						<%-- 관리자 등급(level이 1)일 때만 스위치 버튼 렌더링, 아니면 완전히 숨김(display: none) --%>
						<c:choose>
							<c:when test="${sessionScope.level == 1 || sessionScope.level eq '1'}">
								<input type="checkbox" name="${line.lineName}" class="line-btn"
								data-index="${status.index}"
								${line.lineStatus == 1 ? 'checked' : ''}
								onchange="updateStatus(this)">
							</c:when>
							<c:otherwise>
								<input type="checkbox" class="line-btn" style="display: none;" ${line.lineStatus == 1 ? 'checked' : ''} disabled>
							</c:otherwise>
						</c:choose>
					</label>
				</c:forEach>
			</div>

			<div class="personal-hygiene">
				<span>금일 작업자 위생체크 <span class="worker-count">[총 ${fn:length(map.ph)}명]</span></span>
				<table>
					<tr>
						<th>사원번호</th>
						<th>사원이름</th>
						<th>체온</th>
						<th>위생체크</th>
					</tr>
					<c:forEach var="h" items="${map.ph}">
						<tr>
							<td>${h.empno}</td>
							<td>${h.ename }</td>
							<td>${h.body_temper}°C</td>
							<td>${h.washed}</td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="warehouse">
				<span>실시간 보관고 모니터링</span>
				<c:forEach var="i" items="${map.wh }">
					<div class="wh-item">
						<span>(${i.whSection})</span>
						<span>🌡️ ${i.temperature}°C</span>
						<span>💧 ${i.humidity}%</span>
						
						<%-- 온도 기준: 안정(10~15), 주의(16~19), 경고(20 이상 또는 5 이하) --%>
						<c:choose>
							<%-- 1. 경고 (가장 위험한 상황을 먼저 체크) --%>
							<c:when test="${i.temperature >= 20.0 || i.temperature <= 5.0}">
								<span class="status-red"> [경고🚨] 고온/저온 위험</span>
							</c:when>
	
							<%-- 2. 주의 (안정 범위를 벗어났지만 치명적이지는 않은 상태) --%>
							<c:when test="${(i.temperature > 15.0 && i.temperature < 20.0) || (i.temperature > 5.0 && i.temperature < 10.0)}">
								<span class="status-yellow">[경고:온도주의⚠️]</span>
							</c:when>
	
							<%-- 3. 안정 (최적 상태) --%>
							<c:otherwise>
								<span class="status-green">[정상]</span>
							</c:otherwise>
						</c:choose>
					</div>
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
			
			if(!confirm(lineName + "을(를) " + msg)){
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