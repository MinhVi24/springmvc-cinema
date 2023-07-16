package fa.training.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.entities.SuatChieu;
import fa.training.page.PageAble;
import fa.training.repository.SuatChieuRepository;


@Service
@Transactional
public class SuatChieuService {
	
	@Autowired
	private SuatChieuRepository suatChieuRepository;
	
	public List<SuatChieu> findAll() {
		return suatChieuRepository.findAll();
	}
	
	public void saveOrUpdate(SuatChieu SuatChieu) {
		suatChieuRepository.saveOrUpdate(SuatChieu);
	}
	
	public void delete(int id) {
		SuatChieu SuatChieu = suatChieuRepository.findById(id);
		if (SuatChieu != null) {
			suatChieuRepository.delete(SuatChieu);
		}
	}
	
	public SuatChieu findById(int id) {
		return suatChieuRepository.findById(id);
	}
	
	public List<SuatChieu> findWithPageAble(PageAble pageAble) {
		return suatChieuRepository.findWithPageAble(pageAble);
	}
	
	public int totalPages(PageAble pageAble) {
		return (int) Math.ceil((double) suatChieuRepository.count() / pageAble.getSize());
	}
	
	public List<SuatChieu> search(String searchKey) {
		return suatChieuRepository.search(searchKey);
	}
	
	public boolean existInDB(String s) {
		return suatChieuRepository.existInDB(s);
	}
	
	public List<SuatChieu> searchByDate(String maPhim,LocalDate ngayChieu) {
		return suatChieuRepository.searchByDate(maPhim, ngayChieu);
	}
	
}
