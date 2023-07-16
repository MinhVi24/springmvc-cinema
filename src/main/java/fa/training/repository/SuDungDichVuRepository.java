package fa.training.repository;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fa.training.entities.SuDungDichVu;
import fa.training.page.PageAble;

@Repository
public class SuDungDichVuRepository {
	@Autowired

	private SessionFactory sessionFactory;

	public void save(SuDungDichVu suDungDichVu) {
		Session session = sessionFactory.getCurrentSession();
		session.save(suDungDichVu);

	}

	public void saveOrUpdate(SuDungDichVu suDungDichVu) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(suDungDichVu);

	}

	public List<SuDungDichVu> find() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From SuDungDichVu";
		List<SuDungDichVu> listSuDungDichVu = session.createQuery(hql, SuDungDichVu.class).getResultList();
		return listSuDungDichVu;

	}

	/*
	 * Project: Cinema WebApp   Method: lấy số doanh thu dịch
	 * vụ theo ngày trong tháng
	 */
	public List<Object[]> getDoanhThuTheoThang(int month, int year) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select sddv.ngaySuDung , sum((sddv.soLuong * dv.donGia)*(100 - km.tiLeKhuyenMai)/100)"
				+ "From SuDungDichVu sddv join sddv.dichVu dv join sddv.khuyenMai km where MONTH(sddv.ngaySuDung) = "
				+ month + " and YEAR(sddv.ngaySuDung) = " + year + " group by sddv.ngaySuDung";
		return session.createQuery(hql).getResultList();
	}
	
	/*
	 * Ducnm74
	 * Project: Cinema WebApp   Method: lấy số doanh thu dịch vụ
	 *  theo ngày
	 */
	public List<Object[]> getDoanhThuDichVuTheoNgay(LocalDate ngay) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select dv.maDichVu, dv.tenDichVu, sum(sddv.soLuong) as soLuongDaBan, sum(dv.donGia * sddv.soLuong * (100 - km.tiLeKhuyenMai)/100) tongDoanhThu\n" + 
				"From DichVu dv join SuDungDichVu sddv on dv.maDichVu = sddv.maDichVu\n" + 
				"join khuyenMai km on km.maKhuyenMai = sddv.maKhuyenMai\n" + 
				"where sddv.ngaySuDung = '"+ ngay +"'  group by dv.maDichVu,dv.tenDichVu";
		return session.createSQLQuery(hql).getResultList();
	}

	/*
	 * Project: Cinema WebApp   Method: lấy số doanh thu dịch
	 * vụ theo tháng trong năm
	 */
	public List<Object[]> getDoanhThuTheoNam(int year) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select MONTH(sddv.ngaySuDung) , sum((sddv.soLuong * dv.donGia)*(100 - km.tiLeKhuyenMai)/100)"
				+ "From SuDungDichVu sddv join sddv.dichVu dv join sddv.khuyenMai km where YEAR(sddv.ngaySuDung) = "
				+ year + " group by MONTH(sddv.ngaySuDung)";
		return session.createQuery(hql).getResultList();
	}

	/*
	 * Project: Cinema WebApp
	 * Method: Lấy tổng chi tiêu sử dụng dịch vụ của Khách hàng trong năm hiện tại
	 */
	public long getTotalTicketPaymentByCustomer(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		double result = 0;
		try {
			Query query = session.createSQLQuery(
					"select sum( (100-km.tiLeKhuyenMai) * dv.donGia * sddv.soLuong/100 ) as tongtien from \n"
							+ "SuDungDichVu sddv join KHUYENMAI km on sddv.maKhuyenMai = km.maKhuyenMai\n"
							+ "join DichVu dv on dv.maDichVu = sddv.maDichVu\n" + "where sddv.maKhachHang = "
							+ maKhachHang + " and YEAR(sddv.ngaySuDung) = YEAR(GETDATE())");
			result = (double) query.getSingleResult();
		} catch (Exception e) {
			result = 0;
		}
		return (long) result;
	}

	/**
	 * Project: Cinema WebApp
	 *  Author : ViTM 
	 *  Method : Tìm kiếm và trả về danh sách sử dụng dịch vụ của một khách hàng dựa trên mã khách hàng.
	 */
	public List<SuDungDichVu> findKhachHang(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From SuDungDichVu s where s.khachHang.maKhachHang = :maKhachHang";
		List<SuDungDichVu> listSuDungDichVu = session.createQuery(hql, SuDungDichVu.class)
				.setParameter("maKhachHang", maKhachHang).getResultList();
		return listSuDungDichVu;

	}

	/**
	 * Project: Cinema WebApp 
	 * Author : ViTM
	 * Method : Tìm kiếm và trả về danh sách sử dụng dịch vụ của một khách hàng dựa trên mã khách hàng.
	 */
	public List<SuDungDichVu> findByIdKhachHang(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From SuDungDichVu sddv where sddv.khachHang.maKhachHang = :maKhachHang";
		Query<SuDungDichVu> query = session.createQuery(hql, SuDungDichVu.class);
		query.setParameter("maKhachHang", maKhachHang);
		List<SuDungDichVu> listSuDungDichVu = query.getResultList();
		return listSuDungDichVu;
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Method : Tìm kiếm và trả về danh sách sử dụng dịch vụ với phân trang.
	 */
	public List<SuDungDichVu> findWithPageAble(PageAble pageAble) {
		Session session = sessionFactory.getCurrentSession();
		List<SuDungDichVu> suDungDichVus = session.createQuery("SELECT m FROM SuDungDichVu m", SuDungDichVu.class)
				.setFirstResult(pageAble.getOffset())// Offset
				.setMaxResults(pageAble.getSize()) // limit
				.getResultList();

		return suDungDichVus;
	}

	/**
	 * Project: Cinema WebApp  
	 * Author : ViTM
	 * Method : Đếm số lượng sử dụng dịch vụ trong cơ sở dữ liệu.
	 */
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT COUNT(*) FROM SuDungDichVu m", Long.class).getSingleResult();
	}

	/**
	 * Project: Cinema WebApp  
	 * Author : ViTM 
	 * Method : Tìm kiếm và trả về danh sách sử dụng dịch vụ của một khách hàng với phân trang.
	 */
	public List<SuDungDichVu> findWithPageAble(PageAble pageAble, int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		List<SuDungDichVu> suDungDichVus = session
				.createQuery("SELECT sddv FROM SuDungDichVu sddv where sddv.khachHang.maKhachHang = :maKhachHang ",
						SuDungDichVu.class)
				.setParameter("maKhachHang", maKhachHang).setFirstResult(pageAble.getOffset())// Offset
				.setMaxResults(pageAble.getSize()) // limit
				.getResultList();

		return suDungDichVus;
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Method : Đếm số lượng sử dụng dịch vụ của một khách hàng trong cơ sở dữ liệu.
	 */
	public long countSuDungDichVu(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		return session
				.createQuery("SELECT COUNT(*) FROM SuDungDichVu sddv where sddv.khachHang.maKhachHang = :maKhachHang",
						Long.class)
				.setParameter("maKhachHang", maKhachHang).getSingleResult();
	}

	/**
	 * Project: Cinema WebApp  
	 * Author : ViTM 
	 * Method : Tìm kiếm và trả về danh sách sử dụng dịch vụ của một khách hàng với phân trang
	 */
	public List<SuDungDichVu> findWithPageAbleall(PageAble pageAble, int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		List<SuDungDichVu> suDungDichVus = session
				.createQuery("SELECT sddv FROM SuDungDichVu sddv where sddv.khachHang.maKhachHang = :maKhachHang ",
						SuDungDichVu.class)
				.setParameter("maKhachHang", maKhachHang).setFirstResult(pageAble.getOffset())// Offset
				.setMaxResults(pageAble.getSize()) // limit
				.getResultList();

		return suDungDichVus;
	}

	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Method : Đếm số lượng sử dụng dịch vụ của một khách hàng trong cơ sở dữ liệu 
	 */
	public long countSuDungDichVuall(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		return session
				.createQuery("SELECT COUNT(*) FROM SuDungDichVu sddv where sddv.khachHang.maKhachHang = :maKhachHang",
						Long.class)
				.setParameter("maKhachHang", maKhachHang).getSingleResult();
	}
}
