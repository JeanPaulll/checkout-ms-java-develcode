package com.develcode.checkout_ms_java.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestModel implements Serializable {
    private Long orderId;
    private Double amount;
}
