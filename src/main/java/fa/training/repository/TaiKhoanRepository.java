package fa.training.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fa.training.entities.KhachHang;
import fa.training.entities.TaiKhoan;
import fa.training.page.PageAble;

@Repository
@Transactional
public class TaiKhoanRepository {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : tìm kiếm tài khoản
	 */
	public List<TaiKhoan> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT c FROM TaiKhoan c", TaiKhoan.class).getResultList();
	}

	/**
	 * Project: Cinema 
	 * WebApp Method : update tài khoản
	 */
	public void saveOrUpdate(TaiKhoan TaiKhoan) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(TaiKhoan);
	}

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : xóa tài khoản
	 */
	public void delete(TaiKhoan TaiKhoan) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(TaiKhoan);
	}

	/**
	 * 
	 * Project: Cinema WebApp
	 * Method : tìm theo ID
	 */
	public TaiKhoan findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(TaiKhoan.class, id);
	}

	/**
	 * 
	 * Project: Cinema 
	 * WebApp Method : phân trang
	 */
	public List<TaiKhoan> findWithPageAble(PageAble pageAble) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT c FROM TaiKhoan c", TaiKhoan.class).setFirstResult(pageAble.getOffset())
				.setMaxResults(pageAble.getSize()).getResultList();
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : tổng số trang của phân trang
	 */
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT COUNT(*) FROM TaiKhoan", Long.class).getSingleResult();
	}

	/**
	 * 
	 * Project: Cinema WebApp 
	 * Method : tìm kiếm tài khoản
	 */
	public List<TaiKhoan> search(String searchKey) {
		Session session = sessionFactory.getCurrentSession();
		Query<TaiKhoan> query = session.createQuery("SELECT c FROM TaiKhoan c WHERE c.account LIKE :searchKey",
				TaiKhoan.class);
		query.setParameter("searchKey", "%" + searchKey + "%");
		return query.getResultList();
	}

	/**
	 * Project: Cinema WebApp 
	 * Method : tổng số tài khoản
	 */
	public boolean existInDB(String s) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery("SELECT COUNT(*) FROM TaiKhoan c WHERE c.account = :s", Long.class);
		query.setParameter("s", s);
		return query.getSingleResult() > 0;
	}

	/**
	 * Project: Cinema WebApp 
	 * Method: có tồn tại trong database (form Đăng Nhập)
	 */
	public boolean findByAccount(String account, String password) {
		Session session = null;
		List<TaiKhoan> accounts = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			System.out.println(account + password);
			String hql = "FROM TaiKhoan t  where t.account =:account and t.password=:password";
			accounts = session.createQuery(hql).setParameter("account", account).setParameter("password", password)
					.list();
			if (accounts.size() > 0) {
				if (password.equals(accounts.get(0).getPassword())) {
					return true;
				} else {
					return false;
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Project: Cinema WebApp
	 * Method: lấy object ACCOUNT trong database
	 */
	public TaiKhoan findByAccount(String account) {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		TaiKhoan tk = session.find(TaiKhoan.class, account);
		return tk;
	}

	/**
	 * Project:Cinema WebApp  
	 * 
	 * Method: lấy object KhachHang để insert vào Bảng Tài Khoản (form Đăng Ký)
	 */
	public KhachHang findByEmail(String email) {
		Session session = null;
		List<KhachHang> kh = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			String hql = "FROM KhachHang kh where kh.email =:email";
			kh = session.createQuery(hql).setParameter("email", email).getResultList();
			if (kh.size() > 0) {
				return kh.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Project:Cinema WebApp  
	 * Method: Dùng để lấy object KhachHang từ mã KH (form Đăng Ký)
	 */
	public KhachHang findByMaKH(int maKhachHang) {
		Session session = null;
		List<KhachHang> kh = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			String hql = "FROM KhachHang kh where kh.maKhachHang =:maKhachHang";
			kh = session.createQuery(hql).setParameter("maKhachHang", maKhachHang).list();
			if (kh.size() > 0) {
				return kh.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Project:Cinema WebApp  
	 * Method: insert Tài Khoản vào Bảng Tài Khoản (form Đăng Ký)
	 */
	public void save(TaiKhoan tk) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(tk);
	}

	/**
	 * Project:Cinema WebApp  
	 * Method: insert Khách Hàng vào Bảng Khách hàng (form Đăng Ký)
	 */
	public void addKhachHang(KhachHang kh) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.save(kh);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * Project:Cinema WebApp  
	 * Method: Dùng để update Pass (form ResetPass)
	 */
	public void updatePass(String tk, String pw) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Query query = session
					.createQuery("update TaiKhoan tk set tk.password = :password  where tk.account = :account");
			query.setParameter("password", pw);
			query.setParameter("account", tk);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
