package fa.training.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.entities.Phim;
import fa.training.entities.PhongChieu;
import fa.training.entities.SuatChieu;
import fa.training.entities.Ve;
import fa.training.page.PageAble;
import fa.training.service.PhimService;
import fa.training.service.PhongChieuService;
import fa.training.service.SuatChieuService;
import fa.training.service.VeService;

/**
 * 
 * Project: Cinema WebApp
 * Controller Suat Chieu
 * 
 */
@Controller
@RequestMapping("/admin/suatchieu")
public class SuatChieuController {
	
	@Autowired
	private SuatChieuService suatChieuService;
	
	@Autowired
	private PhimService phimService;
	
	@Autowired
	private PhongChieuService phongchieuService;
	
	@Autowired
	private VeService veService;
	
	@GetMapping("/list")
	public String getAllWithPageAble(Model model, @RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<SuatChieu> suatChieus = suatChieuService.findWithPageAble(pageAble);
		model.addAttribute("suatchieus", suatChieus);
		model.addAttribute("totalPages", suatChieuService.totalPages(pageAble));
		model.addAttribute("currentPage", page);
		return "/suatchieu/list";
	}
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("suatChieuForm", new SuatChieu());
		return "/suatchieu/new";
	}
	
	
	@PostMapping("/save")
	public String addNewRecord(Model model, @ModelAttribute("suatChieuForm") @Valid SuatChieu suatChieu, BindingResult result) {
		if (result.hasErrors()) {
			return "/suatchieu/new";
		}
		suatChieuService.saveOrUpdate(suatChieu);	
		PhongChieu phongChieu = phongchieuService.findById(suatChieu.getPhongChieu().getMaPhongChieu());
			int room = phongChieu.getSoLuongGhe();
			int hang = 0;
			int cot = 0;
			switch (room) {
			case 100:
				hang = 10;
				cot = 10;
				break;
			case 64: 
				hang = 8;
				cot = 8;
				break;
			case 50: 
				hang = 5;
				cot = 10;
			case 30:
				hang = 5;
				cot = 6;
			}
			char x = 'A';
			for (int i = 0; i<hang ; i++ ) {
				char y = (char)(x + i);
				for (int j = 0 ; j<cot;j++) {
				int k = j+1;
				String maGhe = "" + y + k;
				Ve ve = new Ve(suatChieu, null, null, "1", maGhe, null);
				veService.saveOrUpdate(ve);
				}
			}	
			
		return "redirect:/admin/suatchieu/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		List<Ve> ves = veService.getListBySuatChieu(id);
		for (int i = 0; i < ves.size(); i++) {
			veService.delete(ves.get(i).getId());
		}
		suatChieuService.delete(id);
		return "redirect:/admin/suatchieu/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") int id, @RequestParam("phim") String maPhim, @RequestParam("phongchieu") String maPhongChieu, Model model) {
		SuatChieu suatChieu = suatChieuService.findById(id);
		model.addAttribute("phim", maPhim);
		model.addAttribute("phongchieu", maPhongChieu);
		model.addAttribute("suatChieuForm", suatChieu);
		return "/suatchieu/update";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("searchKey") String searchKey, Model model) {
		List<SuatChieu> suatChieus = suatChieuService.search(searchKey);
		model.addAttribute("suatchieus", suatChieus);
		return "/suatchieu/search";
	}
	
	@ModelAttribute("phims")
	public List<Phim> initPhim(){
		return phimService.findAll();
	}
	
	@ModelAttribute("phongchieus")
	public List<PhongChieu> initPhongChieu(){
		return phongchieuService.findAll();
	}
}
