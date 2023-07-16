package fa.training.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fa.training.entities.DichVu;
import fa.training.page.PageAble;

@Repository
public class DichVuRepository {

	@Autowired
	SessionFactory sessionFactory;

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng tìm kiếm tất cả
	 */
	public List<DichVu> findAll() {
		String hql = "SELECT d FROM DichVu d";
		return sessionFactory.getCurrentSession().createQuery(hql, DichVu.class).getResultList();
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng lưu dịch vụ
	 */
	public void save(DichVu dichVu) {
		Session session = sessionFactory.getCurrentSession();
		session.save(dichVu);
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng cập nhật dịch vụ
	 */
	public void saveOrUpdate(DichVu dichVu) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(dichVu);
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng tìm kiếm theo ID
	 */
	public DichVu findById(String Id) {
		return sessionFactory.getCurrentSession().find(DichVu.class, Id);
	}

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : Chức năng xóa dịch vụ
	 */
	public void delete(DichVu dichVu) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(dichVu);
	}

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : Chức năng tìm kiếm dịch vụ
	 */
	public List<DichVu> search(String searchKey) {
		Session session = sessionFactory.getCurrentSession();
		Query<DichVu> createQuery = session.createQuery(
				"SELECT d FROM DichVu d where d.maDichVu like :searchKey or d.tenDichVu like :searchKey or d.moTaDichVu like :searchKey or d.donGia like :searchKey",
				DichVu.class);
		createQuery.setParameter("searchKey", "%" + searchKey + "%");
		List<DichVu> listRS = createQuery.getResultList();
		return listRS;
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng dùng để tạo phân trang
	 */
	public List<DichVu> findWithPageAble(PageAble pageAble) {
		Session session = sessionFactory.getCurrentSession();
		List<DichVu> DichVus = session.createQuery("SELECT m FROM DichVu m", DichVu.class)
				.setFirstResult(pageAble.getOffset())// Offset
				.setMaxResults(pageAble.getSize()) // limit
				.getResultList();

		return DichVus;
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : Chức năng dùng để check trùng mã dịch vụ
	 */
	public DichVu maDichVu(String maDichVu) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From DichVu as m where maDichVu = :maDichVu";
		javax.persistence.Query query = session.createQuery(hql, DichVu.class);
		query.setParameter("maDichVu", maDichVu);
		if (query.getResultList().size() == 0) {
			return null;
		} else {
			DichVu dichVu = (DichVu) query.getResultList().get(0);
			return dichVu;
		}
	}

	/*
	 * Project: Cinema WebApp 
	 * Method: lấy số lượng dịch vụ hiện có trong DB
	 */
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT COUNT(*) FROM DichVu m", Long.class).getSingleResult();
	}
	
	public boolean checkSuDungDichVu(String maDichVu) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT COUNT(sddv.maDichVu) From SUDUNGDICHVU sddv join DICHVU dv on sddv.maDichVu = dv.maDichVu"
				+ " where dv.maDichVu = '" + maDichVu + "'";
		Query query = session.createNativeQuery(hql);
		if ((Integer) query.getSingleResult() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
