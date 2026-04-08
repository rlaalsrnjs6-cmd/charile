<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div{
width: 150px;
height: 150px;
margin: 30px;
}

.mainColor{
background-color: #4B2C20;
}

.subColor{
background-color: #5C6BC0;
}

.activeGreenColor{
background-color: #2E7D32;
}

.activeYellowColor{
background-color: #FBC02D;
}

.alertRedColor{
background-color: #C62828;
}

.backgroundGrey{
background-color: #F5F5F5;
}

.surfaceWhite{
background-color: #FFFFFF;
}

.softBorder{
background-color: #E0E0E0;
}
</style>
</head>
<body>
<div class="mainColor">메인 컬러</div>

<div class="subColor">서브 컬러</div>

<div class="activeGreenColor">상태:정상 컬러</div>
<div class="activeYellowColor">상태:경고 컬러</div>
<div class="alertRedColor">상태:위험 컬러</div>

<div class="backgroundGrey">배경 / 무채색 / 대시보드 전체 배경</div>
<div class="surfaceWhite">배경 / 무채색 / 데이터 입력되는 카드, 입력 폼</div>

<div class="softBorder">구분선</div>
</body>
</html>