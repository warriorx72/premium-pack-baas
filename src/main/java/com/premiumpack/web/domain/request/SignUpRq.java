package com.premiumpack.web.domain.request;

import com.premiumpack.web.domain.RolDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_EMAIL;
import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_NOTNULL;
import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_PASSWORD;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRq {

    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = ERROR_VALID_PASSWORD)
    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String password;

    @Email(message = ERROR_VALID_EMAIL)
    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String email;

    private List<RolDto> roles;
}
