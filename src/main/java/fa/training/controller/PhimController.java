package fa.training.controller;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fa.training.entities.Phim;
import fa.training.model.HienThiPhim;
import fa.training.page.PageAble;
import fa.training.service.PhimService;

@Controller
@RequestMapping(value = "/admin/phim")
public class PhimController {
	
	@Autowired
	PhimService phimService;
	/**
	 * Project: Cinema WebApp 
	 * Method:Danh sách phim đang chiếu
	 */
	@RequestMapping(value = "/listPhimDangChieu")
	public String listPhimDangChieu(Model model, @RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<Phim> listPhim = phimService.findPhimDangChieu(pageAble);
		List<HienThiPhim> listThongTinPhim = new ArrayList<HienThiPhim>();
		for (Phim phim : listPhim) {
			HienThiPhim hienThiPhim = new HienThiPhim();
			hienThiPhim.setMaPhim(phim.getMaPhim());
			hienThiPhim.setTenPhim(phim.getTenPhim());
			hienThiPhim.setMoTaPhim(phim.getMoTaPhim());
			hienThiPhim.setDaoDien(phim.getDaoDien());
			hienThiPhim.setThoiLuong(phim.getThoiLuong());
			String ngayKhoiChieu = phim.getNgayKhoiChieu().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			hienThiPhim.setNgayKhoiChieu(ngayKhoiChieu);
			String ngayKetThuc = phim.getNgayKetThuc().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			hienThiPhim.setNgayKetThuc(ngayKetThuc);
			hienThiPhim.setPoster(phim.getPoster());
			listThongTinPhim.add(hienThiPhim);
		}
		model.addAttribute("totalPages", phimService.totalPagesPhimDangChieu(pageAble));
		model.addAttribute("currentPage", page);
		model.addAttribute("listThongTinPhim", listThongTinPhim);
		return "/phim/listPhimDangChieu";
	}

/**
 * Project: Cinema WebApp  
 * Method :Danh sách phim sắp chiếu
 */
	@RequestMapping(value = "/listPhimSapChieu")
	public String listPhimSapChieu(Model model, @RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<Phim> listPhim = phimService.findPhimSapChieu(pageAble);
		List<HienThiPhim> listThongTinPhim = new ArrayList<HienThiPhim>();
		for (Phim phim : listPhim) {
			HienThiPhim hienThiPhim = new HienThiPhim();
			hienThiPhim.setMaPhim(phim.getMaPhim());
			hienThiPhim.setTenPhim(phim.getTenPhim());
			hienThiPhim.setMoTaPhim(phim.getMoTaPhim());
			hienThiPhim.setDaoDien(phim.getDaoDien());
			hienThiPhim.setThoiLuong(phim.getThoiLuong());
			String ngayKhoiChieu = phim.getNgayKhoiChieu().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			hienThiPhim.setNgayKhoiChieu(ngayKhoiChieu);
			String ngayKetThuc = phim.getNgayKetThuc().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			hienThiPhim.setNgayKetThuc(ngayKetThuc);
			listThongTinPhim.add(hienThiPhim);
		}
		model.addAttribute("totalPages", phimService.totalPagesPhimSapChieu(pageAble));
		model.addAttribute("currentPage", page);
		model.addAttribute("listThongTinPhim", listThongTinPhim);
		return "/phim/listPhimSapChieu";
	}
 /**
  * Project: Cinema WebApp
  * Method :Tạo mới một bộ phim
  */
	@RequestMapping(value = "/them")
	public String themPhim(Model model) {
		model.addAttribute("themphim", new Phim());
		return "/phim/them";
	}

