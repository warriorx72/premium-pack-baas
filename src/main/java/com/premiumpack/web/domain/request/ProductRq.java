package com.premiumpack.web.domain.request;
import com.premiumpack.web.domain.request.SupplierRq;
import com.premiumpack.web.domain.response.SupplierRs;
import com.premiumpack.web.entrypoint.service.SupplierService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_NOTNULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRq {

    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String name;

    @NotBlank(message = ERROR_VALID_NOTNULL)
    private String description;

    @Min(value = 0, message = ERROR_VALID_NOTNULL)
    private Integer quantity;

    @Min(value = 0, message = ERROR_VALID_NOTNULL)
    private BigDecimal price;

    @NotNull(message = ERROR_VALID_NOTNULL)
    private UUID idSupplier;

}
