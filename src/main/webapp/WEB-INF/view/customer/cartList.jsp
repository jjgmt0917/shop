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
	<form>
		<table>
			<tr>
				<th>선택</th>
				<th>상품이름</th>
				<th>상품가격</th>
				<th>수량</th>
				<th>총가격</th>
			</tr>
			<c:forEach var="m" items="${list}">
				<tr>
					<td>
						<c:if test="${m.soldout == 'soldout'}">
							&nbsp;
						</c:if>
						<c:if test="${m.soldout != 'soldout'}">
							<input type="checkbox" name="ck" value="${m.cartCode}">
						</c:if>
					</td>
					<td>${m.goodsName}</td>
					<td>${m.goodsPrice}</td>
					<td>${m.goodsQuantity}</td>
					<td>${m.totalPrice}</td>
				</tr>
			</c:forEach>
		</table>
		<button type="button" id="">구매하기</button>
	</form>
</body>
</html>