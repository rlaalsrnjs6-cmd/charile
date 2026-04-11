<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
</head>
<body>

<h1>process list</h1>
<hr>
1. img 태그에 \${ el 태그 }로 넣어서 이미지 띄우기 <br>
2. 공정 순서 nextval 사용해서 insert 시 자동으로 다음 순서 만들게 할 지? <br>
3. mdm_num select해서 보여주기. FK라 값 넣기 까다로움 <br>
4. mdm FK 로 type이 제품인 것들만 가져오기. <br>
		가져올 DB 정보: 제품명칭(name), 코드번호(code)
<hr>

<table border="1px">
		<thead>
			<tr>
				<th>process_num</th>
				<th>process_content</th>
				<th>flow</th>
				<th>img_url</th>
				<th>mdm_num</th>
			</tr>
		</thead>

		<c:forEach var="row" items="${ list }">
			<tr>
				<td>
					${ row.process_num }
				</td>
				<td>${ row.process_content }</td>
				<td>${ row.flow }</td>
				
				<td>
					<a href="process?cmd=detail&process_num=${ row.process_num }">
						${ row.img_url }
					</a>
				</td>
				<td>${ row.mdm_num }</td>
			</tr>
		</c:forEach>
		
		</table>
		
		<hr>
	<a href="process?cmd=insertPage">등록페이지로</a>
</body>
</html>