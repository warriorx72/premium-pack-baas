package com.premiumpack.web.domain.request;

import com.premiumpack.web.crosscutting.enumeration.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_NOTNULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateRq {

    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String description;

    @NotNull(message = ERROR_VALID_NOTNULL)
    private OrderStatus status;

    @NotNull(message = ERROR_VALID_NOTNULL)
    private BigDecimal total;

}
