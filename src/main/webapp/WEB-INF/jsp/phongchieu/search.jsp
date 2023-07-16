<!--  * Project: Cinema WebApp
      * JSP: Danh Sach Phong Chieu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tìm kiếm phòng chiếu</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>

table, td, th {
	border: 1px solid black;
}

table {
	border-collapse: collapse;
	width: 50%;
}

td {
	text-align: center;
}

* {
	font-family: "Cairo", sans-serif;
	color: lightgray;
}

body{
	width : 100%;
	background-color: #181616;
}

.main-body {
	margin-top: 100px;
	width: 80%;
	color: lightgray;
}

.btn {
	background-color: #c0392b;
	border: 2px solid #c0392b;
	border-radius: 5px;
	color: lightgray;
}

.btn:hover {
	/* background-color: white; */
	border: 2px solid white;
	border-radius: 5px;
}

.header {
	width: 100%;
	height: 100px:
}
table {
	border: 1px solid white;
}
td {
	border: 1px solid white;
}
thead {
	background-color: #181616;
	border: 2px solid #c0392b;
	
}
th {
	color: #c0392b;
	text-align: center;
	border: 2px solid #c0392b;
}
.active-red {
	background-color: #c0392b !important;
	border: 2px solid #c0392b !important ;
}
</style>

</head>
<body>
	<%@ include file="/common/header.jsp"%>



	<div class="container p-5"
		style="margin-top: 100px; margin-bottom: 100px; width: 80%">

		<div class="mb-3">
			<a href="add" class="btn btn-dark">Thêm Mới Phòng Chiếu</a>
		</div>


		<div>
			<form class="d-flex"
				action="${pageContext.request.contextPath}/admin/phongchieu/search"
				method="get">
				<input type="text" name="searchKey" class="form-control"> <input
					type="submit" value="Search" class="btn btn-success mx-2">
			</form>
		</div>

		<hr>

		<table class="table">
			<thead>
				<tr>
					<th>Mã Phòng Chiếu</th>
					<th>Tên Phòng</th>
					<th>Hạng Phòng</th>
					<th>Số Lượng Ghế</th>
					<th>Đơn Giá</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${phongchieus}" varStatus="status">
					<tr>
						<td>${c.maPhongChieu}</td>
						<td>${c.tenPhong}</td>
						<td>${c.hangPhong}</td>
						<td>${c.soLuongGhe}</td>
						<td>${c.donGia}</td>
						<td><a href="delete?id=${c.maPhongChieu}" id="delete"
							class="btn btn-danger">Delete</a> <a
							href="update/${c.maPhongChieu}" class="btn btn-warning">Update</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

		<br>

	<%-- 	<div class="pagination">
			<c:if test="${currentPage > 1}">
				<a href="list?page=${currentPage-1}" class="btn btn-light">Previous</a>
			</c:if>
			<c:forEach begin="1" end="${totalPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<a class="active"> ${i} </a>
					</c:when>
					<c:otherwise>
						<a href="list?page=${i}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${currentPage lt totalPages}">
				<a href="list?page=${currentPage+1}" class="btn btn-light">Next</a>

			</c:if>
		</div> --%>
		<div class="pagination">
			<c:if test="${currentPage > 1}">
				<a href="list?page=${currentPage-1}" class="btn btn-light">&laquo;
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
							<a href="list?page=${i}">${i}</a>
						</c:if>
						<%-- <c:if test="${status.index == 3 && totalPages > 5}">
							<span class="ellipsis">...</span>
						</c:if> --%>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${currentPage lt totalPages}">
				<a href="list?page=${currentPage+1}" class="btn btn-light">Next
					&raquo;</a>
			</c:if>
		</div>

	</div>


	<%-- <%@ include file="/common/footer.jsp"%> --%>
	<script type="text/javascript">
		$("#delete").on("click", function(e) {
			if (!confirm("Are you sure to delete that record?")) {
				e.preventDefault();
			}
		})
	</script>

</body>
</html>