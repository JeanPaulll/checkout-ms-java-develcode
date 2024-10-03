package com.develcode.checkout_ms_java.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Jean Paul - <jeanpaulwebb@gmail.com>
 * @date 03/10/2024
 */
public record OrderRecordDto(
        @NotBlank
        String name,
        @NotNull
        BigDecimal value
) {
}
