	/*
	 * Project: Cinema WebApp
	 * Team: 2
	 * Author : Ducnm74
	 * Controller: xử lý thanh toán VNPay
	 */
package fa.training.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fa.training.config.Config;
import fa.training.entities.DichVu;
import fa.training.entities.KhachHang;
import fa.training.entities.KhuyenMai;
import fa.training.entities.SuDungDichVu;
import fa.training.service.DichVuService;
import fa.training.service.KhachHangService;
import fa.training.service.KhuyenMaiService;
import fa.training.service.SuDungDichVuService;
import fa.training.service.SuatChieuService;
import fa.training.service.VeService;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {
	@Autowired
	VeService veService;

	@Autowired
	KhachHangService khachHangService;
	
	@Autowired 
	KhuyenMaiService khuyenMaiService;
	
	@Autowired
	SuDungDichVuService suDungDichVuService;
	
	@Autowired
	DichVuService dichVuService;
	
	@Autowired
	SuatChieuService suatChieuService;

	/*
	 * Project: Cinema WebApp 
	 * Method: Tạo đơn thanh toán
	 */
	@GetMapping("/create")
	public String createPayment(@RequestParam("param1") String soTien, @RequestParam("param2") String orderInfo)
			throws UnsupportedEncodingException {
		long amount = Long.parseLong(soTien) * 100;
		String vnp_TxnRef = Config.getRandomNumber(8);
		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", Config.vnp_Version);
		vnp_Params.put("vnp_Command", Config.vnp_Command);
		vnp_Params.put("vnp_TmnCode", Config.vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");
		vnp_Params.put("vnp_BankCode", "NCB");
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", orderInfo);
		vnp_Params.put("vnp_Locale", "vn");
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
//        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
		try {
			// Tạo một ObjectMapper
			ObjectMapper objectMapper = new ObjectMapper();

			// Đọc chuỗi JSON thành JsonNode
			JsonNode jsonNode = objectMapper.readTree(orderInfo);

			// Lấy giá trị từ JsonNode
			String[] maGhe = objectMapper.convertValue(jsonNode.get("maGhe"), String[].class);
			int maSuatChieu = jsonNode.get("maSuatChieu").asInt();
			int maKhachHang = jsonNode.get("maKhachHang").asInt();
			LocalDate ngaySuDung = LocalDate.now();
				for (String string : maGhe) {
					veService.updateDatVe(string, maSuatChieu,ngaySuDung,maKhachHang);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
		return "redirect:" + paymentUrl;
	}

	/*
	 * Project: Cinema WebApp
	 * Method: Lấy thông tin thanh toán và xử lý lưu dữ liệu vào database
	 */
	@GetMapping("/return")
	public String getPaymentInfo(@RequestParam(value = "vnp_Amount") String amount,
			@RequestParam(value = "vnp_ResponseCode") String status,
			@RequestParam(value = "vnp_OrderInfo") String orderInfo, Model model) {
		int maSuatChieu = 0;
		String ghe = "";
		try {
			// Tạo một ObjectMapper
			ObjectMapper objectMapper = new ObjectMapper();

			// Đọc chuỗi JSON thành JsonNode
			JsonNode jsonNode = objectMapper.readTree(orderInfo);

			// Lấy giá trị từ JsonNode
			maSuatChieu = jsonNode.get("maSuatChieu").asInt();
			LocalDate ngaySuDung = LocalDate.now();
			LocalTime gioSuDung = LocalTime.now();
			int maKhachHang = jsonNode.get("maKhachHang").asInt();
			String maKhuyenMai = jsonNode.get("maKhuyenMai").asText();
			String[] maGhe = objectMapper.convertValue(jsonNode.get("maGhe"), String[].class);
			JsonNode danhSachDichVu = jsonNode.get("danhSachDichVu");
			if (status.equals("00")) {
				KhachHang kh = khachHangService.findById(maKhachHang);
				KhuyenMai km = khuyenMaiService.findById(maKhuyenMai);
				model.addAttribute("soTien", Integer.parseInt(amount) / 100);
				model.addAttribute("tinhTrang", "thanh toan thanh cong");
				model.addAttribute("ngayThanhToan", ngaySuDung);
				model.addAttribute("maGhe", maGhe);
				model.addAttribute("maSuatChieu", maSuatChieu);
				model.addAttribute("tenPhim", suatChieuService.findById(maSuatChieu).getPhim().getTenPhim());
				List<SuDungDichVu> listSD = new ArrayList<SuDungDichVu>();
				for (String string : maGhe) {
					veService.updateDatVe(string, maSuatChieu,ngaySuDung,maKhachHang);
				}
				for (JsonNode jsonNode2 : danhSachDichVu) {
					 String maDichVu = jsonNode2.get("maDichVu").asText();
		                int soLuong = jsonNode2.get("soLuong").asInt();
		            	DichVu dv = dichVuService.findById(maDichVu);
		                SuDungDichVu sddv = new SuDungDichVu(kh, dv, km, ngaySuDung, gioSuDung, soLuong);
		                suDungDichVuService.save(sddv);
		                listSD.add(sddv);
				}
				model.addAttribute("sddv", listSD);
				model.addAttribute("khuyenMai", km);
			} else {
				for (String string : maGhe) {
					veService.updateHuyVe(string, maSuatChieu,ngaySuDung,maKhachHang);
				}
				model.addAttribute("tinhTrang", "thanh toan khong thanh cong");
				return "/ve/" + maSuatChieu;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/ve/bill";
	}
}
