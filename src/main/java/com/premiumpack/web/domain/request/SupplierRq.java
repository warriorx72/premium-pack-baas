package com.premiumpack.web.domain.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_EMAIL;
import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_NOTNULL;
import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_PHONE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRq {

    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String name;

    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String address;

    @Email(message = ERROR_VALID_EMAIL)
    private String email;

    @Min(value = 10, message = ERROR_VALID_PHONE)
    private String phone;
}
