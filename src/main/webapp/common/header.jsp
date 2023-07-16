<!--  * Project: Cinema WebApp
      * JSP: Header -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="fa.training.entities.TaiKhoan"%>
<link href="<c:url value="/resources/css/app.css"/>" rel="stylesheet" type="text/css">
<link href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css"
	rel='stylesheet'>
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<style>
	.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #212529;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  color: white;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}



.dropdown:hover .dropdown-content {
  display: block;
}
	
span {
	font-size: 24px;
}
.text-a {
	font-size: 24px;
}
</style>
<div class="row">
	<!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top bg-dark" id="mainNav" style="height: 80px">
      <div class="container">
        <a href="${pageContext.request.contextPath}/admin" class="logo text-a"> <i
					class='bx bx-movie-play bx-tada main-color'></i>T2<span
					class="main-color">Cine</span>ma
				</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarResponsive"
          aria-controls="navbarResponsive"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          Menu
          <i class="fas fa-bars ms-1"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav nav-menu text-uppercase ms-auto py-4 py-lg-0 navbar">
            <li class="nav-item">
              <div class="dropdown">
				    <a class="nav-link text-a">Khách Hàng 
				      <i class="fa fa-caret-down"></i>
				    </a>
				    <div class="dropdown-content">
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/listkhachhang">Danh sách Khách Hàng</a>
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/listlichsu">Lịch Sử Dịch Vụ</a>
				   
				    </div>
				  </div>
              
            </li>
            <li class="nav-item">
              <!-- <a class="nav-link" href="#dichvu">Dịch Vụ</a> -->
              <div class="dropdown">
				    <a class="nav-link text-a">Dịch Vụ
				      <i class="fa fa-caret-down"></i>
				    </a>
				    <div class="dropdown-content">
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/dichvu/list">QL Dịch Vụ</a>
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/khuyenmai/list">QL Khuyến Mãi</a>
				    </div>
				  </div>
            </li>
            <li class="nav-item">
            	<div class="dropdown">
				    <a class="nav-link text-a">Phim
				      <i class="fa fa-caret-down"></i>
				    </a>
				    <div class="dropdown-content">
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/phongchieu/list">Phòng Chiếu</a>
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/suatchieu/list">Suất Chiếu</a>
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/phim/listPhimDangChieu">Phim</a>
				    </div>
				  </div>
            </li>
            <li class="nav-item">
              <!-- <a class="nav-link" href="#thongke">Thống Kê</a> -->
              <div class="dropdown">
				    <a class="nav-link text-a">Thống Kê
				      <i class="fa fa-caret-down"></i>
				    </a>
				    <div class="dropdown-content">
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/thongke/khachhang">Insight Khách Hàng</a>
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/thongke/doanhthu/theophim">Doanh Thu Theo Phim</a>
				      <a class="nav-link text-a" href="${pageContext.request.contextPath}/admin/thongke/doanhthu">Doanh Thu Theo Thời Gian</a>
				    </div>
				  </div>
            </li>
            <li class="nav-item">
					<a class="btn-hover btn-header hover-white text-a" href="${pageContext.request.contextPath}/dangxuat"><span style="color:white">Đăng Xuất </span></a>
			</li>
          </ul>
        </div>
      </div>
    </nav>
</div>

