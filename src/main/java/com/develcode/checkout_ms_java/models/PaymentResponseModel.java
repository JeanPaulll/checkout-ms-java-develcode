package com.develcode.checkout_ms_java.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jean Paul - <jeanpaulwebb@gmail.com>
 * @date 03/10/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseModel implements Serializable {
    private boolean success;
    private String message;
    private String status;
}
