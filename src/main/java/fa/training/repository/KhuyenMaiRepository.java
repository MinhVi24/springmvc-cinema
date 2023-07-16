package fa.training.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fa.training.entities.KhuyenMai;
import fa.training.page.PageAble;

@Repository
public class KhuyenMaiRepository {

	@Autowired
	SessionFactory sessionFactory;

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : Chức năng tìm kiếm tất cả
	 */
	public List<KhuyenMai> findAll() {
		String hql = "FROM KhuyenMai";
		return sessionFactory.getCurrentSession().createQuery(hql, KhuyenMai.class).getResultList();
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng lưu khuyến mãi
	 */
	public void save(KhuyenMai khuyenMai) {
		Session session = sessionFactory.getCurrentSession();
		session.save(khuyenMai);
	}

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : Chức năng cập nhật khuyến mãi
	 */
	public void saveOrUpdate(KhuyenMai khuyenMai) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(khuyenMai);
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng tìm kiếm theo ID
	 */
	public KhuyenMai findById(String Id) {
		return sessionFactory.getCurrentSession().find(KhuyenMai.class, Id);
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng xóa khuyến mãi
	 */
	public void delete(KhuyenMai khuyenMai) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(khuyenMai);
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng tìm kiếm khuyến mãi
	 */
	public List<KhuyenMai> search(String searchKey) {
		Session session = sessionFactory.getCurrentSession();
		Query<KhuyenMai> createQuery = session.createQuery(
				"SELECT d FROM KhuyenMai d where d.maKhuyenMai like :searchKey or d.tenKhuyenMai like :searchKey or d.moTaKhuyenMai like :searchKey or d.tiLeKhuyenMai like :searchKey",
				KhuyenMai.class);
		createQuery.setParameter("searchKey", "%" + searchKey + "%");
		List<KhuyenMai> listRS = createQuery.getResultList();
		return listRS;
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng dùng để tạo phân trang
	 */
	public List<KhuyenMai> findWithPageAble(PageAble pageAble) {
		Session session = sessionFactory.getCurrentSession();
		List<KhuyenMai> KhuyenMais = session.createQuery("SELECT m FROM KhuyenMai m", KhuyenMai.class)
				.setFirstResult(pageAble.getOffset())// Offset
				.setMaxResults(pageAble.getSize()) // limit
				.getResultList();

		return KhuyenMais;
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng dùng để đếm tổng số khuyến mãi
	 */
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT COUNT(*) FROM KhuyenMai m", Long.class).getSingleResult();
	}

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : Chức năng dùng để check trùng mã dịch vụ
	 */
	public KhuyenMai maKhuyenMai(String maKhuyenMai) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From KhuyenMai as m where maKhuyenMai = :maKhuyenMai";
		javax.persistence.Query query = session.createQuery(hql, KhuyenMai.class);
		query.setParameter("maKhuyenMai", maKhuyenMai);
		if (query.getResultList().size() == 0) {
			return null;
		} else {
			KhuyenMai khuyenMai = (KhuyenMai) query.getResultList().get(0);
			return khuyenMai;
		}
	}

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : Chức năng hiển thị top 3 khuyến mãi
	 */
	public List<KhuyenMai> findTop3KhuyenMai() {
		String hql = "SELECT d FROM KhuyenMai d";
		return sessionFactory.getCurrentSession().createQuery(hql, KhuyenMai.class).setMaxResults(3).getResultList();
	}
	
	public boolean checkSuDungKM(String maKhuyenMai) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(v.maKhuyenMai)\n" + 
				"from KHUYENMAI km join VE v on km.maKhuyenMai = v.maKhuyenMai\n" + 
				"where km.maKhuyenMai = '"+ maKhuyenMai +"'";
		Query query = session.createNativeQuery(hql);
		if ((Integer) query.getSingleResult() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
