package com.premiumpack.web.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class ProductUpdateRq {

    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String description;

    @Min(value = 0, message = ERROR_VALID_NOTNULL)
    private Integer quantity;

    @Min(value = 0, message = ERROR_VALID_NOTNULL)
    private BigDecimal price;

}
