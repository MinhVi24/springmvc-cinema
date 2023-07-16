<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý hệ thống</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<%-- <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet"> --%>
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/display.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<style>

.container {
	margin-top: 50px;
	padding-bottom: 20px;
	padding-top: 20px;
	width: 450px;
	background-color: white;
	color: black;
}

.canGiua {
	display: flex;
	justify-content: center;
}

.label {
	font-size: 20px;
	font-weight: 500;
}

.canTrai {
	text-align: right;
}
* {
color: black;
}
</style>
</head>

<body>
		<div align="center" class="mt-3">
			<a href="${pageContext.request.contextPath}/trangchu">
				<button class="btn btn-primary">Trang chủ</button>
			</a>
		</div>
	<div class="container">
		<div class="headerBill">
			<h1 class="canGiua">FA Cinema</h1>
			<p class="canGiua">FPT complex , Nam Kì Khởi Nghĩa, Ngũ Hành Sơn,
				Đà Nẵng</p>
			<p class="canGiua">---------------------------------------</p>
		</div>
		<div class="bodybill">
			<h2 class="canGiua">Phiếu thanh toán</h2>
			<p class="label">Tên phim: ${tenPhim }</p>
			<p class="label">
				Số ghế:
				<c:forEach var="c" items="${maGhe}">
					<span>${c} </span>
				</c:forEach>
			</p>
			<h4>Dịch vụ sử dụng</h4>
			<table class="table-bordered">
				<thead>
					<tr>
						<th style="width: 40px;">Stt</th>
						<th style="width: 150px;">Tên dịch vụ</th>
						<th style="width: 70px;">Đơn giá</th>
						<th style="width: 71px;">Số lượng</th>
						<th style="width: 83px;">Thành tiền</th>
					</tr>
				</thead>
				<tbody>
					<%
						int soThuTu = 1;
					%>
					<c:forEach var="c" items="${sddv}">

						<tr>
							<td><%=soThuTu%></td>
							<td>${ c.dichVu.tenDichVu}</td>
							<td>${ c.dichVu.donGia}</td>
							<td>${ c.soLuong}</td>
							<td>${ c.dichVu.donGia * c.soLuong }</td>
						</tr>
						<%
							soThuTu++;
						%>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="canTrai fw-bold">Tổng cộng: ${soTien*1 }</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="4" class="canTrai fw-bold">Giảm giá: ${soTien * khuyenMai.tiLeKhuyenMai /100}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="4" class="canTrai fw-bold">Số tiền thanh toán:
							${soTien * (100-khuyenMai.tiLeKhuyenMai) /100}</td>
						<td></td>
					</tr>
				</tfoot>
			</table>

			<div class="ngayTinhBill">
				<p class="canGiua">Ngày thanh toán : ${ngayThanhToan }</p>
			</div>

		</div>
		<div class="footerBill">
			<p class="canGiua">Chân thành cảm ơn quý khách!</p>
			<p class="canGiua">Xin hẹn gặp lại!</p>
		</div>
	</div>
</body>
</html>