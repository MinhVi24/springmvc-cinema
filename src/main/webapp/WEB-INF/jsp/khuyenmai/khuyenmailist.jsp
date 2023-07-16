<!-- * Project: Cinema WebApp
	 * Danh sách Khuyến mãi-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách khuyến mãi</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
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

h2 {
	font-size: 50px;
}

*:not(span) {
	font-size: 20px;
}
</style>
</head>
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/header.jsp"%>
	<div
		class="main-body row col-10 d-flex flex-row justify-content-center">
		<div class="container">
			<div align="center">
				<h2 class=" text-uppercase fw-bold" style="font-size: 50px;">Danh
					sách khuyến mãi</h2>
				<div>
					<div>
						<div align="left">
							<a href="${pageContext.request.contextPath}/admin/khuyenmai/them"
								class="btn" style="margin-bottom: -120px; font-size: 30px;">Thêm
								khuyến mãi</a>
						</div>

						<p>${messageUpdate}</p>
						<form
							action="${pageContext.request.contextPath}/admin/khuyenmai/timkiem"
							class="d-flex justify-content-end">
							<input type="text" name="keySearch" value="${searchInput}"
								class="form-control" style="width: 15%; height: 60px"
								placeholder="Nhập mã, tên hoặc tỉ lệ KM..."> <input
								type="submit" value="Tìm kiếm" style="font-size: 30px"
								class="btn btn-warning">
						</form>
					</div>
				</div>
				<br>
				<table class="table table-bordered">
					<thead class="text-white">
						<tr>
							<th width="10%">MÃ KHUYẾN MÃI</th>
							<th width="10%">TÊN KHUYẾN MÃI</th>
							<th width="10%">MÔ TẢ KHUYẾN MÃI</th>
							<th width="10%">NGÀY BẮT ĐẦU</th>
							<th width="10%">NGÀY KẾT THÚC</th>
							<th width="10%">TỈ LỆ KHUYẾN MÃI</th>
							<th width="10%">HÀNH ĐỘNG</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="abc" items="${khuyenmais}" varStatus="status">
							<tr>
								<td style="padding-top: 1%">${abc.maKhuyenMai}</td>
								<td style="padding-top: 1%">${abc.tenKhuyenMai}</td>
								<td>${abc.moTaKhuyenMai}</td>
								<td style="padding-top: 1%">${abc.ngayBatDau}</td>
								<td style="padding-top: 1%">${abc.ngayKetThuc}</td>
								<td><a
									href="${pageContext.request.contextPath}/admin/khuyenmai/capnhat/${abc.maKhuyenMai}"
									onclick="return confirm('Bạn có muốn chỉnh sửa không?')"
									class="btn" style="margin-bottom: -10%">CHỈNH SỬA</a> <a
									href="${pageContext.request.contextPath}/admin/khuyenmai/delete/${abc.maKhuyenMai}"
									onclick="return confirm('Bạn có muốn xóa không?')" class="btn"
									style="margin-bottom: -10%">XÓA</a></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
				<p style="color: green" align="center">${messageThemKM}</p>
				<p style="color: green" align="center">${messageUpdateKM}</p>
				<p style="color: green" align="center">${messageXoaKM}</p>
				<p style="color: red" align="center">${messageTimKiemKM}</p>
				<p style="color: red" align="center">${KMDaDuocSuDung}</p>
				<p style="color: red" align="center">${KMDaDuocSuDung2}</p>
				<br />
				<div class="pagination">
					<c:if test="${currentPage > 1}">
						<a href="list?page=${currentPage-1}"
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
									<a href="list?page=${i}">${i}</a>
								</c:if>
								<%-- <c:if test="${status.index == 3 && totalPages > 5}">
							<span class="ellipsis">...</span>
						</c:if> --%>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:if test="${currentPage lt totalPages}">
						<a href="list?page=${currentPage+1}"
							class="btn btn-light btn-direction">Next &raquo;</a>
					</c:if>
				</div>
			</div>

		</div>
	</div>
	<script src="<c:url value="/resources/js/scripts.js"/>"></script>
</body>
</html>