	/**
	 * Project: Cinema WebApp
	 * Method :Thêm thành công một bộ phim
	 */
	@RequestMapping(value = "/themphim")
	public String savePhim(@Valid @ModelAttribute(name = "themphim") Phim phim, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, @RequestParam(value = "shutdown") MultipartFile file,
			HttpServletRequest request) {
		Phim phim1 = phimService.findById(phim.getMaPhim());
		if (phim.getThoiLuong() == null) {
			model.addAttribute("msg2", "Bạn cần phải nhập thông tin thời lượng đúng định dạng HH:mm");
			return "/phim/them";
		}
		if (phim1 != null) {
			model.addAttribute("msg3", "Mã phim đã tồn tại trong hệ thống!");
			return "/phim/them";
		}
		if (phim.getNgayKhoiChieu() == null) {
			model.addAttribute("msg6", "Bạn cần phải nhập thông tin ngày khởi chiếu!");
			return "/phim/them";
		}
		if (phim.getNgayKetThuc() == null) {
			model.addAttribute("msg", "Bạn cần phải nhập thông tin ngày kết thúc!");
			return "/phim/them";
		}
		if (phim.getNgayKhoiChieu().isEqual(LocalDate.now()) == true
				|| phim.getNgayKhoiChieu().isAfter(LocalDate.now()) == false) {
			model.addAttribute("msg4", "Ngày khởi chiếu phải lớn hơn ngày hiện tại!");
			return "/phim/them";
		}
		if (phim.getNgayKhoiChieu().plusDays(7).isBefore(phim.getNgayKetThuc()) == false) {
			model.addAttribute("msg1", "Ngày kết thúc phải lớn hơn ngày khởi chiếu ít nhất 7 ngày!");
			return "/phim/them";
		}
		if (result.hasErrors()) {
			return "/phim/them";
		}
		if (file != null) {
			try {
				InputStream inputStream = file.getInputStream();
				if (inputStream == null)
					System.out.println("File inputstream is null");
				String path = request.getSession().getServletContext().getRealPath("/") + "resources/image/";
				FileUtils.forceMkdir(new File(path));
				File upload = new File(path + file.getOriginalFilename());
				System.out.println(path);
				file.transferTo(upload);
				String imagePath = request.getContextPath() + "/resources/image/" + file.getOriginalFilename();
				phim.setPoster(imagePath);
				String path1 = "C:\\Users\\admin\\OneDrive\\Desktop\\Mock_Project\\CinemaWebApp\\CinemaWebApp\\src\\main\\webapp\\resources\\image\\";
				FileUtils.forceMkdir(new File(path1));
				File upload1 = new File(path1 + file.getOriginalFilename());
				file.transferTo(upload1);
				IOUtils.closeQuietly(inputStream);
			} catch (Exception ex) {
				System.out.println("Upload hình ảnh lỗi!");
			}
		}
		if (phim.getPoster() == null) {
			model.addAttribute("msg5", "Bạn phải tải hình ảnh poster của bộ phim lên!");
			return "/phim/them";
		}
		phimService.save(phim);
		redirectAttributes.addFlashAttribute("msgSave", "Thêm phim thành công!");
		return "redirect:/admin/phim/listPhimSapChieu";
	}

	/**
	 * Project: Cinema WebApp 
	 * ViTM
	 * Method :Cập nhật một bộ phim đang chiếu
	 */
	@RequestMapping(value = "/phimupdatedangchieu/{id}")
	public String updatePhimDangChieu(@PathVariable String id, Model model) {
		Phim phim = phimService.findById(id);
		model.addAttribute("themphim", phim);
		return "/phim/updatePhimDangChieu";
	}

	/**
	 * Project: Cinema WebApp
	 * ViTM
	 * Method :Cập nhật một bộ phim sắp chiếu
	 */
	@RequestMapping(value = "/phimupdatesapchieu/{id}")
	public String updatePhimSapChieu(@PathVariable String id, Model model) {
		Phim phim = phimService.findById(id);
		model.addAttribute("themphim", phim);
		return "/phim/updatePhimSapChieu";
	}

