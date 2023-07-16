package fa.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.entities.PhongChieu;
import fa.training.page.PageAble;
import fa.training.repository.PhongChieuRepository;

@Service
@Transactional
public class PhongChieuService {
	
	@Autowired
	private PhongChieuRepository PhongchieuRepository;
	
	public List<PhongChieu> findAll() {
		return PhongchieuRepository.findAll();
	}
	
	public void saveOrUpdate(PhongChieu PhongChieu) {
		PhongchieuRepository.saveOrUpdate(PhongChieu);
	}
	
	public void delete(String id) {
		PhongChieu PhongChieu = PhongchieuRepository.findById(id);
		if (PhongChieu != null) {
			PhongchieuRepository.delete(PhongChieu);
		}
	}
	
	public PhongChieu findById(String id) {
		return PhongchieuRepository.findById(id);
	}
	
	public List<PhongChieu> findWithPageAble(PageAble pageAble) {
		return PhongchieuRepository.findWithPageAble(pageAble);
	}
	
	public int totalPages(PageAble pageAble) {
		return (int) Math.ceil((double) PhongchieuRepository.count() / pageAble.getSize());
	}
	
	public List<PhongChieu> search(String searchKey) {
		return PhongchieuRepository.search(searchKey);
	}
	
	public boolean existInDB(String s) {
		return PhongchieuRepository.existInDB(s);
	}
}
