package com.premiumpack.web.entrypoint.controller;
import com.premiumpack.web.domain.ProductBase;
import com.premiumpack.web.domain.request.ProductRq;
import com.premiumpack.web.domain.request.ProductUpdateRq;
import com.premiumpack.web.domain.response.ProductRs;
import com.premiumpack.web.entrypoint.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductBase> addProduct(@RequestBody @Valid ProductRq request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<ProductRs>> getProduct(Pageable pageable) {
        return ResponseEntity.ok(productService.getProducts(pageable));
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductBase> deleteProduct(@PathVariable UUID uuid) {
        return ResponseEntity.ok(productService.deleteProduct(uuid));
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductRs> updateProduct(@PathVariable UUID uuid, @RequestBody @Valid ProductUpdateRq request) {
        return ResponseEntity.ok(productService.updateProduct(uuid, request));
    }
}
