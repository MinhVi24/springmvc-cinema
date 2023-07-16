<!--* Project: Cinema WebApp
	* Trang chi tiết một bộ phim-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><!DOCTYPE html>
	<%@ page import="fa.training.entities.KhachHang"%>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style type="text/css"></style>
<meta charset="UTF-8">
<title>Chi Tiết Phim</title>
</head>
<body id="page-top">
	<!-- Navigation-->
	<%@ include file="/common/headerkhach.jsp"%>
	<header class="bg-dark" style="padding-top: 100px">
		<div class="container px-5">
			<div class="row gx-5 align-items-center justify-content-center">
				<div class="col-lg-8 col-xl-7 col-xxl-6">
					<div class="my-5 text-center text-xl-start">
						<h1 class="display-5 fw-bolder text-white mb-2">${phim.tenPhim}</h1>
						<p class="lead fw-normal text-white-50 mb-4">
							Thể loại: <span class="text-white">${phim.moTaPhim}</span>
						</p>
						<p class="lead fw-normal text-white-50 mb-4">
							Đạo diễn: <span class="text-white">${phim.daoDien}</span>
						</p>
						<p class="lead fw-normal text-white-50 mb-4">
							Ngày khởi chiếu: <span class="text-white">${phim.ngayKhoiChieu}</span>
						</p>
						<p class="lead fw-normal text-white-50 mb-4">
							Thời Lượng: <span class="text-white">${phim.thoiLuong}</span>
						</p>
						<div
							class="d-grid gap-3 d-sm-flex justify-content-sm-center justify-content-xl-start">
							<a class="btn btn-primary btn-lg px-4 me-sm-3" href="#lichchieu">SUẤT
								CHIẾU</a> 
						</div>
					</div>
				</div>
				<div class="col-xl-5 col-xxl-6 d-none d-xl-block text-center">
					 <img
						class="img-fluid rounded-3 my-5"
						src="${phim.poster}"
						alt="..." />
				
				</div>
		<div style="margin-bottom: 100px;" class="section">
		<div class="lead fw-normal text-white-50 mb-4"> <h2>Nội Dung Phim:</h2></div>
		<div class="hero-slide-item">
			<div class="hero-slide-item-content">
				<div class="item-content-wraper">
					<div class="item-content-description" style="color: white; word-wrap:break-word">${phim.noiDungPhim}</div>
				</div>
			</div>
		</div>
	</div>
			</div>
		</div>
	</header>
	<!-- Lịch Chiếu-->
	<section class="page-section" id="lichchieu">
		<div class="container">
			<div class="text-center mt-4">
				<h2 class="section-heading text-uppercase">
					lị<span
					class="main-color">ch ch</span>iếu</h2>
			</div>
			<div class="row text-center">
			<form:form id="formNgayChieu" action="${pageContext.request.contextPath}/khachhang/phimview/${phim.maPhim }#lichchieu" method="POST" modelAttribute="NgayChieu">
				<div class="input-group mb-3">
					<form:input id="chonNgayChieu" path="ngayChieu" type="date" class="form-control" placeholder="Ngày chiếu"/>
					<div style="display:none"><form:input
							 path="maPhim"/>${phim.maPhim }</div>
				</div>
			</form:form>
			<p style="color:#c0392b">${suatChieuMessage }</p>
			</div>
		</div>
	</section>
	<!-- Suất Chiếu-->
	<section class="page-section" id="lichchieu">
		<div class="container">
			<div class>
				<div class="row d-flex">

					<c:forEach var="b" items="${suatChieus}">

						<div>
							<%
									if (account != null) {
							%>
							<a class="btn btn-outline-warning btn-lg"
								href="${pageContext.request.contextPath}/ve/${b.maSuatChieu}">${b.gioBatDau}</a>
							<%
								} else {
							%>
							<a class="btn btn-outline-warning btn-lg"
								href="${pageContext.request.contextPath}/dangnhap/${b.maSuatChieu}">${b.gioBatDau}</a>
							<%
								}
							%>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>
	<!-- Suất Chiếu-->
	<section class="py-5">
		<div class="container px-5 my-5">
			<div class="row gx-5">
			<c:if test="${not empty listPhim}">
					<c:forEach var="c" items="${listPhim}">
				<div class="col-lg-4 mb-5">
					<div class="card h-100 shadow border-0">
						<img class="card-img-top"
							src="${c.poster}" alt="..." />
						<div class="card-body p-4" style="background-color: black;">
							<div class="badge bg-danger bg-gradient rounded-pill mb-2">Phim Đang Chiếu</div>
							<a class="text-decoration-none link-dark stretched-link"
								href="${pageContext.request.contextPath}/khachhang/phimview/${c.maPhim}">
								<h5 class="card-title mb-3">${c.tenPhim}</h5></a>
							
						</div>
					</div>
				</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</section>
	<!-- Footer-->
	<%@ include file="/common/footer.jsp"%>
	<script>
	let chonNgayChieu = document.getElementById("chonNgayChieu");
	chonNgayChieu.addEventListener("change", function() {
		document.getElementById("formNgayChieu").submit();
	})
	</script>
	<script src="<c:url value="/resources/js/scripts.js"/>"></script>
</body>
</html>