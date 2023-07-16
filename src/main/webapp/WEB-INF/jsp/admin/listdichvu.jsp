<!--* Project: Cinema WebApp
	* JSP: màn hình thống kê các dịch vụ mà khách hàng đã sử dụng-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<meta charset="UTF-8">

<title>Dịch Vụ</title>
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
	<%@ include file="/common/header.jsp"%>
	<div
		class="main-body row col-10 d-flex flex-row justify-content-center">
		<div class="container">

			<div class="mb-3">
				<a href="/CinemaWebApp/admin/listlichsu" class="btn btn-dark">Về
					Trang Lich Sử Khách Hàng</a>
			</div>

			<h1 align="center">Lịch Sử Dịch Vụ</h1>


			<table class="table table-bordered">
				<thead class="text-white">
					<tr>

						<th>Tên dịch vụ</th>
						<th>Ngày Sử Dụng</th>
						<th>Số Lượng</th>
						<th>Số Tiền Thanh Toán</th>
						<th>Tổng tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${suDungDichVu}" varStatus="status">
						<tr>
							<td>${c.dichVu.tenDichVu}</td>
							<td>${c.ngaySuDung}</td>
							<td>${c.soLuong}</td>
							<td>${c.dichVu.donGia}</td>
							<td>${c.dichVu.donGia*c.soLuong}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<p style="color: red">${search}</p>
			<p style="color: red">${msg1}</p>

			<div class="pagination">
				<c:if test="${currentPage > 1}">
					<a href="?page=${currentPage-1}"
						class="btn btn-light btn-direction">&laquo; Previous</a>
				</c:if>

				<c:forEach var="i" begin="1" end="${totalPages}" varStatus="status">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<a class="active active-red">${i}</a>
						</c:when>
						<c:otherwise>
							<c:if
								test="${status.index <= 2 || status.index >= totalPages - 1}">
								<a href="?page=${i}">${i}</a>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${currentPage lt totalPages}">
					<a href="?page=${currentPage+1}"
						class="btn btn-light btn-direction">Next &raquo;</a>
				</c:if>
			</div>

		</div>
	</div>

</body>
</html>