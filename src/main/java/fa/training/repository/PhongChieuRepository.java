package fa.training.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fa.training.entities.PhongChieu;
import fa.training.page.PageAble;

/**
 * 
 * Project: Cinema WebApp
 * Team: 2
 * Author : BaoHC1
 * Repository Phong Chieu
 */
@Repository
public class PhongChieuRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<PhongChieu> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT c FROM PhongChieu c", PhongChieu.class).getResultList();
	}
	
	public void saveOrUpdate(PhongChieu PhongChieu) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(PhongChieu);
	}
	
	public void delete(PhongChieu PhongChieu) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(PhongChieu);
	}
	
	public PhongChieu findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(PhongChieu.class, id);
	}
	
	public List<PhongChieu> findWithPageAble(PageAble pageAble) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT c FROM PhongChieu c", PhongChieu.class)
				.setFirstResult(pageAble.getOffset())
				.setMaxResults(pageAble.getSize())
				.getResultList();
	}
	
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT COUNT(*) FROM PhongChieu", Long.class).getSingleResult();
	}
	
	public List<PhongChieu> search(String searchKey) {
		Session session = sessionFactory.getCurrentSession();
		Query<PhongChieu> query = session.createQuery("SELECT c FROM PhongChieu c WHERE c.maPhongChieu LIKE :searchKey", PhongChieu.class);
		query.setParameter("searchKey", "%" + searchKey + "%");
		return query.getResultList();
	}
	
	public boolean existInDB(String s) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery("SELECT COUNT(*) FROM PhongChieu c WHERE c.maPhongChieu = :s", Long.class);
		query.setParameter("s", s);
		return query.getSingleResult() > 0;
	}
}
