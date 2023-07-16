package fa.training.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Project:Cinema WebApp
 * Dùng để lấy dữ liệu từ form Đăng Ký insert vào 2 bảng Tài Khoản Và Khách Hàng
 */

public class InsertDK {

	@Pattern(regexp = "^[a-zA-ZÀ-ỹỲ-ỹĐđ]+(\\s[a-zA-ZÀ-ỹỲ-ỹĐđ]+)+{3,50}$", message = "Họ tên ít nhất phải có 2 từ và dài từ 3-50 kí tự")
	@NotBlank(message = "Xin hãy nhập thông tin vào trường này")
	String tenKhachHang;

	@Pattern(regexp = "0[0-9]{9}", message = "Số điện thoại phải bắt đầu bằng 0 và có 10 số")
	@NotBlank(message = "Xin hãy nhập thông tin vào trường này")
	String sdt;

	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Địa chỉ email không đúng định dạng")
	@NotBlank(message = "Xin hãy nhập thông tin vào trường này")
	String email;

	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{2,15}$", message = "Account từ 3-16 kí tự, chỉ chứa dấu gạch dưới và không có kí tự đặc biệt")
	@NotBlank(message = "Xin hãy nhập thông tin vào trường này")
	String account;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{8,300}$", message = "xxxx")
	@NotBlank(message = "Xin hãy nhập thông tin vào trường này")
	String password;

	String repassword;

	String gioiTinh;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Ngày sinh phải nhỏ hơn ngày hiện tại")
	LocalDate ngaySinh;

	String diaChi;

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public InsertDK(String tenKhachHang, String sdt, String email, String account, String password, String repassword,
			String gioiTinh, LocalDate ngaySinh, String diaChi) {
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.email = email;
		this.account = account;
		this.password = password;
		this.repassword = repassword;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
	}

	public InsertDK() {
		super();
	}

}
