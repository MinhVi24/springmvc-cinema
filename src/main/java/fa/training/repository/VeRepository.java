package fa.training.repository;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fa.training.entities.Ve;
import fa.training.page.PageAble;

@Repository
public class VeRepository {

	@Autowired
	SessionFactory sessionFactory;

	public void save(Ve ve) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(ve);
	}

	public void saveOrUpdate(Ve Ve) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(Ve);
	}

	/*
	 * Project: Cinema WebApp  
	 * Method: cập nhật thông tin đặt vé
	 */
	public int updateDatVe(String maGhe, int maSuatChieu, LocalDate ngayMuaVe, int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE Ve as VE SET VE.trangThai = 2, VE.ngayMuaVe = :ngayMuaVe,VE.khachHang.maKhachHang = :maKhachHang where VE.suatChieu.maSuatChieu = :maSuatChieu and VE.maGhe = :maGhe";
		org.hibernate.query.Query<Ve> createQuery = session.createQuery(hql);
		createQuery.setParameter("maSuatChieu", maSuatChieu);
		createQuery.setParameter("maGhe", maGhe);
		createQuery.setParameter("ngayMuaVe", ngayMuaVe);
		createQuery.setParameter("maKhachHang", maKhachHang);
		int x = createQuery.executeUpdate();
		return x;
	}

	/*
	 * Project: Cinema WebApp 
	 * Method: cập nhật thông tin hủy vé
	 */
	public int updateHuyVe(String maGhe, int maSuatChieu, LocalDate ngayMuaVe, int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE Ve as VE SET VE.trangThai = 1, VE.ngayMuaVe = :ngayMuaVe,VE.khachHang.maKhachHang = :maKhachHang where VE.suatChieu.maSuatChieu = :maSuatChieu and VE.maGhe = :maGhe";
		org.hibernate.query.Query<Ve> createQuery = session.createQuery(hql);
		createQuery.setParameter("maSuatChieu", maSuatChieu);
		createQuery.setParameter("maGhe", maGhe);
		createQuery.setParameter("ngayMuaVe", ngayMuaVe);
		createQuery.setParameter("maKhachHang", maKhachHang);
		int x = createQuery.executeUpdate();
		return x;
	}

	public List<Ve> find() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Ve";
		List<Ve> listVe = session.createQuery(hql, Ve.class).getResultList();
		return listVe;
	}

	public Ve findById(int ve) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Ve.class, ve);
	}

	public Ve maVe(int maVe) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Ve where maVe =: maVe";
		Query query = session.createQuery(hql, Ve.class);
		query.setParameter("maVe", maVe);
		if (query.getResultList().size() == 0) {
			return null;
		} else {
			Ve ve = (Ve) query.getResultList().get(0);
			return ve;
		}
	}

	public void delete(Ve ve) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(ve);
	}

	/*
	 * Project: Cinema WebApp  
	 * Method: lấy danh sách ghế theo mã suất chiếu và mã ghế
	 */
	public List<Ve> search(int maSuatChieu, String maGhe) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Ve as VE where VE.suatChieu.maSuatChieu = :maSuatChieu and VE.maGhe LIKE :maGhe";
		Query<Ve> createQuery = session.createQuery(hql, Ve.class);
		createQuery.setParameter("maSuatChieu", maSuatChieu);
		createQuery.setParameter("maGhe", "%" + maGhe + "%");
		List<Ve> ve = createQuery.getResultList();
		return ve;
	}

	/*
	 * Project: Cinema WebApp  
	 * Method: lấy số doanh thu bán vé theo ngày trong tháng
	 */
	public List<Object[]> getDoanhThuTheoThang(int month, int year) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select sc.ngayChieu, COUNT(*), sum(pc.donGia * (100 - km.tiLeKhuyenMai)/100)"
				+ "From Ve v join v.suatChieu sc join sc.phongChieu pc join v.khuyenMai km where v.trangThai = '2' and MONTH(sc.ngayChieu) = "
				+ month + " and YEAR(sc.ngayChieu) = " + year + " group by sc.ngayChieu";
		return session.createQuery(hql).getResultList();
	}

	/*
	 * Project: Cinema WebApp
	 * Method: lấy số doanh thu bán vé theo tháng trong năm
	 */
	public List<Object[]> getDoanhThuTheoNam(int year) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select MONTH(sc.ngayChieu), COUNT(*), sum(pc.donGia * (100 - km.tiLeKhuyenMai)/100)"
				+ "From Ve v join v.suatChieu sc join sc.phongChieu pc join v.khuyenMai km where v.trangThai = '2' and YEAR(sc.ngayChieu) = "
				+ year + " group by MONTH(sc.ngayChieu)";
		return session.createQuery(hql).getResultList();
	}
	
	/*
	 * Project: Cinema WebApp 
	 * Method: lấy số doanh thu bán vé theo ngày
	 */
	public List<Object[]> getDoanhThuBanVeTheoNgay(LocalDate ngay) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select ph.tenPhim, count(distinct sc.maSuatChieu) as soSuatChieu, count(v.maGhe) as soLuongVeDaBan, sum(pc.donGia * (100 - km.tiLeKhuyenMai)/100) tongDoanhThu\n" + 
				"From Ve v join suatChieu sc on v.maSuatChieu = sc.maSuatChieu\n" + 
				"join phongChieu pc on sc.maPhongChieu = pc.maPhongChieu\n" + 
				"join PHIM ph on ph.maPhim = sc.maPhim\n" + 
				"join khuyenMai km on km.maKhuyenMai = v.maKhuyenMai\n" + 
				"where v.trangThai = '2' and sc.ngayChieu = '"+ngay+"'  group by ph.tenPhim";
		return session.createSQLQuery(sql).getResultList();
	}

	/*
	 * Project: Cinema WebApp 
	 * Method: lấy danh sách vé theo suất chiếu cụ thể
	 */
	public List<Ve> getListBySuatChieu(int searchKey) {
		Session session = sessionFactory.getCurrentSession();
		Query<Ve> query = session.createQuery("SELECT c FROM Ve c WHERE c.suatChieu.maSuatChieu = :searchKey",
				Ve.class);
		query.setParameter("searchKey", searchKey);
		return query.getResultList();
	}

	/*
	 * Project: Cinema WebApp 
	 * Method: Lấy tổng chi tiêu mua vé của Khách hàng trong năm hiện tại
	 */
	public long getTotalTicketPaymentByCustomer(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		double result = 0;
		try {
			Query query = session
					.createSQLQuery("select sum( (100-km.tiLeKhuyenMai) * pc.donGia/100 ) as tongtien from \n"
							+ "Ve v join KhuyenMai km on v.maKhuyenMai = km.maKhuyenMai\n"
							+ "join SuatChieu sc on sc.maSuatChieu = v.maSuatChieu \n"
							+ "join PhongChieu pc on sc.maPhongChieu = pc.maPhongChieu\n" + "where v.maKhachHang = "
							+ maKhachHang + " and YEAR(v.ngayMuaVe) = YEAR(GETDATE())");
			result = (double) query.getSingleResult();
		} catch (Exception e) {
			result = 0;
		}
		return (long) result;
	}
	



	/**
	 * Project: Cinema WebApp Team: 2 Author : ViTM Fuciton :
	 */
	public List<Ve> findByIdKhachHang(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Ve V where V.khachHang.maKhachHang = :maKhachHang";
		Query<Ve> query = session.createQuery(hql, Ve.class);
		query.setParameter("maKhachHang", maKhachHang);
		List<Ve> listVe = query.getResultList();
		return listVe;
	}

	/**
	 * Project: Cinema WebApp Team: 2 Author : ViTM Fuciton :
	 */
	public List<Ve> findWithPageAble(PageAble pageAble, int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		List<Ve> ves = session
				.createQuery("SELECT sddv FROM Ve sddv where sddv.khachHang.maKhachHang = :maKhachHang ", Ve.class)
				.setParameter("maKhachHang", maKhachHang).setFirstResult(pageAble.getOffset())// Offset
				.setMaxResults(pageAble.getSize()) // limit
				.getResultList();

		return ves;
	}

	/**
	 * Project: Cinema WebApp Team: 2 Author : ViTM Fuciton :
	 */
	public long countmuave(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		return session
				.createQuery("SELECT COUNT(*) FROM Ve sddv where sddv.khachHang.maKhachHang = :maKhachHang", Long.class)
				.setParameter("maKhachHang", maKhachHang).getSingleResult();
	}

	/**
	 * Project: Cinema WebApp Team: 2 Author : ViTM Fuciton :
	 */
	public List<Ve> findWithPageAbleall(PageAble pageAble, int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		List<Ve> ves = session
				.createQuery("SELECT sddv FROM Ve sddv where sddv.khachHang.maKhachHang = :maKhachHang ", Ve.class)
				.setParameter("maKhachHang", maKhachHang).setFirstResult(pageAble.getOffset())// Offset
				.setMaxResults(pageAble.getSize()) // limit
				.getResultList();

		return ves;
	}

	/**
	 * Project: Cinema WebApp Team: 2 Author : ViTM Fuciton :
	 */
	public long countmuaveall(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		return session
				.createQuery("SELECT COUNT(*) FROM Ve sddv where sddv.khachHang.maKhachHang = :maKhachHang", Long.class)
				.setParameter("maKhachHang", maKhachHang).getSingleResult();
	}
}
