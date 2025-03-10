package com.skillboostfootball.backend_payments_springboot.application.service_portIn.payments;

import com.skillboostfootball.backend_payments_springboot.application.dto.payments.PaymentRequest;
import reactor.core.publisher.Mono;
import java.util.Map;

public interface PaymentService {
    
    Mono<Map<String, Object>> initiatePayment(PaymentRequest paymentRequest);
    Mono<Map<String, Object>> capturePayment(String orderId);

}
