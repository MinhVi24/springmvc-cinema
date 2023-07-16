package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.entities.Phim;
import fa.training.service.KhuyenMaiService;
import fa.training.service.PhimService;


@Controller
@RequestMapping(value = "/trangchu")
public class HomePageController {

	@Autowired
	PhimService phimService;
	
	@Autowired
	KhuyenMaiService khuyenMaiService;
	/**
	 * Trang chủ
	 * Proeject: Cinema WebApp
	 */
	@GetMapping()
	public String trangChu(Model model) {
		List<Phim> listPhim = phimService.findTop3();
		List<Phim> listPhim1 = phimService.findAllSauTop3();
		List<Phim> listPhim3 = phimService.findPhimSapChieu();
		List<Phim> listPhim4 = phimService.findHoatHinh();
		Phim phim = phimService.findById("PH00061");
		model.addAttribute("listPhim", listPhim);
		model.addAttribute("listPhim1", listPhim1);
		model.addAttribute("listPhim3", listPhim3);
		model.addAttribute("listPhim4", listPhim4);
		model.addAttribute("phim", phim);
		model.addAttribute("top3KM",khuyenMaiService.findAll());
		return "/trangchu";
	}
}
