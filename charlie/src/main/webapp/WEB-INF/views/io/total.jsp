<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Mdm.MdmDTO"%> 
<%@ page import="fileLibrary.CommonDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	/* [1] 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	a {
		text-decoration: none;
		color: inherit;
	}

	hr {
		display: none;
	}

	/* [2] 메인 컨테이너 */
	.mdm-all {
		width: 95vw;
		max-width: 1400px;
		margin: 3vh auto;
		min-height: 78.2vh;
	}

	/* [3] 상단 컨트롤 영역 (필터 + 검색) */
	.ctrl-box {
		display: flex;
		justify-content: space-between;
		align-items: center;
		flex-wrap: wrap;
		gap: 1vw;
		margin-bottom: 1.5vh;
		padding: 1rem;
		background-color: #f9f9f9;
		border-radius: 0.5rem;
		border-top: 3px solid #4B2C20;
	}

	.flt-fm, .sch-fm {
		display: flex;
		align-items: center;
		gap: 0.5rem;
	}
	
	.flt-fm {
	display: flex;
	justify-content: space-between;  /* ⭐ 이거 필수 */
	align-items: center;
	width: 100%;
}

	/* Select 및 Input 공통 스타일 */
	.c-sel, .c-in {
		padding: 0.5rem;
		border: 1px solid #ddd;
		border-radius: 0.25rem;
		font-size: 1rem;
		height: 2.5rem;
		color: #333;
	}

	.c-in {
		width: 18vw;
		min-width: 150px;
	}
	
	.left-box,
	.right-box {
    display: flex;
    align-items: center;
    gap: 0.5rem;
}

	/* 버튼 공통 스타일 */
	.btn-main {
		background-color: #4B2C20;
		color: #ffffff;
		border: none;
		height: 2.5rem;
		padding: 0 1.5rem;
		border-radius: 0.25rem;
		font-size: 1rem;
		font-weight: 700;
		cursor: pointer;
		transition: background-color 0.2s ease;
	}

	.btn-main:hover {
		background-color: #5C6BC0;
	}

	.btn-wr {
		display: inline-block;
		float: right;
		margin-top: 2vh;
		line-height: 2.5rem;
	}

	/* [4] 테이블 스타일 */
	.tb-wrap {
		width: 100%;
		overflow-x: auto;
	}

	.mdm-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
		border: 1px solid #eaeaea;
	}

	.mdm-tb th, .mdm-tb td {
		padding: 1rem 0.5rem;
		border-bottom: 1px solid #eaeaea;
		white-space: nowrap; 
	}

	.mdm-tb th {
		background-color: #4B2C20;
		color: #ffffff;
		font-size: 1.05rem;
		font-weight: 700;
	}

	/* 로직 참조 요구사항 반영: td 글씨 굵기 */
	.mdm-tb td {
		font-weight: 700;
		font-size: 0.95rem;
/* 			text-decoration: underline; */
/* 	text-underline-offset: 5px; */
	}

	.mdm-tb tbody tr:nth-child(even) { background-color: #fcfcfc; }
	.mdm-tb tbody tr:hover { background-color: #f1f3f5; }

	.mdm-tb td a:hover {
		color: #5C6BC0;
	}

	/* [5] 페이징 스타일 (paging.jsp에서 사용할 클래스) */
	.page-wrap {
		display: flex;
		justify-content: center;
		margin-top: 2vh;
		gap: 0.5vw;
	}
	
	.page-btn {
		padding: 0.5rem 0.8rem;
		color: #555;
		border-radius: 0.25rem;
		font-weight: bold;
		transition: 0.2s;
	}

	.page-btn:hover {
		background-color: #eaeaea;
	}

	/* 현재 페이지 식별을 위한 서식 */
	.page-active {
		color: #ffffff;
		background-color: #4B2C20;
		font-weight: 900;
		box-shadow: 0 2px 4px rgba(0,0,0,0.2);
	}

	/* [6] 모바일 반응형 (카드형 레이아웃) */
	@media (max-width: 768px) {
		.ctrl-box {
			flex-direction: column;
			align-items: stretch;
		}

		.flt-fm, .sch-fm {
			flex-wrap: wrap;
 			justify-content: space-between; 
/* 			justify-content: center;  */
			width: 100%;
		}
		
		.c-sel, .c-in {
			width: 48%;
			min-width: unset;
		}
		
		.btn-main {
			width: 100%;
			margin-top: 0.5rem;
		}

		.mdm-tb, .mdm-tb tbody, .mdm-tb tr, .mdm-tb td {
			display: block;
			width: 100%;
		}

		.mdm-tb thead {
			display: none;
		}

		.mdm-tb tr {
			margin-bottom: 1rem;
			border: 1px solid #ddd;
			border-radius: 0.5rem;
			padding: 0.5rem;
			box-shadow: 0 2px 5px rgba(0,0,0,0.05);
		}

		.mdm-tb td {
			display: flex;
			justify-content: space-between;
			align-items: center;
			text-align: right;
			border-bottom: 1px solid #eee;
			padding: 0.8rem 0.5rem;
			white-space: normal;
		}

		.mdm-tb td:last-child {
			border-bottom: none;
		}

		.mdm-tb td::before {
			content: attr(data-label);
			font-weight: bold;
			color: #4B2C20;
			text-align: left;
			flex-shrink: 0;
			margin-right: 1rem;
		}

		.btn-wr {
			width: 100%;
			text-align: center;
			float: none;
		}
	}
</style>
</head>
<body>

<%@ include file="/header.jsp" %>

<div class="mdm-all">

    <h1 style="margin-bottom:2vh; color:#4B2C20;">자재관리</h1>

    <div class="tb-wrap">
        <table class="mdm-tb">
            <thead>
                <tr>
                    <th>이름</th>
                    <th>자재총량</th>
                    <th>총금액</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="row" items="${list}">
                    <tr>
                        <td data-label="이름">
                            ${row.name}
                        </td>

                        <td data-label="자재총량">
                            ${row.total_quantity}${ unit }
                        </td>

                        <td data-label="총금액(원)">
                            <fmt:formatNumber value="${row.total_price}" pattern="#,###"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@ include file="/footer.jsp" %>

</body>
</html>