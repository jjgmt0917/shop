<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>shop</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@4.5.0"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import>
	<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
	<h1>매출 통계</h1>
	<input type="text" id="fromYM" value="2025-01-01">
	~
	<input type="text" id="toYM" value="2025-12-31">
	<button type="button" id="order">특정년도의 월별 주문횟수(누적) : 선차트</button>
	<button type="button" id="price">특정년도의 월별 주문금액(누적) : 선차트</button>
	<button type="button" id="">특정년도의 월별 주문 수량 : 막대</button>
	<button type="button" id="">특정년도의 월별 주문 금액 : 막대</button>
	<button type="button" id="">고객별 주문 횟수 1위 ~ 10위 : 막대차트</button>
	<button type="button" id="">고객별 총금액 1위 ~ 10위 : 막대차트</button>
	<button type="button" id="">상품별 주문횟수 1위 ~ 10위 : 막대차트</button>
	<button type="button" id="">상품별 주문금액 1위 ~ 10위 : 막대차트</button>
	<button type="button" id="">상품별 평균 리뷰평점 1위 ~ 10위 : 막대차트</button>
	<button type="button" id="">성별 총 주문 금액 : 파이차트</button>
	<button type="button" id="">성별 총 주문 수량 : 파이차트</button>
	
	<canvas id="myChart"style="width:100%;max-width:700px"></canvas>
	<script>
		let myChart = null;
		
		$('#order').click(function() {
			// alert('orderAndPrice클릭');
			$.ajax({
				url : $('#contextPath').val()+'/totalOrder',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {	// result --> list
					console.log(result);
					$('#myChart').empty();
					
					let x = [];
					let y = [];
					
					result.forEach(function(m) {
						x.push(m.ym);
						y.push(m.totalOrder);
					});
					
					if(myChart != null) {
						myChart.destroy();
					}
					
					myChart = new Chart("myChart", {
						type: "line",
						data: {
							labels: x,
							datasets: [{
								label: ($('#fromYM').val()).slice(0, 4) + '년도 주문량 추이(누적)',
							    data: y,
							    borderColor: "red",
							    fill: false,
							}]
						},
						options: {
							legend: {display: false}
						}
					});
				},
			});
		});
		
		$('#price').click(function() {
			// alert('orderAndPrice클릭');
			$.ajax({
				url : $('#contextPath').val()+'/totalPrice',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {	// result --> list
					console.log(result);
					$('#myChart').empty();
					
					let x = [];
					let y = [];
					
					result.forEach(function(m) {
						x.push(m.ym);
						y.push(m.totalPrice);
					});
					
					if(myChart != null) {
						myChart.destroy();
					}
					
					myChart = new Chart("myChart", {
						type: "line",
						data: {
							labels: x,
							datasets: [{
								label: ($('#fromYM').val()).slice(0, 4) + '년도 총 판매 금액 추이(누적)',
							    data: y,
							    borderColor: "blue",
							    fill: false,
							}]
						},
						options: {
							legend: {display: false}
						}
					});
				},
			});
		});
	</script>
</body>
</html>