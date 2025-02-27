package com.premiumpack.web.entrypoint.service;

import com.premiumpack.web.domain.request.SupplierRq;
import com.premiumpack.web.domain.response.SupplierRs;
import com.premiumpack.web.domain.response.SupplierSavedRs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {

    SupplierSavedRs addSupplier(SupplierRq request);

    Page<SupplierRs> getSuppliers(Pageable pageable);
}
