<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Đăng nhập</title>
<!--* Project:Cinema WebApp
    * Đăng Nhập Form-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

<style>
html, body {
	height: 110%;
}

body {
	display: flex;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 360px;
	padding: 15px;
}

.form-signin .form-floating:focus-within {
	z-index: 2;
}

.form-floating {
	margin-top: 30px;
	margin-bottom: 20px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

img {
	border-radius: 8px;
	display: block;
	margin-left: auto;
	margin-right: auto;
	width: 100%;
}

h1 {
	text-align: center;
}

button {
	margin-top: 10px;
	margin-bottom: 20px;
}

.right {
	text-align: right;
	float: right;
}

.center {
	text-align: center;
	float: center;
}
</style>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<div class="container mt-3">
		<main class="form-signin w-100 m-auto">
			<form id="loginForm" action="dangnhap" method="POST"
				modelAttribute="taikhoan">
				<img class="mb-4"
					src="https://i.pinimg.com/564x/c4/c8/08/c4c8085cd050efafd96b021591e6f75c.jpg"
					alt="" width="250" height="220">
				<h1 class="h3 mb-3 fw-normal">Đăng Nhập</h1>
				<div class="form-floating">
					<input type="text" class="form-control" id="account" name="account"
						path="account" placeholder="Password"> <label
						path="account">UserName</label>
					<div id="usernameErrow" style="color: red;"></div>
					<p style="color: red">${messageAccount}</p>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="password"
						name="password" path="password" placeholder="Password"> <label
						path="password">Password</label>
					<div id="passwordErrow" style="color: red;"></div>
				</div>
				<div class="checkbox mb-3">
					<input type="checkbox" value="remember-me" name="remember-me" id="rememberme">
					<label> Remember me</label>
					<div class="right">
						<a href="">Quên Mật Khẩu !</a>
					</div>
				</div>
				<button class="w-100 btn btn-lg btn-warning" id="myBtn"
					type="submit" style="color: white">Đăng Nhập</button>
				<div class="center">
					<i class="fa fa-home" style="font-size: 24px;"></i><a href="">
						Về Trang Chủ !</a>
				</div>
			</form>
		</main>
	</div>
	<script>
		document.getElementById("loginForm").onsubmit = function(e) {
			let account = document.getElementById("account").value;
			let password = document.getElementById("password").value;
			if (account == "") {
				account = "";
				document.getElementById("account").style.borderColor = "red"
				document.getElementById("usernameErrow").innerHTML = "Vui Lòng Nhập UserName ";
				e.preventDefault();
			} else {
				document.getElementById("account").style.borderColor = ""
				document.getElementById("usernameErrow").innerHTML = "";
			}
			if (password == "") {
				password = "";
				document.getElementById("password").style.borderColor = "red"
				document.getElementById("passwordErrow").innerHTML = "Vui Lòng Nhập Password ";
				e.preventDefault();
			} else {
				document.getElementById("password").style.borderColor = ""
				document.getElementById("passwordErrow").innerHTML = "";
				return true;
			}
		}
	</script>
</body>
</html>