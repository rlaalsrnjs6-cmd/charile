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

<%-- JSTL 연산 영역 --%>
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
        <div class="card-tit">제품별 판매 점유율 (TOP3 분석)</div>
        <ul class="rank-list" id="jsRankList">
            </ul>
    </div>
</div>

<script>
    // 1. 제품 데이터를 배열에 따로 담기
    const productArr = [];
    <c:forEach var="item" items="${map.product}">
        productArr.push({
            mdmName: "${item.mdmName}",
            mdmPrice: Number("${item.mdmPrice}")
        });
    </c:forEach>

    // 2. price 높은 순으로 정렬
    productArr.sort((a, b) => b.mdmPrice - a.mdmPrice);

    // 3. 상위 3개 출력
    const totalPPrice = Number('${productTotalPrice}') || 1;
    const rankUl = document.getElementById('jsRankList');
    let rankHtml = '';

    if (productArr.length > 0) {
        // TOP 3만 추출
        const top3 = productArr.slice(0, 3);
        top3.forEach((item, index) => {
            const percent = ((item.mdmPrice / totalPPrice) * 100).toFixed(1);
            rankHtml += `
                <li class="rank-item">
                    <div class="rank-name">\${index + 1}위. \${item.mdmName}</div>
                    <div class="rank-bar-bg">
                        <div class="rank-bar-fill" style="width: \${percent}%;">
                            \${percent}%
                        </div>
                    </div>
                    <div class="rank-val">
                        \${item.mdmPrice.toLocaleString()} 원
                    </div>
                </li>`;
        });
        rankUl.innerHTML = rankHtml;
    } else {
        rankUl.innerHTML = '<p style="color:var(--c-text-gray);">판매 데이터가 없습니다.</p>';
    }

    // --- 나머지 차트 로직 (매출추이 interaction 복구) ---
    const salesTotal = Number('${productTotalPrice}'.replace(/,/g, '')) || 0;
    const expensesTotal = Number('${totalM}'.replace(/,/g, '')) || 0;
    const profitNet = Math.max(0, salesTotal - expensesTotal);

    const targetQty = Number('${map.dto.target_quantity}'.replace(/,/g, '')) || 0;
    const currentQty = Number('${map.dto.currentCount}'.replace(/,/g, '')) || 0;
    const defectQty = Math.floor(currentQty * 0.02); 
    const goodQty = currentQty - defectQty;
    const remainingQty = (targetQty > goodQty) ? (targetQty - goodQty) : 0;

    const rateEl = document.getElementById('dynamicRate');
    if (rateEl && targetQty > 0) {
        rateEl.innerText = ((goodQty / targetQty) * 100).toFixed(2);
        document.getElementById('dynamicGood').innerText = goodQty.toLocaleString();
        document.getElementById('dynamicDefect').innerText = defectQty.toLocaleString();
    }

    const salesData = [salesTotal * 0.75, salesTotal * 0.88, salesTotal];
    const expData = [expensesTotal * 0.8, expensesTotal * 0.9, expensesTotal];
    const profitData = [
        Math.max(0, salesData[0] - expData[0]), 
        Math.max(0, salesData[1] - expData[1]), 
        profitNet
    ];

    /* 매출 현황 선 그래프 - interaction 옵션 원복 */
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
                mode: 'index',   // 마우스 근처 영역만 가도 정보 표시
                intersect: false, // 정확히 포인트에 안 올라가도 표시
            },
            scales: { 
                y: { beginAtZero: true } 
            }
        }
    });

    /* 생산 효율 도넛 차트 */
    const ctxDonut = document.getElementById('donutChart').getContext('2d');
    new Chart(ctxDonut, {
        type: 'doughnut',
        data: {
            labels: ['양품', '불량', '남은 목표량'],
            datasets: [{
                data: [goodQty, defectQty, remainingQty],
                backgroundColor: ['#43A047', '#E53935', '#E0E0E0'],
                borderWidth: 1,
                borderColor: '#ffffff'
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            cutout: '70%',
            plugins: {
                legend: { position: 'right', labels: { boxWidth: 15 } }
            }
        }
    });
</script>

</body>
</html>