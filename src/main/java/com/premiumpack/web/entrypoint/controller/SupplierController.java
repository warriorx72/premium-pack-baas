package com.premiumpack.web.entrypoint.controller;

import com.premiumpack.web.domain.request.SupplierRq;
import com.premiumpack.web.domain.response.SupplierRs;
import com.premiumpack.web.domain.SupplierBased;
import com.premiumpack.web.entrypoint.service.SupplierService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/supplier")
@CrossOrigin(origins = "*")
public class SupplierController {

    private final SupplierService supplierService;
    private final PagedResourcesAssembler<SupplierRs> assembler;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SupplierBased> addSupplier(@RequestBody @Valid SupplierRq request) {
        return ResponseEntity.ok(supplierService.addSupplier(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<SupplierRs>> getSuppliers(Pageable pageable) {
        return ResponseEntity.ok(supplierService.getSuppliers(pageable));
    }

    @GetMapping("/find")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SupplierRs>> getSupplierByName(@RequestParam @Size(min = 3) String name) {
        return ResponseEntity.ok(supplierService.getSuppliersByName(name));
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SupplierBased> deleteSupplier(@PathVariable UUID uuid) {
        return ResponseEntity.ok(supplierService.deleteSupplier(uuid));
    }

}
