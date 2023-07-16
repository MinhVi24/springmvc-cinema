package fa.training.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
//	@Value("${vnpay.tmnCode}")
//    private String tmnCode;
//
//    @Value("${vnpay.hashSecretKey}")
//    private String hashSecretKey;
//
//    @Value("${vnpay.returnUrl}")
//    private String returnUrl;
//
//    @Value("${vnpay.cancelUrl}")
//    private String cancelUrl;
//
//    public String createPaymentUrl(PaymentRequest paymentRequest) {
//        VNPayClient vnpayClient = new VNPayClient();
//
//        vnpayClient.setTmnCode(tmnCode);
//        vnpayClient.setHashSecretKey(hashSecretKey);
//        vnpayClient.setReturnUrl(returnUrl);
//        vnpayClient.setCancelUrl(cancelUrl);
//
//        // Thiết lập các thông tin khác của paymentRequest vào vnpayClient
//
//        String paymentUrl = vnpayClient.createPaymentUrl(paymentRequest);
//
//        return paymentUrl;
//    }
//
//    public Map<String, String> extractCallbackParams(Map<String, String[]> requestParams) {
//        VNPayClient vnpayClient = new VNPayClient();
//
//        vnpayClient.setHashSecretKey(hashSecretKey);
//
//        Map<String, String> params = vnpayClient.extractCallbackParams(requestParams);
//
//        return params;
//    }
//
//    public boolean validateCallback(Map<String, String> params) {
//        VNPayClient vnpayClient = new VNPayClient();
//
//        vnpayClient.setTmnCode(tmnCode);
//        vnpayClient.setHashSecretKey(hashSecretKey);
//
//        boolean isValidCallback = vnpayClient.validateCallback(params);
//
//        return isValidCallback;
//    }
}
