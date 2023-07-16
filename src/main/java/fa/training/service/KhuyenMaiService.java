package fa.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entities.KhuyenMai;
import fa.training.page.PageAble;
import fa.training.repository.KhuyenMaiRepository;

@Service
@Transactional
public class KhuyenMaiService {

	@Autowired
	KhuyenMaiRepository khuyenMaiRepository;

	public List<KhuyenMai> findAll() {
		return khuyenMaiRepository.findAll();
	}

	public void save(KhuyenMai x) {
		khuyenMaiRepository.save(x);
	}

	public boolean checkSuDungKM(String maKhuyenMai) {
		return khuyenMaiRepository.checkSuDungKM(maKhuyenMai);
	}

	public void saveOrUpdate(KhuyenMai x) {
		khuyenMaiRepository.saveOrUpdate(x);
	}

	public KhuyenMai findById(String Id) {
		return khuyenMaiRepository.findById(Id);
	}

	public void delete(String maKhuyenMai) {
		KhuyenMai khuyenMai = findById(maKhuyenMai);
		if (khuyenMai != null) {
			khuyenMaiRepository.delete(khuyenMai);
		}
	}

	public List<KhuyenMai> search(String searchKey) {

		return khuyenMaiRepository.search(searchKey);
	}

	public List<KhuyenMai> findWithPageAble(PageAble pageAble) {
		return khuyenMaiRepository.findWithPageAble(pageAble);
	}

	public int totalPages(PageAble pageAble) {
		long totalRecord = khuyenMaiRepository.count();
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

	public KhuyenMai maKhuyenMai(String maKhuyenMai) {
		return khuyenMaiRepository.maKhuyenMai(maKhuyenMai);
	}

	public List<KhuyenMai> findTop3KhuyenMai() {
		return khuyenMaiRepository.findTop3KhuyenMai();
	}
}