	/**
	 * Cập nhật thành công một bộ phim đang chiếu
	 * Project: Cinema WebApp
	 * Team: 2
	 * Dang Ngoc Sinh
	 * 30/08/1998
	 * @param phim
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @param ketqua
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatephimdangchieu")
	public String doUpdatePhimDangChieu(@Valid @ModelAttribute(name = "themphim") Phim phim, BindingResult result,
			Model model, RedirectAttributes redirectAttributes,
			@RequestParam(value = "luachon", required = false) String ketqua,
			@RequestParam(value = "shutdown") MultipartFile file, HttpServletRequest request) {
		Phim phim1 = phimService.findById(phim.getMaPhim());
		if (phim.getNgayKetThuc() == null) {
			model.addAttribute("msg1", "Bạn cần phải nhập thông tin ngày kết thúc!");
			return "/phim/updatePhimDangChieu";
		}
		if (phim.getNgayKetThuc().isBefore(LocalDate.now()) == true) {
			model.addAttribute("msg", "Ngày kết thúc phải lớn hơn hoặc bằng ngày hiện tại!");
			return "/phim/updatePhimDangChieu";
		}
		if (result.hasErrors()) {
			return "/phim/updatePhimDangChieu";
		}
		if (ketqua.equals("")) {
			model.addAttribute("msg5", "Vui lòng chọn mục này!");
			return "/phim/updatePhimDangChieu";
		} else if (ketqua.equals("co")) {
			if (file != null) {
				try {
					InputStream inputStream = file.getInputStream();
					if (inputStream == null)
						System.out.println("File inputstream is null");
					String path = request.getSession().getServletContext().getRealPath("/") + "resources/image/";
					FileUtils.forceMkdir(new File(path));
					File upload = new File(path + file.getOriginalFilename());
					System.out.println(path);
					file.transferTo(upload);
					String imagePath = request.getContextPath() + "/resources/image/" + file.getOriginalFilename();
					phim.setPoster(imagePath);
					phimService.update(phim);
					String path1 = "C:\\Users\\admin\\OneDrive\\Desktop\\Mock_Project\\CinemaWebApp\\CinemaWebApp\\src\\main\\webapp\\resources\\image\\";
					FileUtils.forceMkdir(new File(path1));
					File upload1 = new File(path1 + file.getOriginalFilename());
					file.transferTo(upload1);
					IOUtils.closeQuietly(inputStream);
				} catch (Exception ex) {
					System.out.println("Upload hình ảnh lỗi!");
				}
			}
			if (phim.getPoster() == null) {
				model.addAttribute("msg6", "Bạn phải tải hình ảnh poster của bộ phim lên!");
				return "/phim/updatePhimDangChieu";
			}
		} else if (ketqua.equals("khong")) {
			phim.setPoster(phim1.getPoster());
			phimService.update(phim);
		}
		redirectAttributes.addFlashAttribute("msgUpdate", "Cập nhật phim thành công!");
		return "redirect:/admin/phim/listPhimDangChieu";
	}

	/**
	 * Cập nhật thành công một bộ phim sắp chiếu
	 * Project: Cinema WebApp
	 * Team: 2
	 * Dang Ngoc Sinh
	 * 30/08/1998
	 * @param phim
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @param ketqua
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatephimsapchieu")
	public String doUpdatePhimSapChieu(@Valid @ModelAttribute(name = "themphim") Phim phim, BindingResult result,
			Model model, RedirectAttributes redirectAttributes,
			@RequestParam(value = "luachon", required = false) String ketqua,
			@RequestParam(value = "shutdown") MultipartFile file, HttpServletRequest request) {
		Phim phim1 = phimService.findById(phim.getMaPhim());
		if (phim.getThoiLuong() == null) {
			model.addAttribute("msg2", "Bạn cần phải nhập thông tin thời lượng đúng định dạng HH:mm");
			return "/phim/updatePhimSapChieu";
		}
		if (phim.getNgayKhoiChieu() == null) {
			model.addAttribute("msg7", "Bạn cần phải nhập thông tin ngày khởi chiếu!");
			return "/phim/updatePhimSapChieu";
		}
		if (phim.getNgayKetThuc() == null) {
			model.addAttribute("msg", "Bạn cần phải nhập thông tin ngày kết thúc!");
			return "/phim/updatePhimSapChieu";
		}
		if (phim.getNgayKhoiChieu().isEqual(LocalDate.now()) == true
				|| phim.getNgayKhoiChieu().isAfter(LocalDate.now()) == false) {
			model.addAttribute("msg4", "Ngày khởi chiếu phải lớn hơn ngày hiện tại!");
			return "/phim/updatePhimSapChieu";
		}
		if (phim.getNgayKhoiChieu().plusDays(7).isBefore(phim.getNgayKetThuc()) == false) {
			model.addAttribute("msg1", "Ngày kết thúc phải lớn hơn ngày khởi chiếu ít nhất 7 ngày!");
			return "/phim/updatePhimSapChieu";
		}
		if (result.hasErrors()) {
			return "/phim/updatePhimSapChieu";
		}
		if (ketqua.equals("")) {
			model.addAttribute("msg5", "Vui lòng chọn mục này!");
			return "/phim/updatePhimSapChieu";
		} else if (ketqua.equals("co")) {
			if (file != null) {
				try {
					InputStream inputStream = file.getInputStream();
					if (inputStream == null)
						System.out.println("File inputstream is null");
					String path = request.getSession().getServletContext().getRealPath("/") + "resources/image/";
					FileUtils.forceMkdir(new File(path));
					File upload = new File(path + file.getOriginalFilename());
					System.out.println(path);
					file.transferTo(upload);
					String imagePath = request.getContextPath() + "/resources/image/" + file.getOriginalFilename();
					phim.setPoster(imagePath);
					phimService.update(phim);
					String path1 = "C:\\Users\\admin\\OneDrive\\Desktop\\Mock_Project\\CinemaWebApp\\CinemaWebApp\\src\\main\\webapp\\resources\\image\\";
					FileUtils.forceMkdir(new File(path1));
					File upload1 = new File(path1 + file.getOriginalFilename());
					file.transferTo(upload1);
					IOUtils.closeQuietly(inputStream);
				} catch (Exception ex) {
					System.out.println("Upload hình ảnh lỗi!");
				}
			}
			if (phim.getPoster() == null) {
				model.addAttribute("msg6", "Bạn phải tải hình ảnh poster của bộ phim lên!");
				return "/phim/updatePhimSapChieu";
			}
		} else if (ketqua.equals("khong")) {
			phim.setPoster(phim1.getPoster());
			phimService.update(phim);
		}
		redirectAttributes.addFlashAttribute("msgUpdate", "Cập nhật phim thành công!");
		return "redirect:/admin/phim/listPhimSapChieu";
	}
	/**
	 * Xoá một bộ phim
	 * Project: Cinema WebApp
	 * Team: 2
	 * Dang Ngoc Sinh
	 * 30/08/1998
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletephimsapchieu/{id}")
	public String doDeletePhimSapChieu(@PathVariable String id,RedirectAttributes redirectAttributes) {
		phimService.delete(id);
		redirectAttributes.addFlashAttribute("msgDelete", "Bạn đã xóa thành công một bộ phim!");
		return "redirect:/admin/phim/listPhimSapChieu";
	}
	
	/**
	 * Tìm kiếm theo tên phim
	 * Project: Cinema WebApp
	 * Team: 2
	 * Dang Ngoc Sinh
	 * 30/08/1998
	 * @param searchKey
	 * @param model
	 * @return
	 */
	@GetMapping("/searchPhimDangChieu")
	public String searchPhimDangChieu(@RequestParam(required = false,name ="searchKey") String searchKey, Model model,RedirectAttributes redirectAttributes, @RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<Phim> listPhim = phimService.searchTenPhimDangChieu(searchKey,pageAble);
		model.addAttribute("totalPages", phimService.totalPagesPhimDangChieuTheoTen(searchKey,pageAble));
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("currentPage", page);
		List<HienThiPhim> listThongTinPhim = new ArrayList<HienThiPhim>();
		for (Phim phim : listPhim) {
			HienThiPhim hienThiPhim = new HienThiPhim();
			hienThiPhim.setMaPhim(phim.getMaPhim());
			hienThiPhim.setTenPhim(phim.getTenPhim());
			hienThiPhim.setMoTaPhim(phim.getMoTaPhim());
			hienThiPhim.setDaoDien(phim.getDaoDien());
			hienThiPhim.setThoiLuong(phim.getThoiLuong());
			String ngayKhoiChieu = phim.getNgayKhoiChieu().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			hienThiPhim.setNgayKhoiChieu(ngayKhoiChieu);
			String ngayKetThuc = phim.getNgayKetThuc().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			hienThiPhim.setNgayKetThuc(ngayKetThuc);
			listThongTinPhim.add(hienThiPhim);
		}
		if(searchKey.equals("")) {
			redirectAttributes.addFlashAttribute("msg3", "Bạn cần nhập thông tin tìm kiếm!");
			return "redirect:/admin/phim/listPhimDangChieu";
		}
		if(listThongTinPhim.size()==0) {
			model.addAttribute("msg1", "Không có kết quả tìm kiếm phù hợp!");
			return "/phim/searchPhimDangChieu";
		}
		model.addAttribute("listThongTinPhim", listThongTinPhim);
		return "/phim/searchPhimDangChieu";
	}
	/**
	 * Tìm kiếm theo tên phim
	 * Project: Cinema WebApp
	 * Team: 2
	 * Dang Ngoc Sinh
	 * 30/08/1998
	 * @param searchKey
	 * @param model
	 * @return
	 */
	@GetMapping("/searchPhimSapChieu")
	public String searchPhimSapChieu(@RequestParam(required = false,name ="searchKey") String searchKey, Model model,RedirectAttributes redirectAttributes,@RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<Phim> listPhim = phimService.searchTenPhimSapChieu(searchKey,pageAble);
		model.addAttribute("totalPages", phimService.totalPagesPhimSapChieuTheoTen(searchKey,pageAble));
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("currentPage", page);
		List<HienThiPhim> listThongTinPhim = new ArrayList<HienThiPhim>();
		for (Phim phim : listPhim) {
			HienThiPhim hienThiPhim = new HienThiPhim();
			hienThiPhim.setMaPhim(phim.getMaPhim());
			hienThiPhim.setTenPhim(phim.getTenPhim());
			hienThiPhim.setMoTaPhim(phim.getMoTaPhim());
			hienThiPhim.setDaoDien(phim.getDaoDien());
			hienThiPhim.setThoiLuong(phim.getThoiLuong());
			String ngayKhoiChieu = phim.getNgayKhoiChieu().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			hienThiPhim.setNgayKhoiChieu(ngayKhoiChieu);
			String ngayKetThuc = phim.getNgayKetThuc().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			hienThiPhim.setNgayKetThuc(ngayKetThuc);
			listThongTinPhim.add(hienThiPhim);
		}
		if(searchKey.equals("")) {
			redirectAttributes.addFlashAttribute("msg3", "Bạn cần nhập thông tin tìm kiếm!");
			return "redirect:/admin/phim/listPhimSapChieu";
		}
		if(listThongTinPhim.size()==0) {
			model.addAttribute("msg1", "Không có kết quả tìm kiếm phù hợp!");
			return "/phim/searchPhimSapChieu";
		}
		model.addAttribute("listThongTinPhim", listThongTinPhim);
		return "/phim/searchPhimSapChieu";
	}
	
	/**
	 * Project: Cinema WebApp
	 * Team: 2
	 * Author : ThanhDD
	 * Function/Class/JSP : Chuyển hướng url tới trang dịch vụ
	 */
	@GetMapping("/dichvu")
	public String getDichVu() {
		return "admin/dichvu";
	}
}
