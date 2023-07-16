package fa.training.service;

import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.entities.KhachHang;
import fa.training.entities.TaiKhoan;
import fa.training.page.PageAble;
import fa.training.repository.TaiKhoanRepository;

@Service
public class TaiKhoanService {

	@Autowired
	private TaiKhoanRepository taiKhoanRepository;

	public List<TaiKhoan> findAll() {
		return taiKhoanRepository.findAll();
	}

	public void saveOrUpdate(TaiKhoan TaiKhoan) {
		TaiKhoan.setRole("ROLE_USER");
		TaiKhoan.setTrangThai("active");
		taiKhoanRepository.saveOrUpdate(TaiKhoan);
	}

	public void delete(String id) {
		TaiKhoan TaiKhoan = taiKhoanRepository.findById(id);
		if (TaiKhoan != null) {
			taiKhoanRepository.delete(TaiKhoan);
		}
	}

	public TaiKhoan findById(String id) {
		return taiKhoanRepository.findById(id);
	}

	public List<TaiKhoan> findWithPageAble(PageAble pageAble) {
		return taiKhoanRepository.findWithPageAble(pageAble);
	}

	public int totalPages(PageAble pageAble) {
		return (int) Math.ceil((double) taiKhoanRepository.count() / pageAble.getSize());
	}

	public List<TaiKhoan> search(String searchKey) {
		return taiKhoanRepository.search(searchKey);
	}

	public boolean existInDB(String s) {
		return taiKhoanRepository.existInDB(s);
	}

	public boolean finByAccount(String account, String password) {
		return taiKhoanRepository.findByAccount(account, password);
	}

	public TaiKhoan finByAccount(String account) {
		return taiKhoanRepository.findByAccount(account);
	}

	public void save(TaiKhoan tk) {
		taiKhoanRepository.save(tk);
	}

	public void addKH(KhachHang kh) {
		taiKhoanRepository.addKhachHang(kh);
	}

	public KhachHang searchKH(String email) {
		return taiKhoanRepository.findByEmail(email);
	}

	public void updatePass(String tk, String pw) {
		taiKhoanRepository.updatePass(tk, pw);
	}

	public boolean regexPass(String a) {
		if (Pattern.matches("^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$", a)) {
			return false;
		}
		return true;
	}

	public boolean regexHoTen(String a) {
		if (Pattern.matches(
				"^[a-zA-Z_ÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\ ]+$",
				a)) {
			return true;
		}
		return false;
	}

	public boolean regexSDT(String a) {
		if (Pattern.matches("^[0][0-9]{9}$", a)) {
			return true;
		}
		return false;
	}

	public boolean regexEmail(String a) {
		if (Pattern.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", a)) {
			return true;
		}
		return false;
	}

	public boolean regexUsername(String a) {
		if (Pattern.matches("^(\\w{6,20})$", a)) {
			return true;
		}
		return false;
	}

}
