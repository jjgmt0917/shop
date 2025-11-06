<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>shop</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
</head>
<body>
	<c:import url="/WEB-INF/view/inc/customerMenu.jsp"></c:import>
	<h1>addressList</h1>
	<div>
		<a href="${pageContext.request.contextPath}/customer/addAddress">배송지 추가</a>
		<!-- 배송지 최대 5개 : 6번째 입력시 가장 오래된 데이터 삭제 후 입력(트랜잭션) -->
	</div>
</body>
</html>