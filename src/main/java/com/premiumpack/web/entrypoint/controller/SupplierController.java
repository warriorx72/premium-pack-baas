package com.premiumpack.web.entrypoint.controller;

import com.premiumpack.web.domain.request.SupplierRq;
import com.premiumpack.web.domain.response.SupplierRs;
import com.premiumpack.web.domain.response.SupplierSavedRs;
import com.premiumpack.web.entrypoint.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/supplier")
@CrossOrigin(origins = "*")
public class SupplierController {

    private final SupplierService supplierService;
    private final PagedResourcesAssembler<SupplierRs> assembler;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SupplierSavedRs> addSupplier(@RequestBody @Valid SupplierRq request) {
        return ResponseEntity.ok(supplierService.addSupplier(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<SupplierRs>> getSuppliers(Pageable pageable) {
        return ResponseEntity.ok(supplierService.getSuppliers(pageable));
    }

}
