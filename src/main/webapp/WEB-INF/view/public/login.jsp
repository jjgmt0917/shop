<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>shop</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Apple SD Gothic Neo', 'Malgun Gothic', sans-serif;
            background-color: #f5f5f5;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-card {
            background-color: white;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            padding: 40px;
            width: 100%;
            max-width: 420px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .login-title {
            text-align: center;
            font-size: 24px;
            font-weight: 600;
            margin-bottom: 30px;
            color: #333;
        }

        .user-type-section {
            background-color: #f8f9fa;
            border-radius: 6px;
            padding: 15px;
            margin-bottom: 25px;
        }

        .form-check {
            padding-left: 1.5em;
        }

        .form-check-input:checked {
            background-color: #000;
            border-color: #000;
        }

        .form-check-label {
            font-size: 14px;
            color: #333;
        }

        .form-control {
            padding: 12px 15px;
            border-color: #ddd;
        }

        .form-control:focus {
            border-color: #999;
            box-shadow: none;
        }

        .btn-login {
            width: 100%;
            padding: 14px;
            background-color: #000;
            border: none;
            color: white;
            font-weight: 600;
            font-size: 15px;
            transition: background-color 0.3s;
            margin-bottom: 10px;
        }

        .btn-login:hover {
            background-color: #333;
            color: white;
        }

        .btn-login:active {
            background-color: #111 !important;
        }

        .btn-signup {
            width: 100%;
            padding: 14px;
            background-color: white;
            border: 1px solid #ddd;
            color: #333;
            font-weight: 600;
            font-size: 15px;
            transition: all 0.3s;
        }

        .btn-signup:hover {
            background-color: #f8f9fa;
            border-color: #999;
            color: #000;
        }

        .input-label {
            font-weight: 500;
            color: #333;
            margin-bottom: 8px;
            font-size: 14px;
        }
    </style>
</head>
<body>
	<div class="login-card">
        <h1 class="login-title">로그인</h1>
        <form method="post" id="loginForm" action="${pageContext.request.contextPath}/public/login">
            <div class="user-type-section">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="customerOrEmpSel" id="customerSel" value="customer" checked>
                    <label class="form-check-label" for="customerSel">Customer</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="customerOrEmpSel" id="empSel" value="emp">
                    <label class="form-check-label" for="empSel">Employee</label>
                </div>
            </div>

            <div class="mb-3">
                <input type="text" class="form-control" name="id" id="id" placeholder="아이디를 입력하세요" value="user01" required>
            </div>

            <div class="mb-4">
                <input type="password" class="form-control" name="pw" id="pw" placeholder="비밀번호를 입력하세요" value="1234" required>
            </div>

            <button type="button" id="loginBtn" class="btn btn-login">로그인</button>
        </form>
        
        <button type="button" class="btn btn-signup" onclick="location.href='${pageContext.request.contextPath}/public/addMember'">회원가입</button>
    </div>
</body>
<script>
	$('#loginBtn').click(() => {
		if($('#id').val() == null || $('#id').val() == "") {
			alert('아이디를 입력해주세요.');
			return;
		}
		
		let idPattern = /^[a-z0-9]+$/;
		if(!idPattern.test($('#id').val())){
			alert('아이디는 영어소문자와 숫자로만 입력해주세요');
			return;
		}
		
		if($('#pw').val() == null || $('#pw').val() == "") {
			alert('비밀번호를 입력해주세요.');
			return;
		}
		$('#loginForm').submit();
	});
</script>
</html>