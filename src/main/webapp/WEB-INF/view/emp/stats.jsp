<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>매출 통계</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@4.5.0"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/nav.css" rel="stylesheet">
	<style>
		.main-container {
			max-width: 1400px;
			margin: 0 auto;
			padding: 40px 20px;
		}
		
		.page-title {
			font-size: 28px;
			font-weight: 600;
			color: #333;
			margin-bottom: 30px;
		}
		
		.stats-card {
			background-color: white;
			border-radius: 8px;
			padding: 30px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			margin-bottom: 30px;
		}
		
		.date-section {
			display: flex;
			align-items: center;
			gap: 15px;
			margin-bottom: 30px;
			padding-bottom: 20px;
			border-bottom: 2px solid #f0f0f0;
		}
		
		.date-label {
			font-weight: 500;
			color: #333;
			font-size: 14px;
		}
		
		.date-input {
			padding: 10px 15px;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 14px;
			color: #333;
		}
		
		.date-input:focus {
			outline: none;
			border-color: #999;
		}
		
		.section-title {
			font-size: 18px;
			font-weight: 600;
			color: #333;
			margin-bottom: 15px;
			padding-left: 10px;
			border-left: 4px solid #000;
		}
		
		.button-grid {
			display: grid;
			grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
			gap: 12px;
			margin-bottom: 25px;
		}
		
		.stat-btn {
			padding: 14px 20px;
			background-color: white;
			color: #333;
			border: 1px solid #ddd;
			border-radius: 6px;
			font-size: 14px;
			font-weight: 500;
			cursor: pointer;
			transition: all 0.3s;
			text-align: left;
			display: flex;
			align-items: center;
			gap: 10px;
		}
		
		.stat-btn:hover {
			background-color: #000;
			color: white;
			border-color: #000;
			transform: translateY(-2px);
			box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
		}
		
		.stat-btn::before {
			content: '📊';
			font-size: 18px;
		}
		
		.stat-btn.line::before {
			content: '📈';
		}
		
		.stat-btn.bar::before {
			content: '📊';
		}
		
		.stat-btn.pie::before {
			content: '🥧';
		}
		
		.chart-container {
			background-color: white;
			border-radius: 8px;
			padding: 40px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			display: flex;
			justify-content: center;
			align-items: center;
			min-height: 500px;
		}
		
		.empty-chart {
			text-align: center;
			color: #999;
		}
		
		.empty-chart-icon {
			font-size: 64px;
			margin-bottom: 20px;
		}
		
		.empty-chart-text {
			font-size: 16px;
			font-weight: 500;
		}
		
		@media (max-width: 768px) {
			.button-grid {
				grid-template-columns: 1fr;
			}
			
			.date-section {
				flex-direction: column;
				align-items: flex-start;
			}
		}
	</style>
