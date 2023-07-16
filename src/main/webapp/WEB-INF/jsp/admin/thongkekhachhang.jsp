
<!-- 
	 * Project: Cinema WebApp
	 * JSP: màn hình thống kê insight khách hàng
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin thống kê khách hàng</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<style>
<
style>#sidebar {
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
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/header.jsp"%>
	<div class="main-body row col-12 d-flex flex-row justify-content-center">
		<h2 class="text-center mb-4">THỐNG KÊ KHÁCH HÀNG</h2>
		
		<div class="col-6 px-2 d-flex flex-row justify-content-center">
			<div class="col-12 px-2 d-flex flex-column justify-content-center">
			<h3 class="mb-3">Tổng số khách hàng: ${soLieu[0]}</h3>
				<canvas id="gioiTinh" style="width: 100%; max-width: 300px"></canvas>

			</div>
			<br>
			
			<div class="col-6 d-flex flex-column justify-content-center">
				<h3 class="col-10 mb-3">Thống kê độ tuổi Khách Hàng</h3>
				<canvas class="col-10" id="doTuoi" style="width: 100%; max-width: 600px"></canvas>
			</div>
		</div>
	</div>
	 <script>
        var xDoTuoi = ["0 - 18 tuổi", "19 - 24 tuổi", "25 - 30 tuổi", "30 - 45 tuổi", "trên 45 tuổi"];
        var yDoTuoi = [];
        var tuoi18 = ${soLieu[3]};
        var tuoi19 = ${soLieu[4]};
        var tuoi25 = ${soLieu[5]};
        var tuoi30 = ${soLieu[6]};
        var tuoi45 = ${soLieu[7]};
        yDoTuoi.push(tuoi18,tuoi19,tuoi25,tuoi30,tuoi45);
        var barColors = "skyblue";

        new Chart("doTuoi", {
            type: "bar",
            data: {
                labels: xDoTuoi,
                datasets: [{
                    backgroundColor: barColors,
                    data: yDoTuoi
                }]
            },
            options: {
                legend: { display: false },
                title: {
                    display: true,
                    text: "Thống kê theo độ tuổi"
                }
            }
        });
		
        var xGioiTinh = ["Nam", "Nữ"];
        var yGioiTinh = [];
       	var nu = ${soLieu[1]};
       	var nam = ${soLieu[2]};
       	yGioiTinh.push(nam,nu);
        var barColors = [
            "#b91d47",
            "#00aba9",
            "#2b5797",
            "#e8c3b9",
            "#1e7145"
        ];
        
        new Chart("gioiTinh", {
            type: "pie",
            data: {
                labels: xGioiTinh,
                datasets: [{
                    backgroundColor: barColors,
                    data: yGioiTinh
                }]
            },
            options: {
                title: {
                    display: true,
                    text: "Thổng kê theo giới tính"
                }
            }
        });
    </script>
</body>
</html>