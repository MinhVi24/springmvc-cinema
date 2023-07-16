package fa.training.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fa.training.entities.KhachHang;
import fa.training.entities.Phim;
import fa.training.entities.SuDungDichVu;
import fa.training.entities.Ve;
import fa.training.model.PhimDoanhThu;
import fa.training.page.PageAble;
import fa.training.service.KhachHangService;
import fa.training.service.PhimService;
import fa.training.service.SuDungDichVuService;
import fa.training.service.TaiKhoanService;
import fa.training.service.VeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	PhimService phimService;

	@Autowired
	VeService veService;

	@Autowired
	SuDungDichVuService suDungDichVuService;

	@Autowired
	KhachHangService khachHangService;

	@Autowired
	TaiKhoanService taiKhoanService;

	@GetMapping()
	public String getHomePage() {
		return "redirect:/trangchu";
	}

	/*
	 * Project: Cinema WebApp  Method: Thống kê thông tin
	 * khách hàng
	 */
	@GetMapping("thongke/khachhang")
	public String getStatisticKH(Model model) {
		long soNu = khachHangService.getCountByGender("Nữ");
		long soNam = khachHangService.getCountByGender("Nam");
		long tongSo = soNu + soNam;
		long soThieuNien = khachHangService.getCountByAge(0, 18);
		long soThanhNien = khachHangService.getCountByAge(19, 24);
		long soTruongThanh = khachHangService.getCountByAge(25, 30);
		long soTrungNien = khachHangService.getCountByAge(31, 45);
		long soNguoiLon = khachHangService.getCountByAge(46, 999);
		long[] soLieu = { tongSo, soNu, soNam, soThieuNien, soThanhNien, soTruongThanh, soTrungNien, soNguoiLon };
		model.addAttribute("soLieu", soLieu);
		return "/admin/thongkekhachhang";
	}

	/*
	 * Project: Cinema WebApp  
	 * Method: Thống kê doanh thu rạp chiếu phim theo thời gian (màn hình chính)
	 */
	@GetMapping("thongke/doanhthu")
	public String getStatisticDTByYear(Model model) {
		int nam = 2023;
		List<Object[]> listVe = veService.getDoanhThuTheoNam(nam);
		List<Object[]> listDV = suDungDichVuService.getDoanhThuTheoNam(nam);
		if (listVe.size() == 0 && listDV.size() == 0) {
			model.addAttribute("noInfo", "Hệ thống không tìm thấy có dữ liệu doanh thu cho năm bạn chọn");
			return "/admin/thongkedoanhthutheonam";
		}
		long tongDoanhThu = 0;
		List<long[]> listTong = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			long[] x = new long[5];
			x[0] = i;
			for (Object[] objects : listVe) {
				if (i == Integer.parseInt(objects[0].toString())) {
					tongDoanhThu += Math.round(Double.parseDouble(objects[2].toString()));
					x[1] = Math.round(Double.parseDouble(objects[1].toString()));
					x[2] = Math.round(Double.parseDouble(objects[2].toString()));
					break;
				} else {
					x[1] = 0;
					x[2] = 0;
				}
			}
			for (Object[] objects : listDV) {
				if (i == Integer.parseInt(objects[0].toString())) {
					tongDoanhThu += Math.round(Double.parseDouble(objects[1].toString()));
					x[3] = Math.round(Double.parseDouble(objects[1].toString()));
					break;
				} else {
					x[3] = 0;
				}
			}
			x[4] = x[2] + x[3];
			listTong.add(x);
		}
		model.addAttribute("listDoanhThuPhim", listTong);
		model.addAttribute("tongDoanhThu", tongDoanhThu);
		model.addAttribute("year", nam);
		return "/admin/thongkedoanhthutheonam";
	}

	/*
	 * Project: Cinema WebApp
	 *  Method: Thống kê doanh thu rạp chiếu phim theo phim
	 */
	@GetMapping("thongke/doanhthu/theophim")
	public String getStatisticDT(Model model) {
		List<PhimDoanhThu> phimDoanhThu = new ArrayList<>();
		List<Object[]> list = phimService.getDoanhThu();
		for (int i = 0; i < list.size(); i++) {
			PhimDoanhThu pdt = new PhimDoanhThu();
			pdt.setMaPhim(list.get(i)[0].toString());
			pdt.setTenPhim(list.get(i)[1].toString());
			pdt.setDoanhThu(Math.round(Double.parseDouble(list.get(i)[2].toString())));
			phimDoanhThu.add(pdt);
		}
		model.addAttribute("listDoanhThuPhim", phimDoanhThu);
		return "/admin/thongkedoanhthutheophim";
	}

	/*
	 * Project: Cinema WebApp  
	 * Method: Thống kê doanh thu rạp chiếu phim theo tháng
	 */
	@GetMapping("thongke/doanhthu/theothang")
	public String getStatisticDTByMonth(Model model, @RequestParam(name = "thang") String thang,
			@RequestParam(name = "nam") String nam) {
		int year = Integer.parseInt(nam);
		int month = Integer.parseInt(thang);
		List<Object[]> listVe = veService.getDoanhThuTheoThang(month, year);
		List<Object[]> listDV = suDungDichVuService.getDoanhThuTheoThang(month, year);
		if (listVe.size() == 0 && listDV.size() == 0) {
			model.addAttribute("noInfo", "Hệ thống không tìm thấy có dữ liệu doanh thu cho năm bạn chọn");
			return "/admin/thongkedoanhthutheothang";
		}
		long tongDoanhThu = 0;
		List<Object[]> listTong = new ArrayList<>();
		for (int i = 1; i <= 31; i++) {
			String day = "";
			LocalDate date;
			try {
				String toMonth = "-" + month;
				if (month < 10) {
					toMonth = "-0" + month;
				}
				String toDay = "-" + i;
				if (i < 10) {
					toDay = "-0" + i;
				}
				day = year + toMonth + toDay;
				date = LocalDate.parse(day);
			} catch (DateTimeParseException e) {
				System.out.println(day + " cannot be parse");
				continue;
			} catch (Exception e) {
				continue;
			}
			Object[] x = new Object[5];
			x[0] = date;
			for (Object[] objects : listVe) {
				LocalDate toDate = LocalDate.parse(objects[0].toString());
				if (date.equals(toDate)) {
					tongDoanhThu += Math.round(Double.parseDouble(objects[2].toString()));
					x[1] = Math.round(Double.parseDouble(objects[1].toString()));
					x[2] = Math.round(Double.parseDouble(objects[2].toString()));
					break;
				} else {
					x[1] = 0;
					x[2] = 0;
				}
			}
			for (Object[] objects : listDV) {
				LocalDate toDate = LocalDate.parse(objects[0].toString());
				if (date.equals(toDate)) {
					tongDoanhThu += Math.round(Double.parseDouble(objects[1].toString()));
					x[3] = Math.round(Double.parseDouble(objects[1].toString()));
					break;
				} else {
					x[3] = 0;
				}
			}
			x[4] = Long.parseLong(x[2].toString()) + Long.parseLong(x[3].toString());
			listTong.add(x);
		}
		model.addAttribute("listDoanhThuPhim", listTong);
		model.addAttribute("tongDoanhThu", tongDoanhThu);
		model.addAttribute("month", thang);
		model.addAttribute("year", nam);
		return "/admin/thongkedoanhthutheothang";
	}

	/*
	 * Project: Cinema WebApp 
	 * Method: Thống kê doanh thu rạp chiếu phim theo tháng
	 */
	@GetMapping("thongke/doanhthu/theongay")
	public String getStatisticDTByMonth(Model model, @RequestParam(name = "ngay") String ngay) {
		LocalDate date = LocalDate.parse(ngay);
		List<Object[]> listVe = veService.getDoanhThuBanVeTheoNgay(LocalDate.parse(ngay));
		List<Object[]> listDV = suDungDichVuService.getDoanhThuDichVuTheoNgay(LocalDate.parse(ngay));
		if (listVe.size() == 0 && listDV.size() == 0) {
			model.addAttribute("noInfo", "Hệ thống không tìm thấy có dữ liệu doanh thu cho ngay bạn chọn");
			return "/admin/thongkedoanhthutheongay";
		}
		double tongDoanhThu = 0;
		double tongDoanhThuDichVu = 0;
		double tongDoanhThuBanVe = 0;
		for (Object[] objects : listVe) {
			objects[3] = Double.parseDouble(objects[3].toString());
			double x = Double.parseDouble(objects[3].toString());
			System.out.println(objects[3].toString());
			tongDoanhThuBanVe += x;
		}
		for (Object[] objects : listDV) {
			objects[3] = Double.parseDouble(objects[3].toString());
			double y = Double.parseDouble(objects[3].toString());
			System.out.println(objects[3].toString());
			tongDoanhThuDichVu += y;
		}
		tongDoanhThu = tongDoanhThuBanVe + tongDoanhThuDichVu;
		model.addAttribute("listDoanhThuBanVe", listVe);
		model.addAttribute("listDoanhThuDichVu", listDV);
		model.addAttribute("tongDoanhThuBanVe", tongDoanhThuBanVe);
		model.addAttribute("tongDoanhThuDichVu", tongDoanhThuDichVu);
		model.addAttribute("tongDoanhThu", tongDoanhThu);
		model.addAttribute("ngay", ngay);
		return "/admin/thongkedoanhthutheongay";
	}

	/*
	 * Project: Cinema WebApp  
	 * Method: Thống kê doanh thu rạp chiếu phim theo năm tùy chọn
	 */
	@GetMapping("thongke/doanhthu/theonam")
	public String getStatisticDTByYear(Model model, @RequestParam(name = "nam") String nam) {
		List<Object[]> listVe = veService.getDoanhThuTheoNam(Integer.parseInt(nam));
		List<Object[]> listDV = suDungDichVuService.getDoanhThuTheoNam(Integer.parseInt(nam));
		if (listVe.size() == 0 && listDV.size() == 0) {
			model.addAttribute("noInfo", "Hệ thống không tìm thấy có dữ liệu doanh thu cho năm bạn chọn");
			return "/admin/thongkedoanhthutheonam";
		}
		long tongDoanhThu = 0;
		List<long[]> listTong = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			long[] x = new long[5];
			x[0] = i;
			for (Object[] objects : listVe) {
				if (i == Integer.parseInt(objects[0].toString())) {
					tongDoanhThu += Math.round(Double.parseDouble(objects[2].toString()));
					x[1] = Math.round(Double.parseDouble(objects[1].toString()));
					x[2] = Math.round(Double.parseDouble(objects[2].toString()));
					break;
				} else {
					x[1] = 0;
					x[2] = 0;
				}
			}
			for (Object[] objects : listDV) {
				if (i == Integer.parseInt(objects[0].toString())) {
					tongDoanhThu += Math.round(Double.parseDouble(objects[1].toString()));
					x[3] = Math.round(Double.parseDouble(objects[1].toString()));
					break;
				} else {
					x[3] = 0;
				}
			}
			x[4] = x[2] + x[3];
			listTong.add(x);
		}
		model.addAttribute("listDoanhThuPhim", listTong);
		model.addAttribute("tongDoanhThu", tongDoanhThu);
		model.addAttribute("year", nam);
		return "/admin/thongkedoanhthutheonam";
	}

	@RequestMapping(value = "/")
	@ModelAttribute
	public String trangChu(Model model, @RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<Phim> listPhim = phimService.findPhimDangChieu(pageAble);
		model.addAttribute("totalPages", phimService.totalPagesPhimDangChieu(pageAble));
		model.addAttribute("currentPage", page);
		model.addAttribute("listPhim", listPhim);
		return "index";
	}

	/**
	 * Project: Cinema WebApp  
	 *  Author : ViTM
	 *  Method : phan trang hien thi list danh sach cua khach Hang ben admin
	 */
	@GetMapping("/listkhachhang")
	public String getAllmayWithPageAbleKhach(Model model, @RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<KhachHang> khachHang = khachHangService.findWithPageAble(pageAble);
		model.addAttribute("khachHang", khachHang);
		model.addAttribute("totalPages", khachHangService.totalPages(pageAble));
		model.addAttribute("currentPage", page);
		return "/admin/listkhachhang";
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Method : phan trang hien thi list danh sach cua khach Hang ben admin
	 */
	@GetMapping("/listlichsu")
	public String getAllmayWithPageAbleDichVu(Model model, @RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<KhachHang> khachHang = khachHangService.findWithPageAble(pageAble);
		if (khachHang.size() == 0) {

			model.addAttribute("msg1", "Không có kết quả ");
			return "/admin/listlichsu";
		}
		model.addAttribute("khachHang", khachHang);
		model.addAttribute("totalPages", khachHangService.totalPages(pageAble));
		model.addAttribute("currentPage", page);
		return "/admin/listlichsu";
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Method : tim kiem khách hàng có trong list theo Tên
	 */

	@RequestMapping( "/searchkhachhang" )
	public String searchKhachHang(@RequestParam(name = "tenKhachHang") String tenKhachHang, Model model,
			@RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble((int) page);
		List<KhachHang> khachHang = (List<KhachHang>) khachHangService.search(tenKhachHang, pageAble);
		model.addAttribute("khachHang", khachHang);
		model.addAttribute("tenKhachHang1", tenKhachHang);
		if (khachHang.size() == 0) {
			model.addAttribute("search", "Kh\u00f4ng c\u00f3 k\u1ebft qu\u1ea3 t\u00ecm ki\u1ebfm");
		}
		model.addAttribute("khachHang", khachHang);
		model.addAttribute("totalPages", khachHangService.totalPagesSearch(tenKhachHang, pageAble));
		model.addAttribute("currentPage", page);
		return "/admin/searchkhachhang";
	}
	/**
	 * Project: Cinema WebApp  
	 *  Author : ViTM
	 *   Method : tim kiem khách hàng có trong list theo Tên
	 */
	@RequestMapping("/searchlichsu")
	public String searchLichSu(@RequestParam(name = "tenKhachHang") String tenKhachHang, Model model,
			@RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble((int) page);
		List<KhachHang> khachHang = khachHangService.search(tenKhachHang, pageAble);
		model.addAttribute("khachHang", khachHang);
		model.addAttribute("tenKhachHang1", tenKhachHang);
		if (khachHang.size() == 0) {
			model.addAttribute("search", "không có kết quả tìm kiếm");
		}
		model.addAttribute("khachHang", khachHang);
		model.addAttribute("totalPages", khachHangService.totalPagesSearch(tenKhachHang, pageAble));
		model.addAttribute("currentPage", page);
		return "/admin/searchlichsu";
	}

	/**
	 * Project: Cinema WebApp  
	 * Author : ViTM 
	 * Method : url
	 */
	@RequestMapping("/update/{maKhachHang}")
	public String update(@PathVariable("maKhachHang") int maKhachHang, Model model) {
		KhachHang khachHang = khachHangService.findById(maKhachHang);

		model.addAttribute("khachHang", khachHang);
		return "/admin/update";
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM Method : Update
	 */
	@PostMapping("/update")
	public String update(@ModelAttribute("khachHang") @Valid KhachHang khachHang, BindingResult result, Model model) {

		LocalDate ngayHienTai = LocalDate.now();

		if (khachHang.getNgaySinh().isAfter(ngayHienTai)) {
			return "/admin/update";
		}

		if (result.hasErrors()) {
			model.addAttribute("thatbai", "Cập nhật không thành công");
			return "/admin/update";
		}
		model.addAttribute("khachHang", khachHang);
		khachHangService.saveOrUpdate(khachHang);
		model.addAttribute("update", "Đã cập nhật thành công");
		return "/admin/update";
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Method : hiển thị list lịch sử dịch vụ khách hàng 
	 */
	@GetMapping({ "/listdichvu/{maKhachHang}" })
	public String danhsachdichvu(@PathVariable("maKhachHang") int maKhachHang, Model model,
			@RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble((int) page);
		List<SuDungDichVu> suDungDichVu = (List<SuDungDichVu>) this.suDungDichVuService.findWithPageAbleall(pageAble,
				maKhachHang);
		model.addAttribute("totalPages", this.suDungDichVuService.totalPagesall(pageAble, maKhachHang));
		model.addAttribute("currentPage", page);
		if (suDungDichVu.size() == 0) {
			model.addAttribute("msg1", "khách hàng chưa sử dụng dịch vụ. ");
			return "/admin/listdichvu";
		}
		model.addAttribute("suDungDichVu", suDungDichVu);
		return "/admin/listdichvu";
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM
	 * Method : hiển thị list khách Hàng mua vé
	 */
	@RequestMapping( "/listmuave/{maKhachHang}" )
	public String danhsachmuave(@PathVariable("maKhachHang") int maKhachHang, Model model,
			@RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<Ve> ve = veService.findWithPageAbleall(pageAble, maKhachHang);
		model.addAttribute("totalPages", this.veService.totalPagesall(pageAble, maKhachHang));
		model.addAttribute("currentPage", page);
		if (ve.size() == 0) {
			model.addAttribute("msg1", "khách hàng chưa sử dụng dịch vụ. ");
			return "/admin/listmuave";
		}
		model.addAttribute("ve", ve);
		return "/admin/listmuave";
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Method : xóa khách hàng khỏi  danh sach
	 */

	@GetMapping( "/delete/{maKhachHang}" )
	public String delete(@PathVariable(name = "maKhachHang") int maKhachHang, Model model,
			@RequestParam(defaultValue = "1") Integer page,RedirectAttributes redirectAttributes) {
		if (suDungDichVuService.findKhachHang(maKhachHang).size() == 0) {
			khachHangService.delete(maKhachHang);
			PageAble pageAble = new PageAble( page);
			List<KhachHang> khachHang =  khachHangService.findWithPageAble(pageAble);
			model.addAttribute("khachHang", khachHang);
			model.addAttribute("totalPages", khachHangService.totalPages(pageAble));
			model.addAttribute("currentPage", page);
			model.addAttribute("thanhcong", "Đã xóa thành công khách hàng có mã là : ");
			model.addAttribute("maKhachHang", maKhachHang);
			return "/admin/deletethanhcong";
		}
		PageAble pageAble = new PageAble( page);
		List<KhachHang> khachHang =  khachHangService.findWithPageAble(pageAble);
		model.addAttribute("khachHang", khachHang);
		model.addAttribute("totalPages", khachHangService.totalPages(pageAble));
		model.addAttribute("currentPage", page);
		redirectAttributes.addFlashAttribute("thatbai", "không thể xóa khách hàng có mã là  : ");
		redirectAttributes.addFlashAttribute("maKhachHang", maKhachHang);
		return "redirect:/admin/listkhachhang";
	}

}
