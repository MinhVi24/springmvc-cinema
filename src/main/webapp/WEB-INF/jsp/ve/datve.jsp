
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="fa.training.entities.KhachHang"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style type="text/css">
*{
color: black;
}
.daDat {
	background-color: #c0392b !important;
	color: white;
}

h1{
	color: white;
}

td {
	width: 40px;
	height: 40px;
	padding: 0px;
}

table td:hover {
	background-color: #00FF33;
	cursor: pointer;
}

.ticket {
	height: 40px;
	width: 40px;
	justify-content: center;
	text-align: center;
}

.selected {
	background-color: green !important;
	color: white;
}

.hidden {
	display: none;
}

.side {
	
}

.manhinh {
	border-bottom: 2px solid black;
}

label {
	font-weight: bold;
}

.paddstyle {
	padding-left: 90px;
}

.chuthichchuadat {
	width: 20px;
	height: 20px;
	margin-top: 5px;
	background-color: #EEEEEE;
}

.chuthichdadat {
	width: 20px;
	height: 20px;
	margin-top: 5px;
	background-color: #c0392b;
}

.chuthichdangchon {
	width: 20px;
	height: 20px;
	margin-top: 5px;
	background-color: green;
}

#chuthich>p {
	pardding-bottom:
}

#screen {
	margin-top: 100px;
}

