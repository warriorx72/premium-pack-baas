package com.premiumpack.web.domain.response;

import com.premiumpack.web.domain.ProductBase;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

import static com.premiumpack.web.crosscutting.constants.I18Constants.ERROR_VALID_NOTNULL;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRs extends ProductBase {

    private String description;

    private Integer quantity;

    private BigDecimal price;

    private UUID idSupplier;

    private String nameSupplier;

    @Builder(builderMethodName = "superBuilder")
    public ProductRs(UUID uuid, String name, String description, Integer quantity, BigDecimal price, UUID idSupplier, String nameSupplier) {
        super(uuid, name);
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.idSupplier = idSupplier;
        this.nameSupplier = nameSupplier;
    }
}
