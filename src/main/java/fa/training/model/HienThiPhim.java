package fa.training.model;

import java.time.LocalTime;

/**
 * Class hiển thị thông tin 
 * Proeject: Cinema WebApp
 */
public class HienThiPhim {
	String maPhim;
	String tenPhim;
	String moTaPhim;
	String noiDungPhim;
	String daoDien;
	String ngayKhoiChieu;
	String ngayKetThuc;
	LocalTime thoiLuong;
	String poster;

	public HienThiPhim() {
		super();
	}

	public HienThiPhim(String maPhim, String tenPhim, String moTaPhim,String noiDungPhim, String daoDien, String ngayKhoiChieu,
			String ngayKetThuc, LocalTime thoiLuong, String poster) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.moTaPhim = moTaPhim;
		this.noiDungPhim = noiDungPhim;
		this.daoDien = daoDien;
		this.ngayKhoiChieu = ngayKhoiChieu;
		this.ngayKetThuc = ngayKetThuc;
		this.thoiLuong = thoiLuong;
		this.poster = poster;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	public String getMoTaPhim() {
		return moTaPhim;
	}

	public void setMoTaPhim(String moTaPhim) {
		this.moTaPhim = moTaPhim;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public String getNgayKhoiChieu() {
		return ngayKhoiChieu;
	}

	public void setNgayKhoiChieu(String ngayKhoiChieu) {
		this.ngayKhoiChieu = ngayKhoiChieu;
	}

	public String getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(String ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public LocalTime getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(LocalTime thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getNoiDungPhim() {
		return noiDungPhim;
	}

	public void setNoiDungPhim(String noiDungPhim) {
		this.noiDungPhim = noiDungPhim;
	}
}