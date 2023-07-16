<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="d-flex flex-column col-4" id="sidebar">
	<div class="dropdown">
		<a href="${pageContext.request.contextPath}/admin/thongke/khachhang"
			class="list-group-item list-group-item-action py-2"
			aria-current="true"> <i
			class="fa-solid fa-list fa-fw me-3 taoforcus"></i><span>Thống
				kê khách hàng</span>
		</a>
		<div class="dropdown-content">
			<a class="nav-link"
				href="${pageContext.request.contextPath}/admin/thongke/khachhang/dotuoi">Thống
				kê độ tuổi</a> <a class="nav-link"
				href="${pageContext.request.contextPath}/admin/thongke/khachhang/gioitinh">Thống
				kê giới tính</a>
		</div>
	</div>
	<div class="dropdown">
		<a href="${pageContext.request.contextPath}/admin/thongke/doanhthu"
			class="list-group-item list-group-item-action py-2 dropdown taoforcus">
			<i class="fa-solid fa-list fa-fw me-3"></i><span>Thống kê
				doanh thu</span>
		</a>
		<div class="dropdown-content">
			<div class="dropdown-content">
				<a class="nav-link"
					href="${pageContext.request.contextPath}/admin/thongke/doanhthu/theophim">Doanh
					thu theo phim</a> <a class="nav-link"
					href="${pageContext.request.contextPath}/admin/thongke/doanhthu/theothang">Doanh
					thu theo tháng</a> <a class="nav-link"
					href="${pageContext.request.contextPath}/admin/thongke/doanhthu/theonam">Doanh
					thu theo năm</a>
			</div>
		</div>
	</div>
	<div class="dropdown">
		<a href="${pageContext.request.contextPath}/admin/thongke/ve"
			class="list-group-item list-group-item-action py-2 taoforcus"><i
			class="fa-solid fa-list fa-fw me-3"></i><span>Thống kê thông
				tin vé</span> </a>
		<div class="dropdown-content">
			<a class="nav-link"
				href="${pageContext.request.contextPath}/admin/thongke/doanhthu/theophim">Thống
				kê theo phim</a> <a class="nav-link"
				href="${pageContext.request.contextPath}/admin/thongke/doanhthu/theothang">Thống
				kê theo phòng</a> <a class="nav-link"
				href="${pageContext.request.contextPath}/admin/thongke/doanhthu/theonam">Thống
				kê theo theo năm</a>
		</div>
	</div>
</div>
