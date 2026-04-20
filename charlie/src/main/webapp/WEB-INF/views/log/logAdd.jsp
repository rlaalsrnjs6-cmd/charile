<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입출고 작성</title>
<style>
	/* 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	body {
		background-color: #f5f5f5;
		font-family: sans-serif;
	}

	/* 메인 컨테이너 - 최소 높이 설정 (철칙 4번 및 요청사항 반영) */
	.mat-all {
		min-height: 84vh; /* 화면 높이에 맞춘 유동적 높이 */
		display: flex;
		flex-direction: column;
		justify-content: center;
		padding: 2rem 0;
	}

	/* 폼 컨테이너 (반응형 대응) */
	.io-wrap {
		width: 90%;
		max-width: 900px;
		margin: 0 auto; 
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.15); 
		padding: 3rem;
	}

	/* 타이틀 */
	.io-tit {
		font-size: 1.5rem;
		font-weight: 700;
		color: #333;
		margin-bottom: 2rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 모바일 화면 가로 스크롤 */
	.tb-box {
		width: 100%;
		overflow-x: auto;
	}

	/* 테이블 스타일 */
	.io-tb {
		width: 100%;
		min-width: 600px;
		border-collapse: collapse;
		margin-bottom: 1.5rem;
	}

	/* 테이블 셀 공통 속성 */
	.io-tb th, .io-tb td {
		padding: 1rem;
		border-bottom: 1px solid #ddd;
		text-align: center;
		vertical-align: middle;
	}

	/* 테이블 헤더 (메인 컬러 적용) */
	.io-tb th {
		background-color: #f9f9f9;
		color: #4B2C20;
		font-weight: 600;
		font-size: 1rem;
	}

	/* 인풋 및 셀렉트 박스 */
	.io-in {
		width: 100%;
		padding: 0.8rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		font-family: inherit; 
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	/* 포커스 시 서브 컬러 반응 */
	.io-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.1); 
	}

	/* 버튼 그룹 */
	.btn-grp {
		display: flex;
		justify-content: flex-end;
		margin-top: 2rem;
	}

	/* 작성 버튼 (메인 컬러) */
	.btn-sub {
		padding: 0.8rem 2.5rem;
		border-radius: 4px;
		font-size: 1rem;
		font-weight: 600;
		text-align: center;
		cursor: pointer;
		border: none;
		background-color: #4B2C20;
		color: #fff;
		transition: all 0.2s;
	}

	/* 작성 버튼 호버 (서브 컬러) */
	.btn-sub:hover {
		background-color: #5C6BC0; 
	}

	/* 모바일/태블릿 반응형 */
	@media (max-width: 768px) {
		.io-wrap {
			width: 95%;
			padding: 2rem 1.5rem;
		}
		.btn-sub {
			width: 100%;
		}
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="mat-all">
	<div class="io-wrap">
		<div class="io-tit">입출고 작성</div>
	
		<form id="ioForm" method="post" action="log">
			<input type="hidden" name="mod" value="add">
			
			<div class="tb-box">
				<table class="io-tb">
					<thead>
						<tr>
							<th>입출고날짜</th>
							<th>구분</th>
							<th>사용기한</th>
							<th>lot번호</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="date" id="io_time" name="io_time" class="io-in" value="${map.list[0].io_time}"></td>
							<td>
								<select name="io_type" class="io-in">
									<option value="입고">입고</option>
									<option value="출고">출고</option>
								</select>
							</td>
							<td><input type="date" id="exp_date" name="exp_date" class="io-in" value="${map.list[0].exp_date}"></td>
							<td>
								<select name="lot_num" class="io-in">
									<c:forEach var="l" items="${lot}">
										<option value="${l.lot_num}" selected>
											${l.lot_num}${l.qc_chk}${l.lot_count}
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="btn-grp">
				<input type="submit" class="btn-sub" value="작성하기">
			</div>
		</form>
	</div>
</div>

<%@ include file="/footer.jsp" %>

<script>
	window.onload = function() {
		const ioForm = document.getElementById("ioForm");
		
		// 작성 전 유효성 및 방어 로직 (철칙 15번 등 준수)
		ioForm.addEventListener("submit", function(e) {
			const ioTime = document.getElementById("io_time");
			const expDate = document.getElementById("exp_date");

			if(ioTime.value.trim() === "") {
				alert("입출고날짜를 선택해주세요.");
				ioTime.focus();
				e.preventDefault();
				return;
			}

			if(expDate.value.trim() === "") {
				alert("사용기한을 선택해주세요.");
				expDate.focus();
				e.preventDefault();
				return;
			}

			// 철칙 15번: 사용기한 방어 로직
			const date1 = new Date(ioTime.value);
			const date2 = new Date(expDate.value);

			if (date2 < date1) {
				alert("사용기한은 입출고날짜와 같거나 미래여야 합니다.");
				expDate.focus();
				e.preventDefault();
				return;
			}

			if(!confirm("입출고 내역을 작성하시겠습니까?")) {
				e.preventDefault();
			}
		});
	}
</script>
</body>
</html>