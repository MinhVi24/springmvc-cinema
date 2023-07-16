package fa.training.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fa.training.entities.KhachHang;
import fa.training.entities.TaiKhoan;
import fa.training.model.InsertDK;
import fa.training.model.ResetPassword;
import fa.training.page.PageAble;
import fa.training.service.EmailService;
import fa.training.service.KhachHangService;
import fa.training.service.TaiKhoanService;

@Controller
@RequestMapping("/taikhoan")
public class TaiKhoanController {

	@Autowired
	private TaiKhoanService taiKhoanService;

	@Autowired
	private KhachHangService khachhangService;

	@Autowired
	EmailService emailService;

	@Autowired
	PasswordEncoder passEncode;

	/**
	 * 
	 * Project: Cinema WebApp  
	 * Method : Danh sách tài khoản
	 */
	@GetMapping("/list")
	public String getAllTaiKhoanWithPageAble(Model model, @RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<TaiKhoan> TaiKhoans = taiKhoanService.findWithPageAble(pageAble);
		model.addAttribute("TaiKhoans", TaiKhoans);
		model.addAttribute("totalPages", taiKhoanService.totalPages(pageAble));
		model.addAttribute("currentPage", page);
		return "/taiKhoan/list";
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : thêm tài khoản
	 */
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("TaiKhoanForm", new TaiKhoan());
		return "/taikhoan/new";
	}

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : lưu tài khoản
	 */
	@PostMapping("/save")
	public String saveNewTaiKhoan(@ModelAttribute("TaiKhoanForm") @Valid TaiKhoan TaiKhoan, BindingResult result) {
		if (result.hasErrors()) {
			return "/taiKhoan/new";
		}
		TaiKhoan.setPassword(passEncode.encode(TaiKhoan.getPassword()));
		taiKhoanService.saveOrUpdate(TaiKhoan);
		return "redirect:/login";
	}

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : xóa tài khoản
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") String TaiKhoanId) {
		taiKhoanService.delete(TaiKhoanId);
		return "redirect:/taiKhoan/list";
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Update tài khoản
	 */
	@RequestMapping("/update/{id}")
	public String update(@PathVariable(name = "id") String TaiKhoanId, Model model) {
		TaiKhoan TaiKhoan = taiKhoanService.findById(TaiKhoanId);
		model.addAttribute("TaiKhoanForm", TaiKhoan);
		return "/taikhoan/new";
	}

	/**
	 * Project:Cinema WebApp
	 * Method: get Đổi Pass
	 */

	@GetMapping("/doimatkhau")
	public String getChangePass(HttpSession session, Model model) {
		if (checkSession(session) == true) {
			return "/taikhoan/resetpass";
		} else {
			model.addAttribute("messageAccount", "Bạn Đã Đăng Xuất, Vui Lòng Đăng Nhập Lại Để Đổi Mật Khẩu!");
			return "/taikhoan/dangnhap";
		}
	}

	/**
	 * Project:Cinema WebApp
	 * Method: Post Đổi Pass
	 */
	@PostMapping("/resetpass")
	public String postChangePass(@ModelAttribute("Resetpass") ResetPassword rs, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (taiKhoanService.finByAccount(rs.getAccount(), rs.getOldpassword()) == true) {
			if (rs.getOldpassword().equals(rs.getPassword())) {
				request.setAttribute("loggedInUser", session.getAttribute("loggedInUser"));
				request.setAttribute("messagePassword", "Mật Khẩu Mới Không Được Trùng Với Mật Khẩu Cũ !");
				return "/taikhoan/resetpass";
			}
			KhachHang a = (KhachHang) session.getAttribute("khachhang");
			if (checkRequest(request) == false) {
				request.setAttribute("messageAccount", "Bạn Đã Đăng Xuất, Vui Lòng Đăng Nhập Lại Để Đổi Mật Khẩu!");
				return "/taikhoan/dangnhap";
			} else {
				taiKhoanService.updatePass(rs.getAccount(), rs.getPassword());
				return "redirect:/khachhang/trangchu";
			}
		} else {
			request.setAttribute("loggedInUser", session.getAttribute("loggedInUser"));
			request.setAttribute("messageAccount", "Thông Tin Tài Khoản Hoặc Mật Khẩu Không Chính Xác!");
			return "/taikhoan/resetpass";

		}
	}

