package com.premiumpack.web.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_NOTNULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRq {

    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String customerName;

    @NotBlank(message = ERROR_VALID_NOTNULL)
    @Pattern(regexp = "^[0-9]{10}$", message = "El número de teléfono debe tener 10 dígitos")
    private String phone;

    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String description;
}
