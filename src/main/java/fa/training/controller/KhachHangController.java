package fa.training.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import fa.training.entities.KhachHang;
import fa.training.entities.Phim;
import fa.training.entities.SuatChieu;
import fa.training.model.HienThiPhim;
import fa.training.model.NgayChieu;
import fa.training.page.PageAble;
import fa.training.service.KhachHangService;
import fa.training.service.KhuyenMaiService;
import fa.training.service.PhimService;
import fa.training.service.SuDungDichVuService;
import fa.training.service.SuatChieuService;
import fa.training.service.VeService;

@Controller
@RequestMapping(value = "/khachhang")
public class KhachHangController {
	@Autowired
	PhimService phimService;

	@Autowired
	KhachHangService khachHangService;

	@Autowired
	SuDungDichVuService suDungDichVuService;

	@Autowired
	VeService veService;

	@Autowired
	SuatChieuService suatChieuService;

	@Autowired
	KhuyenMaiService khuyenMaiService;
	/**
	 * Trang tìm kiếm Proeject: Cinema WebApp
	 */
	@GetMapping("/search")
	public String search(@RequestParam(name = "searchKey", required = false) String searchKey,
			@RequestParam(name = "mydate") String date, @RequestParam("timkiem") String timKiem, Model model) {
		List<Phim> listPhim = phimService.findTop3();
		List<Phim> listPhim1 = phimService.findAllSauTop3();
		Phim phim = phimService.findById("PH00061");
		model.addAttribute("listPhim", listPhim);
		model.addAttribute("listPhim1", listPhim1);
		model.addAttribute("phim", phim);
		model.addAttribute("top3KM", khuyenMaiService.findAll());
		List<Phim> listPhim2 = new ArrayList<>();
		if (searchKey.length() == 0 && date.length() == 0) {
			model.addAttribute("msg10", "Không tìm thấy thông tin phù hợp");
			return "/phim/trangchusearch";
		}
		if (timKiem.equals("1")) {
			listPhim2 = phimService.searchTenPhim(searchKey);
			model.addAttribute("listPhim2", listPhim2);
			model.addAttribute("searchKey", searchKey);
		}
		if (timKiem.equals("2")) {
			listPhim2 = phimService.searchDaoDien(searchKey);
			model.addAttribute("listPhim2", listPhim2);
			model.addAttribute("searchKey", searchKey);
		}
		if (timKiem.equals("3")) {
			listPhim2 = phimService.searchTheLoai(searchKey);
			model.addAttribute("listPhim2", listPhim2);
			model.addAttribute("searchKey", searchKey);
		}
		if (timKiem.equals("4")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date1 = LocalDate.parse(date, formatter);
			List<SuatChieu> listSuatPhim = phimService.searchNgayChieu(date1);
			for (SuatChieu suat : listSuatPhim) {
				Phim phimsearch = new Phim();
				phimsearch.setMaPhim(suat.getPhim().getMaPhim());
				phimsearch.setTenPhim(suat.getPhim().getTenPhim());
				phimsearch.setMoTaPhim(suat.getPhim().getMoTaPhim());
				phimsearch.setNoiDungPhim(suat.getPhim().getNoiDungPhim());
				phimsearch.setDaoDien(suat.getPhim().getDaoDien());
				phimsearch.setNgayKhoiChieu(suat.getPhim().getNgayKhoiChieu());
				phimsearch.setNgayKetThuc(suat.getPhim().getNgayKetThuc());
				phimsearch.setThoiLuong(suat.getPhim().getThoiLuong());
				phimsearch.setPoster(suat.getPhim().getPoster());
				listPhim2.add(phimsearch);
			}
			model.addAttribute("listPhim2", listPhim2);
			model.addAttribute("date", date1);
		}
		if (listPhim2.size() == 0) {
			model.addAttribute("msg10", "Không tìm thấy thông tin phù hợp");
			return "/phim/trangchusearch";
		}
		return "/phim/trangchusearch";
	}

	/**
	 *  Proeject: Cinema WebApp 
	 *  Author : ViTM 
	 *  Method  :Trang hiển thị chi tiết một bộ phim
	 */
	@GetMapping("/phimview/{id}")
	public String viewDangKy(@PathVariable String id, Model model) {
		Phim phim1 = phimService.findById(id);
		System.out.println(phim1.getPoster());
		HienThiPhim phim = new HienThiPhim();
		phim.setMaPhim(phim1.getMaPhim());
		phim.setTenPhim(phim1.getTenPhim());
		phim.setMoTaPhim(phim1.getMoTaPhim());
		phim.setNoiDungPhim(phim1.getNoiDungPhim());
		phim.setDaoDien(phim1.getDaoDien());
		phim.setThoiLuong(phim1.getThoiLuong());
		String ngayKhoiChieu = phim1.getNgayKhoiChieu().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		phim.setNgayKhoiChieu(ngayKhoiChieu);
		String ngayKetThuc = phim1.getNgayKetThuc().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		phim.setNgayKetThuc(ngayKetThuc);
		phim.setPoster(phim1.getPoster());
		System.out.println(phim.getPoster());
		List<Phim> listPhim = phimService.findPhimGoiY(phim1.getMoTaPhim(),phim1.getMaPhim());
		LocalDate now = LocalDate.now();
		List<SuatChieu> suatChieus = suatChieuService.searchByDate(phim1.getMaPhim(), now);
		model.addAttribute("NgayChieu", new NgayChieu());
		model.addAttribute("suatChieus", suatChieus);
		model.addAttribute("listPhim", listPhim);
		model.addAttribute("phim", phim);
		if (suatChieus.size() == 0) {
			model.addAttribute("suatChieuMessage",
					"Hiện tại chưa có suất chiếu cho phim vào ngày này, vui lòng chọn lại ngày!");
		}
		return "/phim/phimview";
	}

