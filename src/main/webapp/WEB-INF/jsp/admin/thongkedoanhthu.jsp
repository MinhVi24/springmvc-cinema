
<!-- * Project: Cinema WebApp
	 * JSP: màn hình chính thống kê-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin thống kê</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<style>
#sidebar {
	margin: 145px 0 100px 40px;
}

.dropdown {
	position: relative;
	display: block;
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
.body {
	margin-top: 100px;
}
</style>
<body>
	<%@ include file="/common/header.jsp"%>
	
	<div class="row col-12 d-flex flex-row">
		<div class="body col-8 d-flex flex-column justify-content-center">
			<div class = "col-6">
			<label for="input-1">Chọn Năm</label>
			<input id="input-1" type="number" min="2020" max="2100" step="1" value="2023" />
			<button id="chon" class="btn btn-warning"> Chọn </button>
			</div>
			<h2> Thống kê doanh thu năm ${year }</h2>
			<p> Tổng doanh thu : ${tongDoanhThu }</p>
			<p> Doanh thu các tháng trong năm </p>
			<table id="doanhThuPhim" class="table table-bordered">
				<thead>
					<th>Tháng</th>
					<th>Số vé đã bán</th>
					<th>Doanh thu bán vé</th>
					<th>Doanh thu dịch vụ</th>
					<th>Tổng doanh thu</th>
				</thead>
				<tbody id = "tablePaging">
					<c:forEach var="c" items="${listDoanhThuPhim }">
					<tr>
						<td><a href = "${pageContext.request.contextPath}/admin/thongke/doanhthu/theothang?thang=${c[0] }&nam=${year}"> Tháng ${c[0]}-${year } </a></td>
						<td>${c[1]}</td>
						<td>${c[2]}</td>
						<td>${c[3]}</td>
						<td>${c[4]}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<p>${noInfo }</p>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
	
	<script src="<c:url value="/resources/js/scripts.js"/>"></script>
</body>
	<script>
	document.getElementById('chon').addEventListener('click', () => {
	let date = new Date(document.getElementById('input-1').value);
	let year = date.getFullYear();
	console.log(year);
	let url = 'http://localhost:8080/CinemaWebApp/admin/thongke/doanhthu/theonam?nam=' + year;
	window.location.href = url; 
	});

	/* var table = document.getElementById("tablePaging");
	var rowsPerPage = 5; // Số dòng hiển thị trên mỗi trang
	let currentPage = 1; // Trang hiện tại

	function displayTable() {
		  var startIndex = (currentPage - 1) * rowsPerPage;
		  var endIndex = startIndex + rowsPerPage;
		  var rows = table.rows;
		  
		  // Ẩn tất cả các dòng của bảng
		  for (var i = 0; i < rows.length; i++) {
		    if (i >= startIndex && i < endIndex) {
		      rows[i].style.display = "table-row";
		    } else {
		      rows[i].style.display = "none";
		    }
		  }
		}

		function createPagination() {
		  var pageCount = Math.ceil((table.rows.length - 1) / rowsPerPage); // Tính tổng số trang
		  
		  // Tạo nút điều hướng trang trước
		  var prevButton = document.createElement("button");
		  prevButton.classList.add("btn");
		  prevButton.classList.add("btn-warning");
		  prevButton.innerHTML = "Trang trước";
		  prevButton.addEventListener("click", function() {
		    if (currentPage > 1) {
		      currentPage--;
		      displayTable();
		      createPagination();
		    }
		  });
		  
		  // Tạo nút điều hướng trang tiếp theo
		  var nextButton = document.createElement("button");
		  nextButton.classList.add("btn");
		  nextButton.classList.add("btn-warning");
		  nextButton.innerHTML = "Trang tiếp";
		  nextButton.addEventListener("click", function() {
		    if (currentPage < pageCount) {
		    	currentPage++;
		      displayTable();
		      createPagination();
		    }
		  });
		  
		  // Hiển thị trang hiện tại và tổng số trang
		  var pageInfo = document.createElement("span");
		  pageInfo.innerHTML = " Trang " + currentPage + " / " + pageCount +" ";
		  
		  // Thêm các phần tử vào phân trang
		  var pagination = document.getElementById("pagination");
		  pagination.innerHTML = "";
		  pagination.appendChild(prevButton);
		  pagination.appendChild(pageInfo);
		  pagination.appendChild(nextButton);
		}

		displayTable();
		createPagination(); */
	</script>
</html>