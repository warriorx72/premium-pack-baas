package com.premiumpack.web.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierRs extends SupplierBased {

    private String address;

    private String email;

    private String phone;

    @Builder(builderMethodName = "superBuilder")
    public SupplierRs(UUID uuid, String name, String address, String email, String phone) {
        super(uuid, name);
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

}
