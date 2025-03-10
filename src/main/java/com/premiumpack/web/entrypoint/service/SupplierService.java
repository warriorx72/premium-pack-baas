package com.premiumpack.web.entrypoint.service;

import com.premiumpack.web.domain.request.SupplierRq;
import com.premiumpack.web.domain.SupplierBased;
import com.premiumpack.web.domain.request.SupplierUpdateRq;
import com.premiumpack.web.domain.response.SupplierRs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SupplierService {

    SupplierBased addSupplier(SupplierRq request);

    Page<SupplierRs> getSuppliers(Pageable pageable);

    SupplierBased deleteSupplier(UUID uuid);

    SupplierRs updateSupplier(SupplierUpdateRq request, UUID uuid);

}
