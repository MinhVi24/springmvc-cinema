/*
 * Project: Cinema WebApp
 * Team: 2
 * Author : Ducnm74
 * Class: Model để xử lý tính toán doanh thu Phim
 */

package fa.training.model;

import javax.enterprise.inject.Model;

@Model
public class PhimDoanhThu {
	String maPhim;
	
	String tenPhim;
	
	long doanhThu;

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

	public long getDoanhThu() {
		return doanhThu;
	}

	public void setDoanhThu(long doanhThu) {
		this.doanhThu = doanhThu;
	}

	public PhimDoanhThu(String maPhim, String tenPhim, long doanhThu) {
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.doanhThu = doanhThu;
	}

	public PhimDoanhThu() {
	}

	@Override
	public String toString() {
		return String.format("PhimDoanhThu [maPhim=%s, tenPhim=%s, doanhThu=%s]", maPhim, tenPhim, doanhThu);
	}
	
	
	
}
