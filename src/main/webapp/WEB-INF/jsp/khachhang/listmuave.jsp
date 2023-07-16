<!--  * Project: Cinema WebApp
      * JSP: Danh Sach Lịch Sử mua hàng-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<meta charset="UTF-8">

<title>Lịch sử mua vé</title>
<style>
table, td {
	border: 1px solid white;
}

table {
	border-collapse: collapse;
	width: 50%;
}

th, td {
	text-align: center;
}
</style>
</head>
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/headerkhach.jsp"%>
	<div
		class="main-body row col-10 d-flex flex-row justify-content-center">
		<div class="container">

			<h1 align="center">Lịch Sử Mua Vé</h1>
			<table class="table table-bordered">
				<thead class=" text-white">
					<tr>
						<th>Tên Phim</th>
						<th>Ngày Chiếu</th>
						<th>Mã Ghế</th>
						<th>Giờ Bắt Đầu</th>
						<th>Giờ kết Thúc</th>
						<th>Tổng tiền vé</th>
						<th>Tỉ lệ khuyến mãi </th>
						<th>Tông tiền thanh toán</th>
						

					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${ve}" varStatus="status">
						<tr>
							<td>${c.suatChieu.phim.tenPhim}</td>
							<td>${c.ngayMuaVe}</td>
							<td>${c.maGhe}</td>
							<td>${c.suatChieu.gioBatDau}</td>
							<td>${c.suatChieu.gioKetThuc}</td>
							<td>${c.suatChieu.phongChieu.donGia}</td>
							<td>${c.khuyenMai.tiLeKhuyenMai}</td>
							<td>${((100 - c.khuyenMai.tiLeKhuyenMai)/100)*(c.suatChieu.phongChieu.donGia)}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<p style="color: red">${msg1}</p>

			<div class="pagination">
				<c:if test="${currentPage > 1}">
					<a href="listmuave?page=${currentPage-1}" class="btn btn-light btn-direction">&laquo;
						Previous</a>
				</c:if>

				<c:forEach var="i" begin="1" end="${totalPages}" varStatus="status">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<a class="active active-red">${i}</a>
						</c:when>
						<c:otherwise>
							<c:if
								test="${status.index <= 2 || status.index >= totalPages - 1}">
								<a href="listmuave?page=${i}" >${i}</a>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${currentPage lt totalPages}">
					<a href="listmuave?page=${currentPage+1}" class="btn btn-light btn-direction">Next
						&raquo;</a>
				</c:if>
			</div>

		</div>
<%@ include file="/common/footer.jsp"%>

</body>
</html>