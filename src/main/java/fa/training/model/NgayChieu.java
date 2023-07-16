package fa.training.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class NgayChieu {
	String maPhim;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate ngayChieu;
	public String getMaPhim() {
		return maPhim;
	}
	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}
	public LocalDate getNgayChieu() {
		return ngayChieu;
	}
	public void setNgayChieu(LocalDate ngayChieu) {
		this.ngayChieu = ngayChieu;
	}
	public NgayChieu(String maPhim, LocalDate ngayChieu) {
		super();
		this.maPhim = maPhim;
		this.ngayChieu = ngayChieu;
	}
	public NgayChieu() {
		super();
	}
	
	
}
