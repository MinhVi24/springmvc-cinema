package fa.training.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fa.training.entities.KhachHang;
import fa.training.page.PageAble;

@Repository
public class KhachHangRepository {
	@Autowired

	private SessionFactory sessionFactory;

	public void save(KhachHang khachHang) {
		Session session = sessionFactory.getCurrentSession();
		session.save(khachHang);

	}

	public void saveOrUpdate(KhachHang khachHang) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(khachHang);

	}

	public List<KhachHang> find() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From KhachHang";
		List<KhachHang> listKhachHang = session.createQuery(hql, KhachHang.class).getResultList();
		return listKhachHang;

	}

	public KhachHang findById(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(KhachHang.class, maKhachHang);
	}

	/*
	 * Project: Cinema WebApp  
	 * Method: lấy số lượng khách hành hiện có trong DB theo giới tính
	 */
	public long getCountByGender(String gioiTinh) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT COUNT(*) FROM KhachHang kh WHERE kh.gioiTinh = N'" + gioiTinh + "'";
		long number = Long.parseLong(session.createSQLQuery(sql).getSingleResult().toString());
		return number;
	}

	/*
	 * Project: Cinema WebApp   
	 * Method: lấy số lượng khách hành hiện có trong DB theo độ tuổi
	 */
	public long getCountByAge(int min, int max) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT COUNT(*) FROM KhachHang kh WHERE DateDiff(YEAR,kh.ngaySinh,getDate()) between " + min
				+ " and " + max;
		long number = Long.parseLong(session.createSQLQuery(sql).getSingleResult().toString());
		return number;
	}

	/**
	 * Project: Cinema WebApp 
	 * Author : ViTM
	 * Method : Tìm kiếm khách hàng dựa trên tên khách hàng.
	 */
	public KhachHang tenKhachHang(String tenKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From KhachHang where tenKhachHang = :tenKhachHang";
		Query query = session.createQuery(hql, KhachHang.class);
		query.setParameter("tenKhachHang", tenKhachHang);
		if (query.getResultList().size() == 0) {
			return null;
		} else {
			KhachHang khachHang = (KhachHang) query.getResultList().get(0);
			return khachHang;
		}
	}

	/**
	 * Project: Cinema WebApp  
	 *  Author : ViTM 
	 *  Method :  Tìm kiếm khách hàng dựa trên mã khách hàng.
	 */
	public List<KhachHang> findByIdKhachHang(int maKhachHang) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From KhachHang as KH where KH.maKhachHang =  :maKhachHang";
		org.hibernate.query.Query<KhachHang> query = session.createQuery(hql, KhachHang.class);
		query.setParameter("maKhachHang", maKhachHang);
		List<KhachHang> listKhachHang = query.getResultList();
		return listKhachHang;
	}

	/**
	 * Project: Cinema WebApp  
	 * Author : ViTM
	 * Method : Xóa khách hàng khỏi cơ sở dữ liệu.
	 */
	public void delete(KhachHang khachHang) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(khachHang);
	}

	/**
	 * Project: Cinema WebApp  
	 *  Author : ViTM 
	 *  Method : Tìm kiếm và trả về danh sách khách hàng với phân trang.
	 */
	public List<KhachHang> findWithPageAble(PageAble pageAble) {
		Session session = sessionFactory.getCurrentSession();
		List<KhachHang> KhachHangs = session.createQuery("SELECT m FROM KhachHang m", KhachHang.class)
				.setFirstResult(pageAble.getOffset())// Offset
				.setMaxResults(pageAble.getSize()) // limit
				.getResultList();

		return KhachHangs;
	}
	/**
	 * Project: Cinema WebApp   
	 * Author : ViTM 
	 * Method : Tìm kiếm khách hàng dựa trên tên khách hàng, email hoặc số điện thoại với phân trang.
	 */
	public List<KhachHang> search(final String tenKhachHang, final PageAble pageAble) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "From KhachHang as KH where KH.tenKhachHang like  :tenKhachHang or KH.email like :tenKhachHang or KH.sdt like :tenKhachHang  ";
		org.hibernate.query.Query<KhachHang> createQuery = session.createQuery(hql, KhachHang.class);
		createQuery.setParameter("tenKhachHang", ("%" + tenKhachHang + "%"));
		final List<KhachHang> khachHang = createQuery.setFirstResult(pageAble.getOffset())
				.setMaxResults(pageAble.getSize()).getResultList();
		return khachHang;
	}
	/**
	 * Project: Cinema WebApp  
	 * Author : ViTM 
	 * Method : Đếm số lượng khách hàng trong cơ sở dữ liệu.
	 */
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT COUNT(*) FROM KhachHang m", Long.class).getSingleResult();
	}
	/**
	 * Project: Cinema WebApp  
	 *  Author : ViTM 
	 *  Method : Đếm số lượng khách hàng tìm thấy dựa trên tên khách hàng, email hoặc số điện thoại.
	 */
	public long countsearch(final String tenKhachHang) {
		final Session session = this.sessionFactory.getCurrentSession();
		org.hibernate.query.Query<Long> createQuery = session.createQuery(
				"SELECT COUNT(*) FROM KhachHang KH where KH.tenKhachHang like  :tenKhachHang or KH.email like :tenKhachHang or KH.sdt like :tenKhachHang",
				(Class) Long.class);
		createQuery.setParameter("tenKhachHang", ("%" + tenKhachHang + "%"));
		return (long) createQuery.getSingleResult();
	}
}
