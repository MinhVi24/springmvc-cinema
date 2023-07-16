package fa.training.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entities.Phim;
import fa.training.entities.SuatChieu;
import fa.training.page.PageAble;
import fa.training.repository.PhimRepository;

@Service
@Transactional
public class PhimService {
	@Autowired
	private PhimRepository phimRepository;

	public List<Phim> findAll() {
		return phimRepository.findAll();
	}

	public List<Phim> findTop3() {
		List<Phim> listPhim = phimRepository.findTop3();
		return listPhim;
	}

	public List<Phim> findPhimGoiY(String tenTheLoai,String id) {
		List<Phim> listPhim = phimRepository.findPhimGoiY(tenTheLoai,id);
		return listPhim;
	}

	public List<Phim> findAllSauTop3() {
		List<Phim> listPhim = phimRepository.findAllSauTop3();
		return listPhim;
	}
	
	public List<Phim> findPhimSapChieu() {
		List<Phim> listPhim = phimRepository.findPhimSapChieu();
		return listPhim;
	}

	public List<Phim> findHoatHinh() {
		List<Phim> listPhim = phimRepository.findHoatHinh();
		return listPhim;
	}

	public List<Phim> findPhimDangChieu(PageAble pageAble) {
		List<Phim> listPhim = phimRepository.findPhimDangChieu(pageAble);
		return listPhim;
	}

	public List<Phim> findPhimSapChieu(PageAble pageAble) {
		List<Phim> listPhim = phimRepository.findPhimSapChieu(pageAble);
		return listPhim;
	}
	
	public Phim findById(String id) {
		return phimRepository.findById(id);
	}

	public void save(Phim phim) {
		phimRepository.save(phim);
	}

	public void update(Phim phim) {
		phimRepository.update(phim);
	}

	public void delete(String id) {
		phimRepository.delete(id);
	}
	
	public int totalPagesPhimDangChieu(PageAble pageAble) {
		long totalRecord = phimRepository.countPhimDangChieu();
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

	public int totalPagesPhimSapChieu(PageAble pageAble) {
		long totalRecord = phimRepository.countPhimSapChieu();
		return (int) Math.ceil((double) totalRecord / pageAble.getSize());
	}

	public List<Phim> searchTenPhim(String searchKey){
		return phimRepository.searchTenPhim(searchKey);
	}

	public List<Phim> searchDaoDien(String searchKey){
		return phimRepository.searchDaoDien(searchKey);
	}
	
	public List<Phim> searchTheLoai(String searchKey){
		return phimRepository.searchTheLoai(searchKey);
	}
	
	public List<SuatChieu> searchNgayChieu(LocalDate searchKey){
		return phimRepository.searchNgayChieu(searchKey);
	}

   public List<Phim> searchTenPhimDangChieu(String searchKey, PageAble pageAble){
       return phimRepository.searchTenPhimDangChieu(searchKey,pageAble);
   }

   public int totalPagesPhimDangChieuTheoTen(String searchKey,PageAble pageAble) {
       long totalRecord = phimRepository.countPhimTheoTen(searchKey);
       return (int) Math.ceil((double) totalRecord / pageAble.getSize());
   }

   public List<Phim> searchTenPhimSapChieu(String searchKey,PageAble pageAble){
       return phimRepository.searchTenPhimSapChieu(searchKey,pageAble);
   }

   public int totalPagesPhimSapChieuTheoTen(String searchKey,PageAble pageAble) {
       long totalRecord = phimRepository.countPhimSapChieuTheoTen(searchKey);
       return (int) Math.ceil((double) totalRecord / pageAble.getSize());
   }

	public List<Object[]> getDoanhThu(){
		return phimRepository.getDoanhThu();
	}
}
