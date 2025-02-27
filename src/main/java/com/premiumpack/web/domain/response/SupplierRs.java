package com.premiumpack.web.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRs {

    private UUID uuid;

    private String name;

    private String address;

    private String email;

    private String phone;
}
