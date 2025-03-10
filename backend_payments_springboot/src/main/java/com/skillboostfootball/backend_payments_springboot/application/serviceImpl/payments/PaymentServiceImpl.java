package com.skillboostfootball.backend_payments_springboot.application.serviceImpl.payments;

import com.skillboostfootball.backend_payments_springboot.application.dto.payments.PaymentRequest;
import com.skillboostfootball.backend_payments_springboot.application.exception.payments.PaymentMethodNotFoundException;
import com.skillboostfootball.backend_payments_springboot.application.service_portIn.payments.PaymentService;
import com.skillboostfootball.backend_payments_springboot.domain.repository_portOut.payments.PaymentPortOut;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    
    private final Map<String, PaymentPortOut> paymentProcessors;

    @Override
    public Mono<Map<String, Object>> initiatePayment(PaymentRequest paymentRequest) {
        PaymentPortOut processor = paymentProcessors.get(paymentRequest.getPaymentMethod().toLowerCase());
        if (processor == null) {
            return Mono.error(new PaymentMethodNotFoundException());
        }
        return processor.processPayment(paymentRequest);
    }

    @Override
    public Mono<Map<String, Object>> capturePayment(String orderId) {
        return paymentProcessors.get("paypal").capturePayment(orderId);
    }
}
