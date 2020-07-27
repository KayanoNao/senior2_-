<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
	<th><h4>list</h4></th>
</tr>
	<tr>
		<th>ID</th>
		<th>名称</th>
		<th>价格</th>
		<th>已售百分比</th>
	</tr>
	<c:forEach var="s" items="${list}">
		<tr>
			<td>${s.id}</td>
			<td>${s.name}</td>
			<td>${s.price}</td>
			<td>${s.yishou}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>