</head>
<body>
	<!-- 네비게이션 -->
	<jsp:include page="/WEB-INF/view/inc/empMenu.jsp"/>
	
	<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
	
	<div class="main-container">
		<h1 class="page-title">매출 통계</h1>
		
		<div class="stats-card">
			<!-- 날짜 선택 -->
			<div class="date-section">
				<span class="date-label">조회 기간</span>
				<input type="date" class="date-input" id="fromYM" value="2025-01-01">
				<span>~</span>
				<input type="date" class="date-input" id="toYM" value="2025-12-31">
			</div>
			
			<!-- 월별 통계 -->
			<div class="section-title">월별 통계</div>
			<div class="button-grid">
				<button type="button" class="stat-btn line" id="totalOrderBtn">
					특정년도의 월별 주문횟수(누적)
				</button>
				<button type="button" class="stat-btn line" id="totalPriceBtn">
					특정년도의 월별 주문금액(누적)
				</button>
				<button type="button" class="stat-btn bar" id="orderBtn">
					특정년도의 월별 주문 수량
				</button>
				<button type="button" class="stat-btn bar" id="priceBtn">
					특정년도의 월별 주문 금액
				</button>
			</div>
			
			<!-- 고객 랭킹 -->
			<div class="section-title">고객 랭킹</div>
			<div class="button-grid">
				<button type="button" class="stat-btn bar" id="customerOrderRankBtn">
					고객별 주문 횟수 TOP 10
				</button>
				<button type="button" class="stat-btn bar" id="customerPriceRankBtn">
					고객별 총금액 TOP 10
				</button>
			</div>
			
			<!-- 상품 랭킹 -->
			<div class="section-title">상품 랭킹</div>
			<div class="button-grid">
				<button type="button" class="stat-btn bar" id="goodsOrderRankBtn">
					상품별 주문횟수 TOP 10
				</button>
				<button type="button" class="stat-btn bar" id="goodsPriceRankBtn">
					상품별 주문금액 TOP 10
				</button>
				<button type="button" class="stat-btn bar" id="reviewRankBtn">
					상품별 평균 리뷰평점 TOP 10
				</button>
			</div>
			
			<!-- 성별 통계 -->
			<div class="section-title">성별 통계</div>
			<div class="button-grid">
				<button type="button" class="stat-btn pie" id="genderOrderBtn">
					성별 총 주문 수량
				</button>
				<button type="button" class="stat-btn pie" id="genderPriceBtn">
					성별 총 주문 금액
				</button>
			</div>
		</div>
		
		<!-- 차트 영역 -->
		<div class="chart-container">
			<div class="empty-chart" id="emptyChart">
				<div class="empty-chart-icon">📊</div>
				<div class="empty-chart-text">통계 항목을 선택하면 차트가 표시됩니다</div>
			</div>
			<canvas id="myChart" style="display:none; max-width:100%; max-height:500px;"></canvas>
		</div>
	</div>
	
	<script>
		let myChart = null;
		
		function showChart() {
			$('#emptyChart').hide();
			$('#myChart').show();
		}
		
		$('#totalOrderBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/totalOrder',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
					
					let x = [];
					let y = [];
					
					result.forEach(function(m) {
						x.push(m.ym);
						y.push(m.totalOrder);
					});
					
					myChart = new Chart("myChart", {
						type: "line",
						data: {
							labels: x,
							datasets: [{
								label: ($('#fromYM').val()).slice(0, 4) + '년도 주문량 추이(누적)',
							    data: y,
							    borderColor: "#000",
							    backgroundColor: "rgba(0, 0, 0, 0.1)",
							    fill: true,
							    tension: 0.4
							}]
						},
						options: {
							responsive: true,
							maintainAspectRatio: true,
							plugins: {
								legend: {display: true}
							}
						}
					});
				},
			});
		});
		
		$('#totalPriceBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/totalPrice',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
					
					let x = [];
					let y = [];
					
					result.forEach(function(m) {
						x.push(m.ym);
						y.push(m.totalPrice);
					});
					
					myChart = new Chart("myChart", {
						type: "line",
						data: {
							labels: x,
							datasets: [{
								label: ($('#fromYM').val()).slice(0, 4) + '년도 총 판매 금액 추이(누적)',
							    data: y,
							    borderColor: "#000",
							    backgroundColor: "rgba(0, 0, 0, 0.1)",
							    fill: true,
							    tension: 0.4
							}]
						},
						options: {
							responsive: true,
							maintainAspectRatio: true,
							plugins: {
								legend: {display: true}
							}
						}
					});
				},
			});
		});
		
		$('#orderBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/order',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
				
					let xValues = [];
					let yValues = [];
					
					result.forEach(function(m) {
						xValues.push(m.ym);
						yValues.push(m.orderCnt);
					});
					
					let ctx = document.getElementById('myChart');

					myChart = new Chart(ctx, {
					  type: "bar",
					  data: {
					    labels: xValues,
					    datasets: [{
					      label: '주문 횟수',
					      backgroundColor: "#000",
					      data: yValues
					    }]
					  },
					  options: {
					    responsive: true,
					    maintainAspectRatio: true,
					    plugins: {
					      legend: {display: false},
					      title: {
					        display: true,
					        text: ($('#fromYM').val()).slice(0, 4) + '년도 월별 주문횟수 추이',
					        font: {size: 18, weight: 'bold'}
					      }
					    }
					  }
					});
				},
			});
		});
		
		$('#priceBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/price',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
				
					let xValues = [];
					let yValues = [];
					
					result.forEach(function(m) {
						xValues.push(m.ym);
						yValues.push(m.price);
					});
					
					let ctx = document.getElementById('myChart');

					myChart = new Chart(ctx, {
					  type: "bar",
					  data: {
					    labels: xValues,
					    datasets: [{
					      label: '주문 금액',
					      backgroundColor: "#000",
					      data: yValues
					    }]
					  },
					  options: {
					    responsive: true,
					    maintainAspectRatio: true,
					    plugins: {
					      legend: {display: false},
					      title: {
					        display: true,
					        text: ($('#fromYM').val()).slice(0, 4) + '년도 월별 주문금액 추이',
					        font: {size: 18, weight: 'bold'}
					      }
					    }
					  }
					});
				},
			});
		});
		
		$('#customerOrderRankBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/customerOrderRank',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
				
					let xValues = [];
					let yValues = [];
					
					result.forEach(function(m) {
						xValues.push(m.customerCode);
						yValues.push(m.cnt);
					});
					
					let ctx = document.getElementById('myChart');

					myChart = new Chart(ctx, {
					  type: "bar",
					  data: {
					    labels: xValues,
					    datasets: [{
					      label: '주문 횟수',
					      backgroundColor: "#000",
					      data: yValues
					    }]
					  },
					  options: {
					    responsive: true,
					    maintainAspectRatio: true,
					    indexAxis: 'y',
					    plugins: {
					      legend: {display: false},
					      title: {
					        display: true,
					        text: '고객별 주문 횟수 TOP 10',
					        font: {size: 18, weight: 'bold'}
					      }
					    }
					  }
					});
				},
			});
		});
		
		$('#customerPriceRankBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/customerPriceRank',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
				
					let xValues = [];
					let yValues = [];
					
					result.forEach(function(m) {
						xValues.push(m.customerCode);
						yValues.push(m.price);
					});
					
					let ctx = document.getElementById('myChart');

					myChart = new Chart(ctx, {
					  type: "bar",
					  data: {
					    labels: xValues,
					    datasets: [{
					      label: '주문 금액',
					      backgroundColor: "#000",
					      data: yValues
					    }]
					  },
					  options: {
					    responsive: true,
					    maintainAspectRatio: true,
					    indexAxis: 'y',
					    plugins: {
					      legend: {display: false},
					      title: {
					        display: true,
					        text: '고객별 주문 금액 TOP 10',
					        font: {size: 18, weight: 'bold'}
					      }
					    }
					  }
					});
				},
			});
		});
		
		$('#goodsOrderRankBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/goodsOrderRank',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
				
					let xValues = [];
					let yValues = [];
					
					result.forEach(function(m) {
						xValues.push(m.goodsCode);
						yValues.push(m.cnt);
					});
					
					let ctx = document.getElementById('myChart');

					myChart = new Chart(ctx, {
					  type: "bar",
					  data: {
					    labels: xValues,
					    datasets: [{
					      label: '주문 횟수',
					      backgroundColor: "#000",
					      data: yValues
					    }]
					  },
					  options: {
					    responsive: true,
					    maintainAspectRatio: true,
					    indexAxis: 'y',
					    plugins: {
					      legend: {display: false},
					      title: {
					        display: true,
					        text: '상품별 주문 횟수 TOP 10',
					        font: {size: 18, weight: 'bold'}
					      }
					    }
					  }
					});
				},
			});
		});
		
		$('#goodsPriceRankBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/goodsPriceRank',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
				
					let xValues = [];
					let yValues = [];
					
					result.forEach(function(m) {
						xValues.push(m.goodsCode);
						yValues.push(m.price);
					});
					
					let ctx = document.getElementById('myChart');

					myChart = new Chart(ctx, {
					  type: "bar",
					  data: {
					    labels: xValues,
					    datasets: [{
					      label: '주문 금액',
					      backgroundColor: "#000",
					      data: yValues
					    }]
					  },
					  options: {
					    responsive: true,
					    maintainAspectRatio: true,
					    indexAxis: 'y',
					    plugins: {
					      legend: {display: false},
					      title: {
					        display: true,
					        text: '상품별 주문 금액 TOP 10',
					        font: {size: 18, weight: 'bold'}
					      }
					    }
					  }
					});
				},
			});
		});
		
		$('#reviewRankBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/reviewAvgRank',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
				
					let xValues = [];
					let yValues = [];
					
					result.forEach(function(m) {
						xValues.push(m.goodsCode);
						yValues.push(m.average);
					});
					
					let ctx = document.getElementById('myChart');

					myChart = new Chart(ctx, {
					  type: "bar",
					  data: {
					    labels: xValues,
					    datasets: [{
					      label: '평균 평점',
					      backgroundColor: "#000",
					      data: yValues
					    }]
					  },
					  options: {
					    responsive: true,
					    maintainAspectRatio: true,
					    indexAxis: 'y',
					    plugins: {
					      legend: {display: false},
					      title: {
					        display: true,
					        text: '상품별 평균 리뷰 평점 TOP 10',
					        font: {size: 18, weight: 'bold'}
					      }
					    }
					  }
					});
				},
			});
		});
		
		$('#genderOrderBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/genderOrder',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
				
					let xValues = [];
					let yValues = [];
					let barColors = ["#000", "#666"];
					
					result.forEach(function(m) {
						xValues.push(m.gender);
						yValues.push(m.cnt);
					});
					
					let ctx = document.getElementById('myChart');

					myChart = new Chart(ctx, {
					  type: "pie",
					  data: {
					    labels: xValues,
					    datasets: [{
					      backgroundColor: barColors,
					      data: yValues
					    }]
					  },
					  options: {
					    responsive: true,
					    maintainAspectRatio: true,
					    plugins: {
					      legend: {display: true, position: 'bottom'},
					      title: {
					        display: true,
					        text: '성별 총 주문 수량',
					        font: {size: 18, weight: 'bold'}
					      }
					    }
					  }
					});
				},
			});
		});
		
		$('#genderPriceBtn').click(function() {
			$.ajax({
				url : $('#contextPath').val()+'/restapi/genderPrice',
				type : 'get',
				data : {
						fromYM: $('#fromYM').val(), 
						toYM: $('#toYM').val()
					},
				success: function(result) {
					if(myChart != null) {
						myChart.destroy();
					}
					showChart();
				
					let xValues = [];
					let yValues = [];
					let barColors = ["#000", "#666"];
					
					result.forEach(function(m) {
						xValues.push(m.gender);
						yValues.push(m.genderPrice);
					});
					
					let ctx = document.getElementById('myChart');

					myChart = new Chart(ctx, {
					  type: "pie",
					  data: {
					    labels: xValues,
					    datasets: [{
					      backgroundColor: barColors,
					      data: yValues
					    }]
					  },
					  options: {
					    responsive: true,
					    maintainAspectRatio: true,
					    plugins: {
					      legend: {display: true, position: 'bottom'},
					      title: {
					        display: true,
					        text: '성별 총 주문 금액',
					        font: {size: 18, weight: 'bold'}
					      }
					    }
					  }
					});
				},
			});
		});
	</script>
</body>
</html>