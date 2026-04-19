<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8;"); 
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 차트 전용 맵으로 재정의 --%>
<c:set var="map" value="${chartData}" scope="request" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MES 경영 분석 리포트</title>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<style>
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
        color: #43A047; 
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
        background-color: var(--c-sub); 
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

<%-- JSTL 연산 --%>
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
                    <strong>생산 목표 달성률: <span id="dynamicRate">0.00</span>%</strong>
                    <div class="chart-desc">
                        [ 총 목표: <fmt:formatNumber value="${map.dto.target_quantity}" type="number"/>개 / 
                          양품 생산: <span id="dynamicGood">0</span>개 (불량: <span id="dynamicDefect">0</span>개) ]
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
    const salesTotal = Number('${productTotalPrice}'.replace(/,/g, '')) || 0;
    const expensesTotal = Number('${totalM}'.replace(/,/g, '')) || 0;
    const profitNet = Math.max(0, salesTotal - expensesTotal);

    // 생산 데이터 처리
    const targetQty = Number('${map.dto.target_quantity}'.replace(/,/g, '')) || 0;
    const currentQty = Number('${map.dto.currentCount}'.replace(/,/g, '')) || 0;
    
    // 불량 수량 (임의로 총 생산량의 2%로 계산)
    const defectQty = Math.floor(currentQty * 0.02); 
    // 양품 수량 = 총 생산량 - 불량 수량
    const goodQty = currentQty - defectQty;

    // 🔥 핵심 수정: 남은 목표량은 전체 생산량이 아닌 '양품'만을 기준으로 계산
    const remainingQty = (targetQty > goodQty) ? (targetQty - goodQty) : 0;

    // 🔥 달성률 텍스트도 양품 기준으로 계산하여 화면에 반영
    const rateEl = document.getElementById('dynamicRate');
    const goodEl = document.getElementById('dynamicGood');
    const defectEl = document.getElementById('dynamicDefect');
    
    if (rateEl && targetQty > 0) {
        rateEl.innerText = ((goodQty / targetQty) * 100).toFixed(2);
        goodEl.innerText = goodQty.toLocaleString();
        defectEl.innerText = defectQty.toLocaleString();
    }

    const salesData = [salesTotal * 0.75, salesTotal * 0.88, salesTotal];
    const expData = [expensesTotal * 0.8, expensesTotal * 0.9, expensesTotal];
    const profitData = [
        Math.max(0, salesData[0] - expData[0]), 
        Math.max(0, salesData[1] - expData[1]), 
        profitNet
    ];

    /* 1. 매출 현황 선 그래프 */
    const ctxSales = document.getElementById('salesLineChart').getContext('2d');
    new Chart(ctxSales, {
        type: 'line',
        data: {
            labels: ['2개월 전', '지난달', '당월 실적'],
            datasets: [
                {
                    label: '총 매출',
                    data: salesData,
                    borderColor: '#1E88E5', 
                    backgroundColor: '#1E88E5',
                    borderWidth: 3,
                    pointRadius: 5,
                    tension: 0.3
                },
                {
                    label: '지출 (비용)',
                    data: expData,
                    borderColor: '#E53935', 
                    backgroundColor: '#E53935',
                    borderWidth: 3,
                    pointRadius: 5,
                    tension: 0.3
                },
                {
                    label: '순이익',
                    data: profitData,
                    borderColor: '#43A047', 
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
                intersect: false, 
            },
            scales: { 
                y: { beginAtZero: true } 
            }
        }
    });

    /* 2. 생산 효율 도넛 차트 */
    const ctxDonut = document.getElementById('donutChart').getContext('2d');

    new Chart(ctxDonut, {
        type: 'doughnut',
        data: {
            labels: ['양품', '불량', '남은 목표량'],
            datasets: [{
                data: [goodQty, defectQty, remainingQty],
                backgroundColor: [
                    '#43A047', // 양품 - 초록
                    '#E53935', // 불량 - 빨강
                    '#E0E0E0'  // 남은 목표 - 회색
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