	/**
	 * Project:Cinema WebApp
	 * Method: CHuyển sang Trang Login Từ Home
	 */
	@GetMapping("/dangnhaplai")
	public ModelAndView getDangnhaplai() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/dangnhap");
		return view;
	}

	/**
	 * Project:Cinema WebApp
	 * Method :Form Đăng Ký
	 */
	@GetMapping("/quenmatkhau")
	public String getQuenmatkhau(Model model) {
		return "/taikhoan/quenpass";
	}

	/**
	 * Project:Cinema WebApp
	 * Method :Đi Tới Trang Home (Dựa Vào session đã Đăng Nhập Hay chưa để chuyển hướng home phù hợp)
	 */
	@GetMapping("/home")
	public ModelAndView getHome(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("account") == null) {
			request.setAttribute("doiMK", "");
			request.setAttribute("logIn", "Đăng Nhập");
			request.setAttribute("loggedInUser", "");
		} else {
			KhachHang a = (KhachHang) session.getAttribute("khachhang");
			session.setAttribute("username", "Xin Chào, " + a.getTenKhachHang());
			request.setAttribute("loggedInUser", session.getAttribute("loggedInUser"));
			request.setAttribute("doiMK", "Đổi Mật Khẩu");
			request.setAttribute("loggedOutUser", "Đăng Xuất");
		}
		view.setViewName("/admin/Home");
		return view;
	}

	/**
	 * Project:Cinema WebApp
	 * Method: Form Đăng Ký
	 */
	@GetMapping("/dangky")
	public ModelAndView getDangky() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/taikhoan/dangky");
		return view;
	}

	/**
	 * Project: Cinema WebApp
	 * Method: Form Đăng Ký
	 */
	@PostMapping("/dangky")
	public String dangky(@ModelAttribute("khachhang") @Valid InsertDK dk, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/taikhoan/dangky";
		}
		TaiKhoan tk = new TaiKhoan();
		KhachHang kh = new KhachHang();
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$");
		if (!pattern.matcher(dk.getPassword()).find()) {
			request.setAttribute("passwordError", "Mật khẩu phải từ 8 đến 20 kí tự, có chữ hoa chữ thường, số và kí tự đặc biệt");
			return "/taikhoan/dangky";
		}
		if ((taiKhoanService.searchKH(dk.getEmail()) == null)
				&& (taiKhoanService.finByAccount(dk.getAccount()) == null)) {
			kh.setTenKhachHang(dk.getTenKhachHang());
			kh.setSdt(dk.getSdt());
			kh.setEmail(dk.getEmail());
			kh.setGioiTinh(dk.getGioiTinh());
			kh.setDiaChi(dk.getDiaChi());
			kh.setNgaySinh(dk.getNgaySinh());
			khachhangService.save(kh);
			KhachHang khachhang = taiKhoanService.searchKH(dk.getEmail());
			System.out.println(khachhang.toString());
			tk.setAccount(dk.getAccount());
			tk.setPassword(passEncode.encode(dk.getPassword()));
			tk.setKhachHang(khachhang);
			tk.setRole("ROLE_USER");
			tk.setTrangThai("active");
			System.out.println(tk.toString());
			taiKhoanService.save(tk);
			redirectAttributes.addFlashAttribute("accountSuccess", "Đăng kí thành công!! vui lòng đăng nhập để tiếp tục");
			return "redirect:/dangnhap";
		} else {
			if ((taiKhoanService.searchKH(dk.getEmail()) != null)) {
				request.setAttribute("messageEmail", "Email Này Đã Được Đăng Ký!");
			} else {
				request.setAttribute("messageEmail", "");
			}

			if (taiKhoanService.finByAccount(dk.getAccount()) != null) {
				request.setAttribute("messageAccount", "User Đã Tồn Tại Trong Hệ Thống");
			} else {
				request.setAttribute("messageAccount", "");
			}
			request.setAttribute("messageAccount", "Đã xảy ra lỗi");
			return "/taikhoan/dangky";
		}
	}

	/**
	 * Project:Cinema WebApp
	 * Method :Form Quên Mật Khẩu
	 */
	@PostMapping("/otp")
	public String quenPass(@ModelAttribute("emailtk") String email, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession sessionOTP = request.getSession();
		if (taiKhoanService.searchKH(email) == null) {
			request.setAttribute("messageEmail", "Email chưa đăng ký cho tài khoản nào!");
			return "/taikhoan/quenpass";
		} else {
			KhachHang kh = taiKhoanService.searchKH(email);
			System.out.println(email);
			System.out.println(kh.getEmail());
			TaiKhoan tk = kh.getTaiKhoan();
			System.out.println(tk.getAccount());
			sessionOTP.setAttribute("taikhoan", tk);
			Random random = new Random();
			int otp = 100000 + random.nextInt(999999 - 100000 + 1);
			String OTP = String.valueOf(otp);
			String subject = "OTP From T2Cinema";
			String message = "OTP is " + otp;
			String to = email;
			boolean flag = this.emailService.sendEmail(subject, message, to);
			if (flag) {
				sessionOTP.setAttribute("myOtp", OTP);
				sessionOTP.setMaxInactiveInterval(300);
				return "/taikhoan/otp";
			} else {
				request.setAttribute("messageEmailcorrect", "Hệ Thống Đang Lỗi, Vui lòng Chờ Ít Phút !");
				return "/taikhoan/quenpass";
			}

		}
	}

	/**
	 * 
	 * Project: Cinema WebApp  
	 * Method : đổi mật khẩu
	 */
	@PostMapping("/doimatkhau")
	public String checkotp(@ModelAttribute("otpemail") String otp, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession sessionOTP = request.getSession();
		ModelAndView view = new ModelAndView();
		if (sessionOTP.getAttribute("myOtp") == null) {
			request.setAttribute("messageOtp", "OTP đã hết hạn! ");
			return "/taikhoan/quenpass";
		}
		if (sessionOTP.getAttribute("myOtp").equals(otp) == true) {
			TaiKhoan tk = (TaiKhoan) sessionOTP.getAttribute("taikhoan");
			request.setAttribute("loggedInUser", tk.getAccount());
			return "/taikhoan/misspass";
		} else {
			request.setAttribute("messageOtp", "OTP không chính xác ! ");
			return "/taikhoan/otp";
		}
	}

	@GetMapping("/misspass")
	public String changePass() {
		return "/taikhoan/misspass";
	}

	@PostMapping("/misspass")
	public String postMissPass(@ModelAttribute("misspass") ResetPassword rs, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		if (taiKhoanService.regexPass(rs.getPassword()) == true) {
			taiKhoanService.updatePass(rs.getAccount(), rs.getPassword());
			request.setAttribute("messagePassword", "Đổi Mật Khẩu Thành Công, Vui Lòng Đăng Nhập Lại !");
			return "/taikhoan/dangnhap";
		} else {
			request.setAttribute("loggedInUser", session.getAttribute("username1"));
			request.setAttribute("messagePassword",
					"Mật Khẩu Phải Bao Gồm 1 Kí Tự Viết Hoa, Viết Thường Và 1 Ký Tự Đặc Biệt !");
			return "/taikhoan/misspass";
		}
	}

	/**
	 * Project:Cinema WebApp
	 * 
	 * @author TuNC8 Check Session
	 */
	public boolean checkSession(HttpSession session) {
		if (session.getAttribute("account") == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Project:Cinema WebApp
	 * 
	 * @author TuNC8 Check Session
	 */
	public boolean checkRequest(HttpServletRequest request) {
		if (request.getSession().getAttribute("account") == null) {
			return false;
		} else {
			return true;
		}
	}
}
