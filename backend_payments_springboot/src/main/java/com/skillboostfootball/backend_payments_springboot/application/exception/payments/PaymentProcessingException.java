package com.skillboostfootball.backend_payments_springboot.application.exception.payments;

import com.skillboostfootball.backend_payments_springboot.application.exception.BusinessException;

public class PaymentProcessingException extends BusinessException {
    public PaymentProcessingException(String message) {
        super(message);
    }
}
