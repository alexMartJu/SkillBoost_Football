package com.skillboostfootball.backend_payments_springboot.presentation.assembler.payments;

import com.skillboostfootball.backend_payments_springboot.application.dto.payments.PaymentProcessResponse;
import com.skillboostfootball.backend_payments_springboot.application.dto.payments.PayPalCaptureResponse;

import lombok.RequiredArgsConstructor;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentAssembler {

    public PaymentProcessResponse toProcessResponse(Map<String, Object> paymentData) {
        return new PaymentProcessResponse(
            (String) paymentData.get("id"),
            (String) paymentData.get("clientSecret")
        );
    }

    public PayPalCaptureResponse toCaptureResponse(Map<String, Object> captureData) {
        return new PayPalCaptureResponse(
            (String) captureData.get("id"),
            (String) captureData.get("status")
        );
    }
    
}