	/**
	 *  Proeject: Cinema WebApp 
	 *  Author : ViTM 
	 *  Method: Trang hiển thị chi tiết một bộ phim
	 */
	@PostMapping("/phimview/{id}")
	public String viewTheoNgay(@PathVariable String id, Model model, @ModelAttribute("NgayChieu") NgayChieu ngayChieu) {
		System.out.println(ngayChieu.getNgayChieu());
		System.out.println(ngayChieu.getMaPhim());
		Phim phim1 = phimService.findById(id);
		System.out.println(phim1.getPoster());
		HienThiPhim phim = new HienThiPhim();
		phim.setMaPhim(phim1.getMaPhim());
		phim.setTenPhim(phim1.getTenPhim());
		phim.setMoTaPhim(phim1.getMoTaPhim());
		phim.setDaoDien(phim1.getDaoDien());
		phim.setThoiLuong(phim1.getThoiLuong());
		String ngayKhoiChieu = phim1.getNgayKhoiChieu().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		phim.setNgayKhoiChieu(ngayKhoiChieu);
		String ngayKetThuc = phim1.getNgayKetThuc().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		phim.setNgayKetThuc(ngayKetThuc);
		phim.setPoster(phim1.getPoster());
		System.out.println(phim.getPoster());
		List<SuatChieu> suatChieus = suatChieuService.searchByDate(phim1.getMaPhim(), ngayChieu.getNgayChieu());
		if (suatChieus.size() == 0) {
			model.addAttribute("suatChieuMessage",
					"Hiện tại chưa có suất chiếu cho phim vào ngày này, vui lòng chọn lại ngày!");
			model.addAttribute("NgayChieu", new NgayChieu());
			model.addAttribute("suatChieus", suatChieus);
			model.addAttribute("listPhim", phimService.findPhimGoiY(phim1.getMoTaPhim(),phim1.getMaPhim()));
			model.addAttribute("phim", phim);
			return "/phim/phimview";
		}
		model.addAttribute("NgayChieu", new NgayChieu());
		model.addAttribute("suatChieus", suatChieus);
		model.addAttribute("listPhim", phimService.findPhimGoiY(phim1.getMoTaPhim(),phim1.getMaPhim()));
		model.addAttribute("phim", phim);
		return "/phim/phimview";
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Method : hiển thị from cập nhật thông tin 
	 */

	@RequestMapping("/control/updateid")
	public String update(Model model, HttpServletRequest request) {
		KhachHang khachHang = (KhachHang) request.getSession().getAttribute("loggedInUser");
		model.addAttribute("khachHang", khachHang);
		return "/khachhang/update";
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Fuciton : update khách hàng
	 */

	@RequestMapping("/control/update")
	public String update(@ModelAttribute("khachHang") @Valid KhachHang khachHang, BindingResult result, Model model) {
		LocalDate ngayHienTai = LocalDate.now();

		if (khachHang.getNgaySinh().isAfter(ngayHienTai)) {
			return "/khachhang/update";
		}

		if (result.hasErrors()) {
			model.addAttribute("thatbai", "Cập nhật không thành công");
			return "/khachhang/update";
		}
		model.addAttribute("khachHang", khachHang);
		khachHangService.saveOrUpdate(khachHang);
		model.addAttribute("update", "Đã cập nhật thành công");
		return "/khachhang/update";
	}

	/**
	 * Project: Cinema WebApp  
	 *  Author : ViTM 
	 *  Fuciton : hiển thị thông tin
	 * 
	 */

	@GetMapping("/control/listdichvu")
	public String listdichvu(Model model, @RequestParam(defaultValue = "1") Integer page, HttpServletRequest request) {
		KhachHang khachHang = (KhachHang) request.getSession().getAttribute("loggedInUser");
		PageAble pageAble = new PageAble(page);
		model.addAttribute("suDungDichVu", suDungDichVuService.findWithPageAble(pageAble, khachHang.getMaKhachHang()));
		model.addAttribute("totalPages", suDungDichVuService.totalPages(pageAble, khachHang.getMaKhachHang()));
		model.addAttribute("currentPage", page);

		return "/khachhang/listdichvu";
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Fuciton : hiển thị thông tin
	 * 
	 */
	@GetMapping("/control/listmuave")
	public String listmuave(Model model, @RequestParam(defaultValue = "1") Integer page, HttpServletRequest request) {
		KhachHang khachHang = (KhachHang) request.getSession().getAttribute("loggedInUser");
		PageAble pageAble = new PageAble(page);
		model.addAttribute("ve", veService.findWithPageAble(pageAble, khachHang.getMaKhachHang()));
		model.addAttribute("totalPages", veService.totalPages(pageAble, khachHang.getMaKhachHang()));
		model.addAttribute("currentPage", page);

		return "/khachhang/listmuave";
	}
	
	@RequestMapping("/khuyenmaiinfo")
	public String myHandler(Model model) {
		model.addAttribute("allKM", khuyenMaiService.findAll());
		return "/khachhang/KM00001"; // Trả về tên của tệp JSP mà bạn muốn hiển thị
	}
}
