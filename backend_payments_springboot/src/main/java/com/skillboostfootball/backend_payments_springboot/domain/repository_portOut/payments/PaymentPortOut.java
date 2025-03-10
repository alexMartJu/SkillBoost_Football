package com.skillboostfootball.backend_payments_springboot.domain.repository_portOut.payments;

import com.skillboostfootball.backend_payments_springboot.application.dto.payments.PaymentRequest;
import reactor.core.publisher.Mono;
import java.util.Map;

public interface PaymentPortOut {
    
    Mono<Map<String, Object>> processPayment(PaymentRequest paymentRequest);
    Mono<Map<String, Object>> capturePayment(String orderId);

}
