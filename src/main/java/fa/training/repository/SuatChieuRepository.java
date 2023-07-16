package fa.training.repository;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fa.training.entities.SuatChieu;
import fa.training.page.PageAble;

/**
 * 
 * Project: Cinema WebApp
 * Team: 2
 * Author : BaoHC1
 * Repository Suat Chieu
 */
@Repository
public class SuatChieuRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<SuatChieu> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT c FROM SuatChieu c", SuatChieu.class).getResultList();
	}
	
	public void saveOrUpdate(SuatChieu SuatChieu) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(SuatChieu);
	}
	
	public void delete(SuatChieu SuatChieu) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(SuatChieu);
	}
	
	public SuatChieu findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(SuatChieu.class, id);
	}
	
	public List<SuatChieu> findWithPageAble(PageAble pageAble) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT c FROM SuatChieu c", SuatChieu.class)
				.setFirstResult(pageAble.getOffset())
				.setMaxResults(pageAble.getSize())
				.getResultList();
	}
	
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT COUNT(*) FROM SuatChieu", Long.class).getSingleResult();
	}
	
	public List<SuatChieu> search(String searchKey) {
		Session session = sessionFactory.getCurrentSession();
		Query<SuatChieu> query = session.createQuery("SELECT c FROM SuatChieu c WHERE c.phim.maPhim LIKE :searchKey", SuatChieu.class);
		query.setParameter("searchKey", "%" + searchKey + "%");
		return query.getResultList();
	}
	
	public boolean existInDB(String s) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery("SELECT COUNT(*) FROM SuatChieu c WHERE c.ngayChieu = :s", Long.class);
		query.setParameter("s", s);
		return query.getSingleResult() > 0;
	}
	
	public List<SuatChieu> searchByDate(String maPhim,LocalDate ngayChieu) {
		Session session = sessionFactory.getCurrentSession();
		Query<SuatChieu> query = session.createQuery("SELECT c FROM SuatChieu c WHERE c.phim.maPhim LIKE :maPhim and c.ngayChieu = :ngayChieu", SuatChieu.class);
		query.setParameter("maPhim", maPhim );
		query.setParameter("ngayChieu", ngayChieu);
		return query.getResultList();
	}
}
