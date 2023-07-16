package fa.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.entities.KhachHang;
import fa.training.page.PageAble;
import fa.training.repository.KhachHangRepository;

@Service
@Transactional
public class KhachHangService {
	@Autowired

	KhachHangRepository khachHangRepository;

	public void save(KhachHang khachHang) {
		khachHangRepository.save(khachHang);
	}

	public void saveOrUpdate(KhachHang khachHang) {
		khachHangRepository.saveOrUpdate(khachHang);
	}

	public List<KhachHang> find() {
		List<KhachHang> listKhachHang = khachHangRepository.find();
		return listKhachHang;
	}

	public long getCountByGender(String gioiTinh) {
		return khachHangRepository.getCountByGender(gioiTinh);
	}

	public long getCountByAge(int min, int max) {
		return khachHangRepository.getCountByAge(min, max);
	}

	public KhachHang findById(int maKhachHang) {
		return khachHangRepository.findById(maKhachHang);
	}

	public List<KhachHang> findByIdKhachHang(int maKhachHang) {
		return khachHangRepository.findByIdKhachHang(maKhachHang);
	}

	public KhachHang tenKhachHang(String tenKhachHang) {
		return khachHangRepository.tenKhachHang(tenKhachHang);
	}

	public void delete(int maKhachHang) {
		KhachHang dichVu = findById(maKhachHang);
		if (dichVu != null) {
			khachHangRepository.delete(dichVu);
		}
	}

	public List<KhachHang> findWithPageAble(PageAble pageAble) {
		return khachHangRepository.findWithPageAble(pageAble);
	}

	public List<KhachHang> search(String maKhachHang, PageAble pageAble) {
		return khachHangRepository.search(maKhachHang, pageAble);
	}

	public int totalPages(PageAble pageAble) {
		long totalRecord = khachHangRepository.count();
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

	public int totalPagesSearch(String tenKhachHang, PageAble pageAble) {
		final long totalRecord = this.khachHangRepository.countsearch(tenKhachHang);
		return (int) Math.ceil(totalRecord / (double) pageAble.getSize());
	}
}
