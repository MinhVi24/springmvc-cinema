/**
 * 
 * Project: Cinema WebApp
 * Team: 2
 * Author : BaoHC1
 * Controller Phong Chieu
 * 
 */
package fa.training.controller;

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

import fa.training.entities.PhongChieu;
import fa.training.page.PageAble;
import fa.training.service.PhongChieuService;

@Controller
@RequestMapping("/admin/phongchieu")
public class PhongChieuController {
	
	@Autowired
	private PhongChieuService PhongchieuService;
	
	@GetMapping("/list")
	public String getAllWithPageAble(Model model, @RequestParam(defaultValue = "1") Integer page) {
		PageAble pageAble = new PageAble(page);
		List<PhongChieu> phongchieus = PhongchieuService.findWithPageAble(pageAble);
		model.addAttribute("phongchieus", phongchieus);
		model.addAttribute("totalPages", PhongchieuService.totalPages(pageAble));
		model.addAttribute("currentPage", page);
		return "/phongchieu/list";
	}
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("PhongchieuForm", new PhongChieu());
		return "/phongchieu/new";
	}
	
	@PostMapping("/save")
	public String addNewRecord(Model model, @ModelAttribute("PhongchieuForm") @Valid PhongChieu phongchieu, BindingResult result) {
		if (result.hasErrors()) {
			System.out.print("Hello");
			return "/phongchieu/new";
		}
		
		if (PhongchieuService.existInDB(phongchieu.getMaPhongChieu())) {
			model.addAttribute("error", "Mã phòng chiếu đã tồn tại!");
			return "/phongchieu/new";
		}
		
		PhongchieuService.saveOrUpdate(phongchieu);
		return "redirect:/phongchieu/list";
	}
	
	@PostMapping("/update")
	public String updateRecord(Model model, @ModelAttribute("PhongchieuForm") @Valid PhongChieu phongchieu, BindingResult result) {
		if (result.hasErrors()) {
			System.out.print("Hello");
			return "/phongchieu/update";
		}
		
		
		PhongchieuService.saveOrUpdate(phongchieu);
		return "redirect:/admin/phongchieu/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		PhongchieuService.delete(id);
		return "redirect:/phongchieu/list";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") String id, Model model) {
		PhongChieu phongchieu = PhongchieuService.findById(id);
		model.addAttribute("PhongchieuForm", phongchieu);
		return "/phongchieu/update";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("searchKey") String searchKey, Model model) {
		List<PhongChieu> phongchieus = PhongchieuService.search(searchKey);
		model.addAttribute("phongchieus", phongchieus);
		return "/phongchieu/search";
	}
}
