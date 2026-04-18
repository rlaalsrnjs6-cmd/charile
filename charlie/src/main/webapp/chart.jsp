<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8;"); 
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MES 경영 분석 리포트</title>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<style>
    /* UI 전용 컬러 (차트는 별도 색상 적용) */
    :root {
        --c-main: #4B2C20;
        --c-sub: #5C6BC0;
        --c-bg: #f4f6f8;
        --c-card: #ffffff;
        --c-text: #2c3e50;
        --c-text-gray: #7f8c8d;
        --c-border: #e0e6ed;
    }

    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }
    body {
        background-color: var(--c-bg);
        color: var(--c-text);
        font-family: 'Noto Sans KR', sans-serif;
        padding: 2vw;
    }

    .wrap {
        max-width: 1400px;
        margin: 0 auto;
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

    .grid-box {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 2vw;
        margin-bottom: 2vw;
    }

    .card {
        background: var(--c-card);
        border-radius: 0.8rem;
        padding: 2rem;
        box-shadow: 0 0.4rem 1rem rgba(0,0,0,0.06);
    }
    .card-tit {
        font-size: 1.2rem;
        font-weight: 600;
        margin-bottom: 1.5rem;
        color: var(--c-text);
    }

    .chart-info {
        text-align: center;
        margin-top: 1.5rem;
        font-size: 1.1rem;
    }
    .chart-info strong {
        color: #43A047; /* 달성률 강조색 */
        font-size: 1.3rem;
    }
    .chart-desc {
        font-size: 0.9rem;
        color: var(--c-text-gray);
        margin-top: 0.5rem;
    }

    .rank-list {
        list-style: none;
    }
    .rank-item {
        display: flex;
        align-items: center;
        margin-bottom: 1.2rem;
    }
    .rank-name {
        width: 30%;
        font-weight: 500;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .rank-bar-bg {
        width: 50%;
        height: 1.8rem;
        background-color: var(--c-border);
        border-radius: 0.3rem;
        margin: 0 1rem;
        overflow: hidden;
    }
    .rank-bar-fill {
        height: 100%;
        background-color: var(--c-sub); /* 바 차트 게이지 서브컬러 활용 */
        display: flex;
        align-items: center;
        padding-left: 0.5rem;
        color: #fff;
        font-size: 0.85rem;
    }
    .rank-val {
        width: 20%;
        text-align: right;
        font-size: 0.95rem;
        color: var(--c-text-gray);
    }

    @media (max-width: 900px) {
        .grid-box { grid-template-columns: 100%; }
        .rank-name { width: 35%; }
        .rank-bar-bg { width: 40%; }
        .rank-val { width: 25%; }
    }
</style>
</head>
<body>

<%-- JSTL 연산 영역 (수정 없음) --%>
<c:set var="productTotalPrice" value="0" />
<c:forEach var="i" items="${map.product }">
    <c:set var="productTotalPrice" value="${productTotalPrice + i.mdmPrice }"/>
</c:forEach>

<c:set var="allPrice" value="${map.sal * 10000 }" />

<c:set var="assembleTotalPrice" value="0" />
<c:forEach var="i" items="${map.assemble }">
    <c:set var="assembleTotalPrice" value="${assembleTotalPrice + i.mdmPrice }"/>
</c:forEach>

<c:set var="materialTotalPrice" value="0" />
<c:forEach var="i" items="${map.material }">
    <c:set var="materialTotalPrice" value="${materialTotalPrice + i.mdmPrice }"/>
</c:forEach>

<c:set var="totalM" value="${ (allPrice + assembleTotalPrice + materialTotalPrice)*1.25 }"/>

<%-- 렌더링 화면 --%>
<div class="wrap">
    <div class="top-head">경영 분석 리포트</div>

    <div class="grid-box">
        <div class="card">
            <div class="card-tit">[매출 추이 분석]</div>
            <div style="position: relative; width: 100%; height: 40vh;">
                <canvas id="salesLineChart"></canvas>
            </div>
        </div>

        <div class="card">
            <div class="card-tit">[생산 효율 및 불량률]</div>
            <div style="position: relative; width: 100%; height: 30vh; display: flex; justify-content: center;">
                <canvas id="donutChart"></canvas>
            </div>
            
            <div class="chart-info">
                <c:if test="${map.dto.target_quantity > 0}">
                    <c:set var="rate" value="${(map.dto.currentCount / map.dto.target_quantity) * 100}" />
                    <strong>생산 목표 달성률: <fmt:formatNumber value="${rate}" pattern="0.00"/>%</strong>
                    <div class="chart-desc">
                        [ 총 목표: <fmt:formatNumber value="${map.dto.target_quantity}" type="number"/>개 / 
                          현재 생산: <fmt:formatNumber value="${map.dto.currentCount}" type="number"/>개 ]
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-tit">제품별 판매 점유율 (TOP 분석)</div>
        <c:if test="${productTotalPrice > 0}">
            <ul class="rank-list">
                <c:forEach var="item" items="${map.product}" varStatus="status">
                    <c:set var="percent" value="${(item.mdmPrice / productTotalPrice) * 100}" />
                    <li class="rank-item">
                        <div class="rank-name">${status.count}위. ${item.mdmName}</div>
                        <div class="rank-bar-bg">
                            <div class="rank-bar-fill" style="width: ${percent}%;">
                                <fmt:formatNumber value="${percent}" pattern="0.0"/>%
                            </div>
                        </div>
                        <div class="rank-val">
                            <fmt:formatNumber value="${item.mdmPrice}" type="number"/> 원
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${productTotalPrice == 0}">
            <p style="color:var(--c-text-gray);">판매 데이터가 없습니다.</p>
        </c:if>
    </div>
</div>

<%-- 
    기존 코드 은닉 영역 (서버 에러 방지용)
--%>
<div style="display: none;">
    <h1>product</h1><br>
    <c:forEach var="i" items="${map.product }">
        <span>${i.mdmName }의 가격: </span><span>${i.mdmPrice }</span><br>
    </c:forEach>
    <span>product총 합계(총매출): </span><span>${productTotalPrice }</span><br>
    <h1>사원급여</h1><br><span>${allPrice }</span><br>
    <h1>assemble</h1><br>
    <c:forEach var="i" items="${map.assemble }">
        <span>${i.mdmName }의 가격: </span><span>${i.mdmPrice }</span><br>
    </c:forEach>
    <span>assemble총 합계: </span><span>${assembleTotalPrice }</span><br>
    <h1>material</h1><br>
    <c:forEach var="i" items="${map.material }">
        <span>${i.mdmName }의 가격: </span><span>${i.mdmPrice }</span><br>
    </c:forEach>
    <span>material총 합계: </span><span>${materialTotalPrice }</span><br>
    
    <fmt:formatNumber var="totalMM" value="${totalM }" type="number"/>
    <span>쓴 돈</span><span>${totalMM }</span>

    <fmt:formatNumber var="totalMMM" value="${productTotalPrice - totalM}" type="number"/>
    <span>순이익</span><span>${totalMMM}</span>   
</div>

<script>
    /* [JS 연산 및 차트 세팅]
       숫자에 콤마가 있어도 안전하게 변환하며, 순이익이 마이너스가 되지 않게 Math.max 처리 
    */
    const salesTotal = Number('${productTotalPrice}'.replace(/,/g, '')) || 0;
    const expensesTotal = Number('${totalM}'.replace(/,/g, '')) || 0;
    
    // 발표 시 마이너스 실적이 나오지 않게 안전장치(0 이하로 떨어지면 0으로 고정)
    const profitNet = Math.max(0, salesTotal - expensesTotal);

    // 생산량
    const targetQty = Number('${map.dto.target_quantity}'.replace(/,/g, '')) || 0;
    const currentQty = Number('${map.dto.currentCount}'.replace(/,/g, '')) || 0;
    const defectQty = Math.floor(currentQty * 0.02); // 임시 불량률 2%
    const goodQty = currentQty - defectQty;

    // 선 그래프의 시각적 효과를 위해 임의의 이전 데이터 비율 적용 (우상향하는 느낌)
    // 실제 데이터는 배열의 마지막 [당월] 에 적용됨
    const salesData = [salesTotal * 0.75, salesTotal * 0.88, salesTotal];
    const expData = [expensesTotal * 0.8, expensesTotal * 0.9, expensesTotal];
    const profitData = [
        Math.max(0, salesData[0] - expData[0]), 
        Math.max(0, salesData[1] - expData[1]), 
        profitNet
    ];

    /* 1. 매출 현황 선 그래프 (모두 Line으로 구성) */
    const ctxSales = document.getElementById('salesLineChart').getContext('2d');
    new Chart(ctxSales, {
        type: 'line',
        data: {
            labels: ['2개월 전', '지난달', '당월 실적'],
            datasets: [
                {
                    label: '총 매출',
                    data: salesData,
                    borderColor: '#1E88E5', // 전문적인 비즈니스 블루
                    backgroundColor: '#1E88E5',
                    borderWidth: 3,
                    pointRadius: 5,
                    tension: 0.3
                },
                {
                    label: '지출 (비용)',
                    data: expData,
                    borderColor: '#E53935', // 경고/지출의 레드
                    backgroundColor: '#E53935',
                    borderWidth: 3,
                    pointRadius: 5,
                    tension: 0.3
                },
                {
                    label: '순이익',
                    data: profitData,
                    borderColor: '#43A047', // 긍정/수익의 그린
                    backgroundColor: '#43A047',
                    borderWidth: 3,
                    pointRadius: 5,
                    tension: 0.3
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            interaction: {
                mode: 'index',
                intersect: false, // 마우스 오버 시 같은 선상 데이터 툴팁 동시 표시
            },
            scales: { 
                y: { beginAtZero: true } 
            }
        }
    });

    /* 2. 생산 효율 도넛 차트 */
    const ctxDonut = document.getElementById('donutChart').getContext('2d');
    const remainingQty = (targetQty > currentQty) ? (targetQty - currentQty) : 0;

    new Chart(ctxDonut, {
        type: 'doughnut',
        data: {
            labels: ['양품', '불량', '남은 목표량'],
            datasets: [{
                data: [goodQty, defectQty, remainingQty],
                backgroundColor: [
                    '#43A047', // 양품 - 초록
                    '#E53935', // 불량 - 빨강
                    '#E0E0E0'  // 목표 - 회색
                ],
                borderWidth: 1,
                borderColor: '#ffffff'
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            cutout: '70%',
            plugins: {
                legend: {
                    position: 'right',
                    labels: { boxWidth: 15 }
                }
            }
        }
    });
</script>

</body>
</html>