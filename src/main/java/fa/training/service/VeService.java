package fa.training.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.entities.Ve;
import fa.training.page.PageAble;
import fa.training.repository.VeRepository;

@Service
@Transactional
public class VeService {

	@Autowired
	private VeRepository veRepository;

	public void save(Ve ve) {
		veRepository.save(ve);
	}

	public void saveOrUpdate(Ve ve) {
		veRepository.saveOrUpdate(ve);
	}

	public List<Ve> find() {
		List<Ve> listVe = veRepository.find();
		return listVe;
	}

	public Ve findById(int maVe) {
		return veRepository.findById(maVe);
	}

	public Ve maVe(int maVe) {
		return veRepository.maVe(maVe);
	}

	public void delete(int maVe) {
		Ve ve = findById(maVe);
		if (ve != null) {
			veRepository.delete(ve);
		}
	}

	public List<Ve> search(int maSuatChieu, String maGhe) {
		return veRepository.search(maSuatChieu, maGhe);
	}

	public int updateHuyVe(String maGhe, int maSuatChieu, LocalDate ngayMuaVe, int maKhachHang) {
		return veRepository.updateHuyVe(maGhe, maSuatChieu, ngayMuaVe, maKhachHang);
	}

	public int updateDatVe(String maGhe, int maSuatChieu, LocalDate ngayMuaVe, int maKhachHang) {
		return veRepository.updateDatVe(maGhe, maSuatChieu, ngayMuaVe, maKhachHang);
	}

	public List<Object[]> getDoanhThuTheoThang(int month, int year) {
		return veRepository.getDoanhThuTheoThang(month, year);
	}

	public List<Object[]> getDoanhThuTheoNam(int year) {
		return veRepository.getDoanhThuTheoNam(year);
	}

	public List<Ve> getListBySuatChieu(int searchKey) {
		return veRepository.getListBySuatChieu(searchKey);
	}

	public List<Object[]> getDoanhThuBanVeTheoNgay(LocalDate ngay) {
		return veRepository.getDoanhThuBanVeTheoNgay(ngay);
	}

	public long getTotalTicketPaymentByCustomer(int maKhachHang) {
		return veRepository.getTotalTicketPaymentByCustomer(maKhachHang);
	}

	public List<Ve> findByIdKhachHang(int maKhachHang) {
		return veRepository.findByIdKhachHang(maKhachHang);
	}

	public List<Ve> findWithPageAble(PageAble pageAble, int maKhachHang) {
		return veRepository.findWithPageAble(pageAble, maKhachHang);
	}

	public int totalPages(PageAble pageAble, int maKhachHang) {
		long totalRecord = veRepository.countmuave(maKhachHang);
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

	public List<Ve> findWithPageAbleall(PageAble pageAble, int maKhachHang) {
		return veRepository.findWithPageAble(pageAble, maKhachHang);
	}

	public int totalPagesall(PageAble pageAble, int maKhachHang) {
		long totalRecord = veRepository.countmuave(maKhachHang);
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

}
