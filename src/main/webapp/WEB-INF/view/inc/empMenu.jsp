<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/nav.css" rel="stylesheet">
<nav class="top-nav">
    <div class="nav-container">
        <ul class="nav-menu">
            <li><a href="${pageContext.request.contextPath}/emp/goodsList">상품목록</a></li>
            <li><a href="${pageContext.request.contextPath}/emp/customerList">고객목록</a></li>
            <li><a href="${pageContext.request.contextPath}/emp/orderList">주문목록</a></li>
            <li><a href="${pageContext.request.contextPath}/emp/noticeList">공지사항</a></li>
            <li><a href="${pageContext.request.contextPath}/emp/empList">직원목록</a></li>
            <li><a href="${pageContext.request.contextPath}/emp/myInfo">내정보</a></li>
        </ul>
        <div class="nav-right">
            <span class="user-info">${loginEmp.empName}님(관리자)</span>
            <button class="logout-btn" onclick="location.href='${pageContext.request.contextPath}/emp/empLogout'">로그아웃</button>
        </div>
    </div>
</nav>