.mx-2 {
color: white;
}
</style>
<meta charset="UTF-8">
<title>Home</title>
</head>
<%@ include file="/common/headerkhach.jsp"%>
<body class="d-flex flex-column">
	<div class="hidden" id="msc">${sc.maSuatChieu }</div>
	<div class="hidden" id="tiLeKM">${khuyenMai.tiLeKhuyenMai }</div>
	<div class="hidden" id="maKM">${khuyenMai.maKhuyenMai }</div>
	<div id="maKhachHang" style="display: none"> ${maKhachHang }</div> 
	<div id="screen" class="row d-flex flex-row justify-content-center ">
		<div class="side col-3 d-flex flex-column"
			style="background-color: #EEEEEE;">
			<h3 class="col-12 d-flex justify-content-center">Danh sách dịch
				vụ</h3>
			<c:forEach var="c" items="${dichVu }">
				<div class="col-12 d-flex flex-column paddstyle">
					<div class="maDichVu" style="display: none">${c.maDichVu }</div>
					<label class="col-12">${c.tenDichVu }</label>
					<div class="col-12 d-flex flex-row">
						<input type="number" min="0" class="col-3 itemDichVu form-control"
							style="width: 80px"> <span class="col-1 px-2 mt-2">
							x </span> <span class="col-2 donGia mt-2 mb-2">${c.donGia }</span> <span
							class="col-3 tongTienDichVu mt-2"></span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="col-7 p-5 d-flex flex-column justify-content-center"
			style="margin-top: -55px; width: 500px">
			<h1 class="col-12 text-center manhinh">SCREEN</h1>

			<table class="table table-bordered">
				<thead>
					<tr style="display: none">
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${listVe}" varStatus="trangThai">
						<tr>
							<c:forEach var="d" items="${c}" varStatus="trangThai">
								<c:if test="${d.trangThai == 1}">
									<td class="ticket chuaDat" style="background-color: #EEEEEE;">${d.maGhe}</td>
								</c:if>
								<c:if test="${d.trangThai == 2}">
									<td class="daDat ticket" disabled="true">${d.maGhe}</td>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div id="chuthich" class="mt-4">
				<div class=" d-flex">
					<div class="chuthichchuadat"></div>
					<div>
						<p class="mx-2">Ghế chưa đặt</p>
					</div>
				</div>
				<div class=" d-flex">
					<div class="chuthichdadat"></div>
					<p class="mx-2">Ghế đã đặt</p>
				</div>
				<div class="d-flex">
					<div class="chuthichdangchon"></div>
					<p class="mx-2">Ghế đang chọn</p>
				</div>
			</div>
		</div>
		<div class="side col-2 d-flex flex-column mx-5">
			<div id="paymentMenu" style="background-color: #EEEEEE;">
				<ul id="thongTinDV " style="list-style: none"
					class="list-group list-group-flush mx-3 mt-4">
					<li><span class="fw-bold"> Tên Phim: </span> ${phim.tenPhim }</li>
					<li><span class="fw-bold">Phòng chiếu: </span> ${pc.tenPhong }</li>
					<li><span class="fw-bold">Giờ chiếu: </span> ${sc.gioBatDau }</li>
					<li><span class="fw-bold">Giá vé: </span> ${pc.donGia}</li>
					<li><span class="fw-bold">Ghế: </span> <span id="chonGhe"></span></li>
					<div id="totalDichVu">
						<span class="fw-bold">Dịch vụ: </span> 0
					</div>
					<li id="total"><span class="fw-bold">Tổng tiền: </span>0</li>
					<li id="discount"><span class="fw-bold">Giảm giá: </span>0</li>
					<li id="totalFinal"><span class="fw-bold">Thành tiền: </span>0</li>
				</ul>
				<button id="datVeButton" class="btn btn-warning mx-3 mt-3 mb-3">Đặt
					Vé</button>
			</div>
		</div>
	</div>
	<script>
        const seats = document.querySelectorAll('.chuaDat');
        let total = 0;
        let totalDichVu = 0;
        let allTotal = 0;
        let paymentInfo = {}
        let listDichVu = [];
        let tiLeKhuyenMai = parseInt(document.getElementById('tiLeKM').innerText);
        const giaTien = ${pc.donGia };

     // Đặt dịch vụ
		const item = document.querySelectorAll('.itemDichVu');
		const price = document.querySelectorAll('.donGia');
		const maDichVu = document.querySelectorAll('.maDichVu');
		console.log(price);
		const totalUnit = document.querySelectorAll('.tongTienDichVu');
		for ( let i = 0; i<item.length;i++) {
			item[i].addEventListener('change', () => {
				let unitPrice = parseInt(price[i].innerText);
				let unitQuantity = parseInt(item[i].value);
				let unitTotal = unitPrice * unitQuantity;
				
				totalUnit[i].innerText = " = " + unitTotal;
				let calculateTotal = 0;
				for(let i = 0; i< totalUnit.length; i++) {		
					calculateTotal += item[i].value * price[i].innerText ;
					}
				totalDichVu = calculateTotal;
				document.getElementById('totalDichVu').innerHTML = "<span class="+"fw-bold"+"> Tổng tiền dịch vụ :  <span>  " + totalDichVu; 
				allTotal = total + totalDichVu;
				finalTotal = (100-tiLeKhuyenMai)*allTotal/100;
                document.getElementById('total').innerHTML = "<span class="+"fw-bold"+"> Tổng tiền: <span>" + allTotal;
                document.getElementById('discount').innerHTML = "<span class="+"fw-bold"+"> Giảm giá: <span>" + tiLeKhuyenMai*allTotal/100;
                document.getElementById('totalFinal').innerHTML = "<span class="+"fw-bold"+"> Thành Tiền: <span>" + finalTotal;
				});
			}
		
		 // Đặt ghế
        seats.forEach(seat => {
            seat.addEventListener('click', () => {
                seat.classList.toggle('selected');
                if (seat.classList.contains('selected')) {
                    total += giaTien;
                    document.getElementById('chonGhe').innerHTML += "<span id="+seat.innerText+">" + seat.innerText + " </span>"
                } else {
                    total -= giaTien;
                    let p = document.getElementById("#" + seat.innerText + "" );
                    document.getElementById(seat.innerText).remove();
                }
                allTotal = total + totalDichVu;
                finalTotal = (100-tiLeKhuyenMai)*allTotal/100;
                document.getElementById('total').innerText = 'Tổng tiền: ' + allTotal;
                document.getElementById('discount').innerHTML = "<span class="+"fw-bold"+"> Giảm giá: <span>" + tiLeKhuyenMai*allTotal/100;
                document.getElementById('totalFinal').innerHTML = "<span class="+"fw-bold"+"> Thành Tiền: <span>" + finalTotal;
            });
        });
		
       
        document.getElementById('datVeButton').addEventListener('click', () => {
        	let currentDate = new Date();
        	let localDate = currentDate.toLocaleDateString();
        	let localTime = currentDate.toLocaleTimeString();
            let maKhachHang =  parseInt(document.getElementById('maKhachHang').innerText);
            let chosenSeat = document.querySelectorAll('.selected');
            let finalSeats = [];
            let maSuatChieu = document.getElementById('msc').innerText;
            for(let i = 0; i<chosenSeat.length;i++) {
                finalSeats.push(chosenSeat[i].innerText);
                }
            for(let i = 0; i< maDichVu.length;i++) {
            	let suDungDichVu = {};
				suDungDichVu.maDichVu = maDichVu[i].innerText;
				suDungDichVu.soLuong = item[i].value;
				if (suDungDichVu.soLuong != 0 ) {
				listDichVu.push(suDungDichVu);
					}
				}
            paymentInfo.maSuatChieu = document.getElementById('msc').innerText;
            paymentInfo.maGhe = finalSeats;
            paymentInfo.danhSachDichVu = listDichVu;
            paymentInfo.maKhuyenMai = document.getElementById('maKM').innerText;
            paymentInfo.maKhachHang = maKhachHang;
            console.log(paymentInfo);
            var jsonString = JSON.stringify(paymentInfo);
            console.log(jsonString);
        	var url = 'http://localhost:8080/CinemaWebApp/payment/create?param1=' + encodeURIComponent(finalTotal) + '&param2=' + encodeURIComponent(jsonString);
			console.log(url)
        	  // Redirect đến URL
        	window.location.href = url;
            });
    </script>
	<!-- Bootstrap core JS-->
	<%-- <script src="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"/>"> --%>
	<!-- Core theme JS-->
	<script src="<c:url value="/resources/js/scripts.js"/>"></script>

	<%-- <script src="<c:url value="https://cdn.startbootstrap.com/sb-forms-latest.js"/>"></script> --%>
</body>
</html>