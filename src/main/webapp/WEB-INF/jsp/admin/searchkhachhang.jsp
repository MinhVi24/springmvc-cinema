<!--* Project: Cinema WebApp
	* JSP: màn hình hiển thị các khách hàng tiềm kiếm được -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tìm kiếm khách hàng</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
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
			<h1 align="center">List Khách Hàng</h1>
		</div>
		<div style="margin-bottom: 20px; margin-top: 20px">
			<form:form  class="d-flex"
				action="${pageContext.request.contextPath}/admin/searchkhachhang"
				method="get" >
				<input type="text" name="tenKhachHang" class="form-control">
				<input type="submit" value="Search" class="btn btn-success mx-2">
			</form:form >
		</div>


		<table class="table table-bordered ">
			<thead class="text-white">
				<tr>

					<th>Mã Khách Hàng</th>
					<th>Tên Khách Hàng</th>
					<th>Ngày Sinh</th>
					<th>Giới Tính</th>
					<th>Email</th>
					<th>SDT</th>
					<th>Items</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${khachHang}" varStatus="status">
					<tr>

						<td>${c.maKhachHang}</td>
						<td>${c.tenKhachHang}</td>
						<td>${c.ngaySinh}</td>
						<td>${c.gioiTinh}</td>
						<td>${c.email}</td>
						<td>${c.sdt}</td>
						<td>
						<a
							href="${pageContext.request.contextPath}/admin/update/${c.maKhachHang}"
							class="btn btn-secondary">Update</a> <a
							href="${pageContext.request.contextPath}/admin/delete/${c.maKhachHang}"
							class="btn btn-dark"
							onclick="return confirm('Bạn có muốn xóa không?')"> Delete</a></td>
					</tr>
				</c:forEach>

			</tbody>

		</table>

<p style="color: red">${search}</p>

			<div class="pagination">
			<c:if test="${currentPage > 1}">
				<a href="searchkhachhang?page=${currentPage-1}&tenKhachHang=${tenKhachHang1}" class="btn btn-direction btn-light">&laquo;
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
							<a href="searchkhachhang?page=${i}&tenKhachHang=${tenKhachHang1}">${i}</a>
						</c:if>

					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${currentPage lt totalPages}">
				<a href="searchkhachhang?page=${currentPage+1}&tenKhachHang=${tenKhachHang1}" class="btn btn-light btn-direction">Next
					&raquo;</a>
			</c:if>
		</div>
	</div>



</body>
</html>