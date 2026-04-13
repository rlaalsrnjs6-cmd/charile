<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
    <style>
        body{
            margin: 0px;
        }
        a{
            text-decoration: none;
        }
        #header-all {
            width: 100%;
            height: 140px;
            /* border: 1px solid red; */
        }
        .util-menu{
            width: 100%;
            height: 30px;
            /* border: 1px solid blue; */
            
        }

        .util-list{
            /* border: 1px solid yellow; */
            box-sizing: border-box;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            list-style: none;
            margin: 0;
            padding: 0 20px;
            gap: 15px;
            background-color: #4B2C20;
        }
        .util-list a{
            color: white;
        }

        .header-bar{
            width: 100%;
            height: 110px;
            border-bottom: 2px solid #4B2C20;
            display: flex;
            align-items: center;
        }

        .logo-a{
            height: 100%;
        }

        .logo{
            margin: 0;
            height: 100%;
            display: flex;
            align-items: center;
            /* padding-left: 20px; */
        }
    .logo img {
    height: 100%;        
    width: auto;         
    display: block;
}

.header-ul{
    display: flex;
    gap: 50px;
    list-style: none;
    font-size: 2rem;
    margin-left: 100px;
}

.header-ul a{
    color: black;
}
    </style>
</head>
<body>
<%@ include file="/header.jsp" %>
    <div id="header-all">
    <div class="util-menu">
        <ul class="util-list">
            <li><a href="">로그인</a></li>
            <li><a href="">회원가입</a></li>
            <li><a href="">마이페이지</a></li>
            <li><a href="http://localhost:8080/charlie/charlie?mod=logout">로그아웃</a></li>
            <li><a href="">카테고리 관리</a></li>
        </ul>
    </div>

        <div class="header-bar">
            <h1 class="logo">
                <a class="logo-a" href="/">
                    <img src="${pageContext.request.contextPath}/assets/img/로고-배경제거.png" alt="찰리 MES">
                </a>
            </h1>
            <ul class="header-ul">
                <li><a href="">기준관리</a></li>
                <li><a href="http://localhost:8080/charlie/material">자재관리</a></li>
                <li><a href="http://localhost:8080/charlie/order">작업지시서</a></li>
                <li><a href="http://localhost:8080/charlie/production/management">생산관리</a></li>
                <li><a href="http://localhost:8080/charlie/qc">품질관리</a></li>
                <li><a href="">게시판</a></li>
            </ul>
        </div>
    </div>


</div>
<%@ include file="/footer.jsp" %>
</body>
</html>