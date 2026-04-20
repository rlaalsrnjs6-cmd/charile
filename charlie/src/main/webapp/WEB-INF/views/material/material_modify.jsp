<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Material.MaterialDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자재관리 수정</title>
<style>
/* 기본 초기화 */
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

/* 자재 수정 폼 스코프 */
#mat-mod-scope * {
	box-sizing: border-box;
}

#mat-mod-scope {
	width: 100%;
	padding: 2rem 0 5rem 0;
}

/* 수정 폼 메인 컨테이너 (반응형 대응) */
#mat-mod-scope .mod-wrap {
	width: 90%;
	max-width: 800px;
	margin: 0 auto;
	background-color: #fff;
	border-top: 3rem solid #4B2C20; /* 메인 컬러 포인트 */
	border-radius: 6px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
	padding: 3rem;
}

/* 페이지 타이틀 */
#mat-mod-scope .mod-tit {
	font-size: 1.5rem;
	font-weight: 700;
	color: #333;
	margin-bottom: 2rem;
	border-bottom: 2px solid #eee;
	padding-bottom: 1rem;
}

/* 입력 항목 행 */
#mat-mod-scope .mod-row {
	display: flex;
	flex-direction: column;
	margin-bottom: 1.5rem;
}

/* 라벨 (메인 컬러) */
#mat-mod-scope .mod-lb {
	font-weight: 600;
	color: #4B2C20;
	margin-bottom: 0.6rem;
	font-size: 1.05rem;
}

/* 읽기 전용 텍스트 영역 */
#mat-mod-scope .mod-txt {
	font-size: 1rem;
	color: #555;
	padding: 0.8rem 1rem;
	background-color: #f9f9f9;
	border-radius: 4px;
	border: 1px solid #eee;
}

/* 인풋 및 셀렉트 공통 스타일 */
#mat-mod-scope .mod-in {
	width: 100%;
	padding: 0.8rem 1rem;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 1rem;
	font-family: inherit;
	transition: border-color 0.3s, box-shadow 0.3s;
}

/* 포커스 시 서브 컬러 반응 */
#mat-mod-scope .mod-in:focus {
	outline: none;
	border-color: #5C6BC0;
	box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
}

/* 버튼 그룹 */
#mat-mod-scope .mod-btn-grp {
	display: flex;
	justify-content: flex-end;
	gap: 1rem;
	margin-top: 3rem;
	border-top: 1px solid #eee;
	padding-top: 2rem;
}

/* 버튼 공통 스타일 */
#mat-mod-scope .mod-btn {
	padding: 0.8rem 2.5rem;
	border-radius: 4px;
	font-size: 1rem;
	font-weight: 600;
	text-align: center;
	cursor: pointer;
	border: none;
	transition: all 0.2s;
	text-decoration: none;
}

/* 수정하기 버튼 */
#mat-mod-scope .btn-sub {
	background-color: #4B2C20;
	color: #fff;
}

#mat-mod-scope .btn-sub:hover {
	background-color: #5C6BC0;
}

/* 취소 버튼 */
#mat-mod-scope .btn-can {
	background-color: #fff;
	color: #4B2C20;
	border: 1px solid #4B2C20;
}

#mat-mod-scope .btn-can:hover {
	color: #5C6BC0;
	border-color: #5C6BC0;
	background-color: #f8f9ff;
}

/* 태블릿 및 모바일 반응형 대응 */
@media ( max-width : 768px) {
	#mat-mod-scope .mod-wrap {
		width: 95%;
		padding: 2rem 1.5rem;
	}
	#mat-mod-scope .mod-btn-grp {
		flex-direction: column-reverse;
		gap: 0.8rem;
	}
	#mat-mod-scope .mod-btn {
		width: 100%;
		padding: 1rem;
	}
}
</style>
</head>
<body>
	<%@ include file="/header.jsp"%>

	<h1 style="display: none;">material modify</h1>
	<hr style="display: none;">
	<a href="material?cmd=list" style="display: none;">뒤로</a>
	<hr style="display: none;">

	<div id="mat-mod-scope">
		<div class="mod-wrap">
			<div class="mod-tit">자재정보 수정</div>

			<form id="modForm" method="post" action="material">
				
				<input type="hidden" name="cmd" value="update">
				<input type="hidden" name="material_num" value="${ materialDTO.material_num }">
				<input type="hidden" name="mdm_num" value="${ materialDTO.mdm_num }">
				<%-- <input type="hidden" name="warehouse_num" value="${ materialDTO.warehouse_num }"> --%>
				
				<div class="mod-row">
					<span class="mod-lb">자재관리번호</span> 
					<span class="mod-txt">${ materialDTO.material_num }</span>
				</div>

				<div class="mod-row">
					<span class="mod-lb">이름</span> 
					<span class="mod-txt">${ materialDTO.name }</span>
				</div>

				<div class="mod-row">
					<span class="mod-lb">코드</span> 
					<span class="mod-txt">${ materialDTO.code }</span>
				</div>

				<div class="mod-row">
					<span class="mod-lb">섹션/층구분</span> 
					<select name="warehouse_num" class="mod-in" id="wh_select">
						<option value="">선택하세요</option>
						<c:forEach var="item" items="${map.whList}">
							<option value="${item.warehouse_num}" ${item.warehouse_num eq materialDTO.warehouse_num ? 'selected' : ''}>
								${item.wh_section} / ${item.floor_level}
							</option>
						</c:forEach>
					</select>
				</div>

				<div class="mod-btn-grp">
					<button type="button" class="mod-btn btn-can">취소</button>
					<button type="submit" class="mod-btn btn-sub">수정하기</button>
				</div>

			</form>
		</div>
	</div>

	<%@ include file="/footer.jsp"%>

<script>
	const modForm = document.getElementById("modForm");
	const btn_can = document.querySelector(".btn-can");
	const wh_select = document.getElementById("wh_select");

	// 폼 제출 방어 로직 (UX 향상)
	modForm.addEventListener('submit', (e) => {
		const wh_val = wh_select.value.trim();
		
		if (wh_val === "") {
			alert("섹션/층구분을 선택하세요.");
			e.preventDefault();
			wh_select.focus();
			return;
		}

		if (confirm("해당 내용으로 자재정보를 수정하시겠습니까?")) {
			alert("수정이 완료되었습니다.");
		} else {
			e.preventDefault(); 
		}
	});

	// 취소 버튼 이벤트 방어 로직
	btn_can.addEventListener('click', () => {
		if (confirm("정말 수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
			alert("취소되었습니다.");
			location.href = "material?cmd=list";
		}
	});
</script>
</body>
</html>