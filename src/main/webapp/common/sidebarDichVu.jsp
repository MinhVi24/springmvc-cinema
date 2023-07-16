<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Sidebar -->
<div class="d-flex flex-column flex-shrink-0 mw-100"
	style="width: 0px; margin: 145px 0 100px 50px">
	<nav id="sidebarMenu"
		class="collapse d-lg-block sidebar collapse bg-white">
		<div class="position-sticky">
			<div class="list-group list-group-flush mx-3 mt-4">
				<a href="${pageContext.request.contextPath}/admin/dichvu/them"
					class="list-group-item list-group-item-action py-2 taohover taofocus taoactive active "
					aria-current="true"> <i
					class="fa-brands fa-servicestack fa-fw me-3  "></i><span>Thêm
						dịch vụ</span>
				</a> <a href="${pageContext.request.contextPath}/admin/dichvu/list"
					class="list-group-item list-group-item-action py-2 taohover taofocus taoactive ">
					<i class="fa-solid fa-list fa-fw me-3"></i><span>Danh sách
						dịch vụ</span>
				</a> <a href="${pageContext.request.contextPath}/admin/khuyenmai/them"
					class="list-group-item list-group-item-action py-2 taohover taofocus taoactiv "><i
					class="fa-solid fa-gift fa-fw me-3"></i><span>Thêm khuyến
						mãi</span></a> <a href="${pageContext.request.contextPath}/admin/khuyenmai/list"
					class="list-group-item list-group-item-action py-2 taohover taofocus taoactive "><i
					class="fa-solid fa-list fa-fw me-3"></i><span>Danh sách
						khuyến mãi</span></a>
			</div>
		</div>
	</nav>
</div>
<!-- Sidebar -->