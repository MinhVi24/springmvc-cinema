<!-- * Project: Cinema WebApp
	 * thông tin khuyến mãi-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYpE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm khuyến mãi</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">
<style>
p {
	font-size: 25px;
}
</style>
</head>
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/headerkhach.jsp"%>
	<div class=" main-body d-flex justify-content-center">

		<form:form class="form d-flex justify-content-center"
			action="${pageContext.request.contextPath}/admin/khuyenmai/luu"
			method="post" modelAttribute="khuyenmai" id="form1">
			<div class="row" style="margin-left: 21%">
				<h2 class="section-heading text-uppercase fw-bold">Quét mã VN
					Pay, giảm ngay 15K!</h2>
				<div class="col-12">
					<div>
						<table class="table table-bordered">
					<thead class="text-white">
						<tr>
							<th width="10%">MÔ TẢ KHUYẾN MÃI</th>
							<th width="10%">NGÀY BẮT ĐẦU</th>
							<th width="10%">NGÀY KẾT THÚC</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="abc" items="${allKM}" varStatus="status">
							<tr>
								<td>${abc.moTaKhuyenMai}</td>
								<td style="padding-top: 1%">${abc.ngayBatDau}</td>
								<td style="padding-top: 1%">${abc.ngayKetThuc}</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
					</div>
					<div>
						<p>
							Khách hàng sẽ được hưởng loạt Bom tấn điện ảnh Việt tại Galaxy
							trong dịp lễ này, còn gì tuyệt vời hơn khi vừa được thưởng thức
							phim hay, hưởng ưu đãi ngập tràn mà còn được giảm giá khi thanh
							toán qua VNPAY-QR, chi tiết như sau:<br> Bước 1: Khách hàng
							mở Ví VNPAY/App Mobile Banking và chọn "Quét QR" <br>Bước
							2: Scan mã thanh toán trên màn hình của Thu ngân <br>Bước
							3: Tại bước chờ xác nhận thanh toán, Khách hàng nhập mã
							"VNPAYGLX" hoặc "VNPAYGLX2" tại ô "Nhập mã giảm giá"<br>
							Bước 4: Bấm "Xác nhận thanh toán" Khách hàng sẽ được giảm ngay
							15K!
					</div>
				</div>
			</div>
			<p></p>
		</form:form>
		<br />
	</div>
</body>
</html>