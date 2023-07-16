/*
 * Project: Cinema WebApp
 * Team: 2
 * Author : Ducnm74
 * Controller: Quản lý vé
 */
package fa.training.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.entities.KhachHang;
import fa.training.entities.KhuyenMai;
import fa.training.entities.Phim;
import fa.training.entities.PhongChieu;
import fa.training.entities.SuatChieu;
import fa.training.entities.Ve;
import fa.training.service.DichVuService;
import fa.training.service.KhuyenMaiService;
import fa.training.service.PhongChieuService;
import fa.training.service.SuDungDichVuService;
import fa.training.service.SuatChieuService;
import fa.training.service.VeService;

@Controller
@RequestMapping("/ve")
public class VeController {
	@Autowired
	VeService veService;
	
	@Autowired
	SuatChieuService suatChieuService;
	
	@Autowired
	PhongChieuService phongChieuService;
	
	@Autowired
	DichVuService dichVuService;
	
	@Autowired
	KhuyenMaiService khuyenMaiService;
	
	@Autowired
	SuDungDichVuService suDungDichVuService;
	
	/*
	 * Project: Cinema WebApp
	 * Controller: Điều hướng đến trang đặt vé
	 */
	@GetMapping("/{maSuatChieu}")
	public String getVe(@PathVariable(name = "maSuatChieu") int maSuatChieu,Model model,HttpServletRequest request) {
		if (request.getSession().getAttribute("account") == null) {
			return "redirect:/dangnhap";
		}
		SuatChieu sc = suatChieuService.findById(maSuatChieu);
		PhongChieu pc = sc.getPhongChieu();
		Phim ph = sc.getPhim();
		int room = pc.getSoLuongGhe();
		int hang = 0;
		switch (room) {
		case 100:
			hang = 10;
			break;
		case 64: 
			hang = 8;
			break;
		case 50: 
			hang = 5;
		case 30:
			hang = 5;
		}
		char x = 'A';
		List<List<Ve>> listVe = new ArrayList<List<Ve>>();
		for (int i = 0; i<hang ; i++ ) {
			char y = (char)(x + i);
			List<Ve> listVeA = veService.search(maSuatChieu,"" + y);
			listVe.add(listVeA);
		}
		// Lay chuong trinh khuyen mai phu hop 
		KhachHang khachHang = (KhachHang)request.getSession().getAttribute("loggedInUser");
		int maKhachHang = khachHang.getMaKhachHang();
		long tongChiTieu = 	suDungDichVuService.getTotalTicketPaymentByCustomer(maKhachHang) + veService.getTotalTicketPaymentByCustomer(maKhachHang);
		System.out.println(tongChiTieu);
		model.addAttribute("maKhachHang", khachHang.getMaKhachHang());
		model.addAttribute("listVe",listVe);
		model.addAttribute("phim",ph);
		model.addAttribute("pc",pc);
		model.addAttribute("sc",sc);
		model.addAttribute("dichVu", dichVuService.findAll());
		model.addAttribute("khuyenMai", getKhuyenMai(maKhachHang));
		return "/ve/datve";
	}
	
	public KhuyenMai getKhuyenMai(int maKhachHang) {
		KhuyenMai khuyenMai;
		long tongChiTieu = 	suDungDichVuService.getTotalTicketPaymentByCustomer(maKhachHang) + veService.getTotalTicketPaymentByCustomer(maKhachHang);
		LocalDate toDay = LocalDate.now();
		LocalDate ngay31Thang5 = LocalDate.parse("2023-05-31");
		LocalDate mung9Thang6 = LocalDate.parse("2023-06-09");
		DayOfWeek now = toDay.getDayOfWeek();
		if ((now.getValue() == DayOfWeek.WEDNESDAY.getValue())){
			khuyenMai = khuyenMaiService.findById("KM00005");
		} else if ((ngay31Thang5.isBefore(toDay)&&mung9Thang6.isBefore(toDay))) {
			khuyenMai = khuyenMaiService.findById("KM00008");
		} else if (tongChiTieu > 5000000 ) {
			khuyenMai = khuyenMaiService.findById("KM00003");
		} else if (tongChiTieu > 2000000) {
			khuyenMai = khuyenMaiService.findById("KM00002");
		} else {
			khuyenMai = khuyenMaiService.findById("KM00000");
		}
		System.out.println(tongChiTieu);
		return khuyenMai;
	}
} 
