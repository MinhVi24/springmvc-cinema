package fa.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entities.DichVu;
import fa.training.page.PageAble;
import fa.training.repository.DichVuRepository;

@Service
@Transactional
public class DichVuService {

	@Autowired
	DichVuRepository dichVuRepository;

	public List<DichVu> findAll() {
		return dichVuRepository.findAll();
	}

	public void save(DichVu x) {
		dichVuRepository.save(x);
	}

	public void saveOrUpdate(DichVu x) {
		dichVuRepository.saveOrUpdate(x);
	}

	public DichVu findById(String Id) {
		return dichVuRepository.findById(Id);
	}

	public void delete(String maDichVu) {
		DichVu dichVu = findById(maDichVu);
		if (dichVu != null) {
			dichVuRepository.delete(dichVu);
		}
	}

	public List<DichVu> search(String searchKey) {

		return dichVuRepository.search(searchKey);
	}

	public List<DichVu> findWithPageAble(PageAble pageAble) {
		return dichVuRepository.findWithPageAble(pageAble);
	}

	public int totalPages(PageAble pageAble) {
		long totalRecord = dichVuRepository.count();
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

	public DichVu maDichVu(String maDichVu) {
		return dichVuRepository.maDichVu(maDichVu);
	}

	public boolean checkSuDungDichVu(String maDichVu) {
		return dichVuRepository.checkSuDungDichVu(maDichVu);
	}
}
