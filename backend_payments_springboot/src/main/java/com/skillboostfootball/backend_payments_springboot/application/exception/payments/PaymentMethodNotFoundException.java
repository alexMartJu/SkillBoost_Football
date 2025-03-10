package com.skillboostfootball.backend_payments_springboot.application.exception.payments;

import com.skillboostfootball.backend_payments_springboot.application.exception.ResourceNotFoundException;

public class PaymentMethodNotFoundException extends ResourceNotFoundException {
    public PaymentMethodNotFoundException() {
        super("Payment method not found or not supported");
    }
}
