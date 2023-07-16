<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="fa.training.entities.TaiKhoan"%>
<link href="<c:url value="/resources/css/app.css"/>" rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
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
</style>
<div>

	<div class="nav-wrapper">
		<div class="container">
			<div class="nav">
				<a href="${pageContext.request.contextPath}/trangchu" class="logo"> <i
					class='bx bx-movie-play bx-tada main-color'></i>T2<span
					class="main-color">Cine</span>ma
				</a>
				<form class="d-flex"
					action="${pageContext.request.contextPath}/khachhang/search#timkiem" method="get">
					<div class="d-flex" style="width: 500px">
					<div style=" margin-right: 10px" >
					<input style="width: 250px;" id="search" type="search" class="form-control"
								aria-label="Search" aria-describedby="search-addon"
								name="searchKey" value="${searchKey}${date}"/></div>
								<div id="mydate" style=" margin-right: 10px; display: none;">
					<input  style="width: 240px;" class="form-control" name="mydate" type="date" /></div>
					<select style=" margin-right: 10px; width: 120px" name="timkiem" onchange="genderChanged(this)" class="form-select"
							aria-label="Default select example">
								<option value="1">Tên Phim</option>
								<option value="2">Đạo Diễn</option>
								<option value="3">Thể Loại</option>
								<option value="4">Ngày</option>
							
						</select>
							<input style=" margin-right: 10px" type="submit" value="Tìm Kiếm"
								class="btn" />
								</div>
				</form>
				<ul class="nav-menu" id="nav-menu">
				<%
							TaiKhoan account = (TaiKhoan) session.getAttribute("account");
							if (account != null) {
						%>
					<li class="nav-item">
						<div class="dropdown">
							<a class="nav-link">Thông Tin <i class='bx bx-caret-down'></i>
							</a>
							<div class="dropdown-content">
								<a class="nav-link"
									href="${pageContext.request.contextPath}/khachhang/control/updateid">Cập
									nhật thông tin </a> <a class="nav-link"
									href="${pageContext.request.contextPath}/taikhoan/doimatkhau">Đổi mật khẩu
									</a> <a class="nav-link"
									href="${pageContext.request.contextPath}/khachhang/control/listmuave">lịch
									sử mua vé</a> <a class="nav-link"
									href="${pageContext.request.contextPath}/khachhang/control/listdichvu">lịch
									sử dịch vụ</a>
							</div>
						</div>
					</li>
					<%
							}
						%>
					<li><a href="${pageContext.request.contextPath}/trangchu#phim">Phim</a></li>
					<li><a href="${pageContext.request.contextPath}/trangchu#khuyenmai">Khuyến Mãi</a></li>
					<li>
						<%
							if (account != null) {
						%> <a href="${pageContext.request.contextPath}/dangxuat"
						class="btn-header btn-hover hover-white"> <span style="color:white"> Đăng Xuất </span>
					</a> <%
							} else {
						%> <a href="${pageContext.request.contextPath}/dangnhap"
						class="btn-header btn-hover hover-white"> <span style="color:white">Đăng Nhập</span>
					</a> <%
							}
						%>
					</li>
				</ul>
				<!-- MOBILE MENU TOGGLE -->
				<div class="hamburger-menu" id="hamburger-menu">
					<div class="hamburger"></div>
				</div>
			</div>
		</div>
	</div>
</div>









