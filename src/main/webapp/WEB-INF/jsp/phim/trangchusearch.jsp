<!--* Project: Cinema WebApp
	* Trang chủ-->
<%@page import="fa.training.entities.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="fa.training.entities.TaiKhoan"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/app.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/grid.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;700;900&display=swap"
	rel="stylesheet">
<!-- OWL CAROUSEL -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
	integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
	crossorigin="anonymous" />
<!-- BOX ICONS -->
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
<meta charset="UTF-8">
<title>Trang chủ</title>
<style>
.anhthanh {
	position: relative;
	display: block;
	margin: 0 auto;
}

.btn {
	background-color: #c0392b;
	border-radius: 5px;
	color: lightgray;
	border: 0px !important;
	text-align: center;
	text-transform: uppercase;
}

.anhthanh-em {
	display: flex;
	position: absolute;
	width: 100%;
	height: 100%;
	background-color: #c0392b;
	align-items: center;
	justify-content: center;
	opacity: 0;
	transition: opacity ease-in-out 0.25s;
}

.anhthanh-em:hover {
	opacity: 0.9;
}

.img-fluid {
	max-width: 100%;
	height: auto;
}
body {
background-color: #181616;
color:white;

}
</style>
</head>
<body>

	<!-- NAV -->
		<%@ include file="/common/headerkhach.jsp"%>

	<!-- HERO SECTION -->
	<div class="hero-section">
		<!-- HERO SLIDE -->
		<div class="hero-slide">
			<div class="owl-carousel carousel-nav-center" id="hero-carousel">
				<!-- SLIDE ITEM -->
				<c:if test="${not empty listPhim}">
					<c:forEach var="c" items="${listPhim}">
						<div class="hero-slide-item">
							<img src="${c.poster}" alt="">
							<div class="overlay"></div>
							<div class="hero-slide-item-content">
								<div class="item-content-wraper">
									<div class="movie-item-title" style="color: #d75e1d;">
										Phim Hot <i class="fa-solid fa-fire fa-shake fa-2xs"
											style="color: #d75e1d;"></i>
									</div>
									<div class="item-content-title top-down">${c.tenPhim}</div>
									<div class="movie-infos top-down delay-2">
										<div class="movie-info">
											<i class="bx bxs-star"></i> <span> Phim Đang Chiếu</span>
										</div>
										<div class="movie-info">
											<i class="bx bxs-time"></i> <span>${c.thoiLuong}</span>
										</div>
									</div>
									<div class="item-content-description top-down delay-4">
										${c.noiDungPhim }</div>
									<div class="item-action top-down delay-6">
										<a
											href="${pageContext.request.contextPath}/khachhang/phimview/${c.maPhim}"
											class="btn-header btn-hover"> <i class="bx bxs-right-arrow"></i>
											<span>Xem Ngay</span>
										</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<!-- END SLIDE ITEM -->
			</div>
		</div>
		<!-- END HERO SLIDE -->
		<!-- TOP MOVIES SLIDE -->
		<div class="top-movies-slide">
			<div class="owl-carousel" id="top-movies-slide">
				<!-- MOVIE ITEM -->
				<c:if test="${not empty listPhim1}">
					<c:forEach var="c" items="${listPhim1}">
						<a
							href="${pageContext.request.contextPath}/khachhang/phimview/${c.maPhim}"
							class="movie-item"> <img src="${c.poster}" alt="">
							<div class="movie-item-content">
								<div class="movie-item-title">${c.tenPhim}</div>
								<div class="movie-infos">
									<div class="movie-info">
										<i class="bx bxs-star"></i> <span> Phim Đang Chiếu</span>
									</div>
									<div class="movie-info">
										<i class="bx bxs-time"></i> <span>${c.thoiLuong}</span>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</c:if>
				<!-- END MOVIE ITEM -->
			</div>
		</div>
		<!-- END TOP MOVIES SLIDE -->
	</div>
	<!-- END HERO SECTION -->
	<!-- LATEST SERIES SECTION -->
	<div class="section" id="timkiem">
		<div class="container">
			<div class="pricing">
				<div  class="pricing-header">Kết <span class="main-color">Quả</span> Tìm Kiếm</div>
				<div class="movies-slide carousel-nav-center owl-carousel">
				<!-- MOVIE ITEM -->
				<c:if test="${not empty listPhim2}">
					<c:forEach var="c" items="${listPhim2}">
						<a
							href="${pageContext.request.contextPath}/khachhang/phimview/${c.maPhim}"
							class="movie-item"> <img src="${c.poster}" alt="">
							<div class="movie-item-content">
								<div class="movie-item-title">${c.tenPhim}</div>
								<div class="movie-infos">
									<div class="movie-info">
										<i class="bx bxs-star"></i> <span> Phim Sắp Chiếu</span>
									</div>
									<div class="movie-info">
										<i class="bx bxs-time"></i> <span>${c.thoiLuong}</span>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</c:if>
				<!-- END MOVIE ITEM -->
			</div>
			<div align="center" style="color:red">${msg10}</div>
			</div>
		</div>
	</div>
	<!-- END LATEST SERIES SECTION -->
	<!-- SPECIAL MOVIE SECTION -->
	<div class="section">
		<div class="hero-slide-item">
			<img src="${phim.poster}" alt="">
			<div class="overlay"></div>
			<div class="hero-slide-item-content">
				<div class="item-content-wraper">
					<div class="item-content-title">${phim.tenPhim}</div>
					<div class="movie-infos">
						<div class="movie-info">
							<i class="bx bxs-star"></i> <span>Phim Đặc Biệt</span>
						</div>
						<div class="movie-info">
							<i class="bx bxs-time"></i> <span>${phim.thoiLuong}</span>
						</div>
					</div>
					<div class="item-content-description">${phim.noiDungPhim }</div>
					<div class="item-action">
						<a
							href="${pageContext.request.contextPath}/khachhang/phimview/${phim.maPhim}"
							class="btn-header btn-hover"> <i class="bx bxs-right-arrow"></i> <span>Xem
								Ngay</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END SPECIAL MOVIE SECTION -->

	<!-- PRICING SECTION -->

	<div class="section" id="khuyenmai">
		<div class="container">
			
				<div class="pricing">
					<div class="btn-header btn-hover d-flex justify-content-center">
						<span class="">Khuyến Mãi </span>
					</div>
				<div class="movies-slide carousel-nav-center owl-carousel">
							<!-- SLIDE ITEM -->
								<c:forEach var="c" items="${top3KM}">
									<div class="hero-slide-item" style="height: 423px">
										<img class="img-fluid"
											src="/CinemaWebApp/resources/image/${c.maKhuyenMai}.jpg"
											alt="..." />
										<!-- <div class=""></div>
										<div class="hero-slide-item-content">
											<div class="item-content-wraper">
												<div class="movie-item-title" style="color: #d75e1d;">
													 <i class="fa-solid fa-fire fa-shake fa-2xs"
														style="color: #d75e1d;"></i>
												</div>
												<div class="item-content-title top-down"></div>
												<div class="movie-infos top-down delay-2">
													<div class="movie-info">
														<i class="bx bxs-star"></i> <span> </span>
													</div>
													<div class="movie-info">
														<i class="bx bxs-time"></i> <span></span>
													</div>
												</div>
												<div class="item-content-description top-down delay-4">
													</div>
												<div class="item-action top-down delay-6">
							
												</div>
											</div>
										</div> -->
									</div>
								</c:forEach>
						
							<!-- END SLIDE ITEM -->
						</div> 
				</div>
		</div>
	</div>
	<!-- END PRICING SECTION -->

	<!-- FOOTER SECTION -->
	<footer class="section">
		<div class="container">
			<div class="row">
				<div class="col-4 col-md-6 col-sm-12">
					<div class="content">
						<a href="#" class="logo"> <i
							class='bx bx-movie-play bx-tada main-color'></i>T2<span
							class="main-color">Cine</span>ma
						</a>
						<p>Welcome to the world</p>
						<div class="social-list">
							<a href="#" class="social-item"> <i class="bx bxl-facebook"></i>
							</a> <a href="#" class="social-item"> <i class="bx bxl-twitter"></i>
							</a> <a href="#" class="social-item"> <i class="bx bxl-instagram"></i>
							</a>
						</div>
					</div>
				</div>
				<div class="col-8 col-md-6 col-sm-12">
					<div class="row">
						<div class="col-3 col-md-6 col-sm-6">
							<div class="content">
								<p>
									<b>Flix</b>
								</p>
								<ul class="footer-menu">
									<li><a href="#">About us</a></li>
									<li><a href="#">My profile</a></li>
									<li><a href="#">Pricing plans</a></li>
									<li><a href="#">Contacts</a></li>
								</ul>
							</div>
						</div>
						<div class="col-3 col-md-6 col-sm-6">
							<div class="content">
								<p>
									<b>Browse</b>
								</p>
								<ul class="footer-menu">
									<li><a href="#">About us</a></li>
									<li><a href="#">My profile</a></li>
									<li><a href="#">Pricing plans</a></li>
									<li><a href="#">Contacts</a></li>
								</ul>
							</div>
						</div>
						<div class="col-3 col-md-6 col-sm-6">
							<div class="content">
								<p>
									<b>Help</b>
								</p>
								<ul class="footer-menu">
									<li><a href="#">About us</a></li>
									<li><a href="#">My profile</a></li>
									<li><a href="#">Pricing plans</a></li>
									<li><a href="#">Contacts</a></li>
								</ul>
							</div>
						</div>
						<div class="col-3 col-md-6 col-sm-6">
							<div class="content">
								<ul class="footer-menu">
									<li><a href="#"> <img src="./images/google-play.png"
											alt="">
									</a></li>
									<li><a href="#"> <img src="./images/app-store.png"
											alt="">
									</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- END FOOTER SECTION -->

	<!-- COPYRIGHT SECTION -->
	<div class="copyright">
		Copyright 2023 <a
			href="https://www.facebook.com/profile.php?id=100011370247647"
			target="_blank"> Team 2</a>
	</div>
	<!-- END COPYRIGHT SECTION -->
	<!-- Bootstrap core JS-->
	<%-- <script src="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"/>"> --%>
	<!-- Core theme JS-->
	<script src="<c:url value="/resources/js/app.js"/>"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
		integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
		crossorigin="anonymous"></script>
</body>
<script type="text/javascript">
	function genderChanged(obj) {
		var ketqua = document.getElementById('mydate');
		var search = document.getElementById('search');
		var value = obj.value;
		if (value === '4') {
			ketqua.style.display = 'block';
			search.style.display = 'none';
		} else if (value === '1' || value === '2' || value === '3') {
			ketqua.style.display = 'none';
			search.style.display = 'block';
		}
	}
</script>
</html>