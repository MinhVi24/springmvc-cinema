package fa.training.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.entities.SuDungDichVu;
import fa.training.page.PageAble;
import fa.training.repository.SuDungDichVuRepository;

@Service
@Transactional
public class SuDungDichVuService {

	@Autowired

	SuDungDichVuRepository suDungDichVuRepository;

	public void save(SuDungDichVu suDungDichVu) {
		suDungDichVuRepository.save(suDungDichVu);
	}

	public void saveOrUpdate(SuDungDichVu suDungDichVu) {
		suDungDichVuRepository.saveOrUpdate(suDungDichVu);
	}

	public List<SuDungDichVu> find() {
		List<SuDungDichVu> listSuDungDichVu = suDungDichVuRepository.find();
		return listSuDungDichVu;
	}

	public List<Object[]> getDoanhThuTheoThang(int month, int year) {
		return suDungDichVuRepository.getDoanhThuTheoThang(month, year);
	}

	public List<Object[]> getDoanhThuTheoNam(int year) {
		return suDungDichVuRepository.getDoanhThuTheoNam(year);
	}

	public long getTotalTicketPaymentByCustomer(int maKhachHang) {
		return suDungDichVuRepository.getTotalTicketPaymentByCustomer(maKhachHang);
	}

	public List<Object[]> getDoanhThuDichVuTheoNgay(LocalDate ngay) {
		return suDungDichVuRepository.getDoanhThuDichVuTheoNgay(ngay);
	}

	public List<SuDungDichVu> findByIdKhachHang(int maKhachHang) {
		return suDungDichVuRepository.findByIdKhachHang(maKhachHang);
	}

	public List<SuDungDichVu> findWithPageAble(PageAble pageAble) {
		return suDungDichVuRepository.findWithPageAble(pageAble);
	}

	public int totalPages(PageAble pageAble) {
		long totalRecord = suDungDichVuRepository.count();
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

	public List<SuDungDichVu> findWithPageAble(PageAble pageAble, int maKhachHang) {
		return suDungDichVuRepository.findWithPageAble(pageAble, maKhachHang);
	}

	public int totalPages(PageAble pageAble, int maKhachHang) {
		long totalRecord = suDungDichVuRepository.countSuDungDichVu(maKhachHang);
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

	public List<SuDungDichVu> findWithPageAbleall(PageAble pageAble, int maKhachHang) {
		return suDungDichVuRepository.findWithPageAble(pageAble, maKhachHang);
	}

	public int totalPagesall(PageAble pageAble, int maKhachHang) {
		long totalRecord = suDungDichVuRepository.countSuDungDichVuall(maKhachHang);
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

	public List<SuDungDichVu> findKhachHang(int maKhachHang) {
		return suDungDichVuRepository.findKhachHang(maKhachHang);
	}
}
