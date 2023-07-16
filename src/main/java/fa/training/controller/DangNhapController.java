package fa.training.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.entities.SuatChieu;
import fa.training.entities.TaiKhoan;
import fa.training.service.KhachHangService;
import fa.training.service.SuatChieuService;
import fa.training.service.TaiKhoanService;
/**
 * 
 * Project: Cinema WebApp
 * Controller Dang Nhap
 */
@Controller
public class DangNhapController {
	
	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@Autowired
	private KhachHangService khachHangService;
	
	@Autowired
	SuatChieuService suatChieuService;
	
	
	// Hiển thị màn hình Welcome
		// Hiển thị màn hình đăng nhập
		@GetMapping(value = "/dangnhap")
		public String dangNhap (@RequestParam(name = "login-error", defaultValue = "false", required = false) boolean loginError, Model model) {
			if (loginError) {
				model.addAttribute("messageAccount", "Thông Tin Tài Khoản Hoặc Mật Khẩu Không Chính Xác!");
				return "/taikhoan/dangnhap";		
			}
			return "/taikhoan/dangnhap";
		}
		
		// Chức năng đăng xuất khi người dùng có đăng nhập và điều hướng về màn hình đăng nhập ban đầu
		@GetMapping(value = "/dangxuat")
		public String dangXuat (HttpServletRequest request, HttpServletResponse response) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null) {
				new SecurityContextLogoutHandler().logout(request, response, authentication);
			}
			return "redirect:/trangchu";
		}
		
		// Method này dùng để điều hướng theo phân quyền tới 2 trang khác nhau sau khi đăng nhập thành công
		@GetMapping(value = "/role")
		public String hienThiTheoPhanQuyen(Principal principal,HttpSession session) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String account = principal.getName();
			TaiKhoan tk = taiKhoanService.finByAccount(account);
			boolean isAdmin = authentication.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.anyMatch(role -> role.equals("ROLE_ADMIN"));
			if (isAdmin) {
				session.setAttribute("account", tk);
				session.setAttribute("loggedInUser", khachHangService.findById(tk.getKhachHang().getMaKhachHang()));
				return "redirect:/admin";
			}
			if (session.getAttribute("suatChieu") != null) {
				int maSuatChieu = ((SuatChieu)session.getAttribute("suatChieu")).getMaSuatChieu();
				session.setAttribute("account", tk);
				session.setAttribute("loggedInUser", khachHangService.findById(tk.getKhachHang().getMaKhachHang()));
				return "redirect:/ve/" + maSuatChieu;
			}

			session.setAttribute("account", tk);
			session.setAttribute("loggedInUser", khachHangService.findById(tk.getKhachHang().getMaKhachHang()));
			return "redirect:/trangchu";
		}

	
	@GetMapping("/dangnhap/{maSuatChieu}")
	public String getDangNhapDatVe(@PathVariable(name = "maSuatChieu") String maSuatChieu,Model model,HttpSession session) {
		SuatChieu suatChieu = suatChieuService.findById(Integer.parseInt(maSuatChieu));
		session.setAttribute("suatChieu", suatChieu);
		return "/taikhoan/dangnhapphim";
	}
	
//	@PostMapping("/{maSuatChieu}")
//	public String dangNhapDatVe(@ModelAttribute("taikhoan") TaiKhoan taiKhoan, HttpServletRequest request,
//			HttpServletResponse response,@PathVariable(name = "maSuatChieu") String maSuatChieu) {
//		HttpSession session = request.getSession();
//		if (taiKhoanService.finByAccount(taiKhoan.getAccount(), taiKhoan.getPassword()) == true) {
//			TaiKhoan account = taiKhoanService.finByAccount(taiKhoan.getAccount());
//			session.setAttribute("account", account);
//			KhachHang khachHang = account.getKhachHang();
//			session.setAttribute("loggedInUser", khachHang);
//			return "redirect:/ve/" + maSuatChieu;
//		} else {
//			request.setAttribute("messageAccount", "Thông Tin Tài Khoản Hoặc Mật Khẩu Không Chính Xác!");
//			return "/taikhoan/dangnhap";
//
//		}
//	}
	
}
