package com.premiumpack.web.entrypoint.service.impl;
import com.premiumpack.web.crosscutting.mapper.ProductMapper;
import com.premiumpack.web.dataprovider.jpa.entity.ProductEntity;
import com.premiumpack.web.dataprovider.jpa.entity.SupplierEntity;
import com.premiumpack.web.dataprovider.jpa.repository.ProductRepository;
import com.premiumpack.web.dataprovider.jpa.repository.SupplierRepository;
import com.premiumpack.web.domain.ProductBase;
import com.premiumpack.web.domain.request.ProductRq;
import com.premiumpack.web.domain.request.ProductUpdateRq;
import com.premiumpack.web.domain.response.ProductRs;
import com.premiumpack.web.entrypoint.exception.NotFoundException;
import com.premiumpack.web.entrypoint.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    @Override
    public ProductBase addProduct(ProductRq request) {
        SupplierEntity supplierEntity = supplierRepository.findByUuid(request.getIdSupplier()).orElseThrow(() -> new NotFoundException("Supplier not found"));
        ProductEntity productEntity = ProductMapper.INSTANCE.toProductEntity(request);
        productEntity.setSupplier(supplierEntity);
        productRepository.save(productEntity);
        return ProductMapper.INSTANCE.toProductBase(productEntity);
    }
    @Override
    public Page<ProductRs> getProducts(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findAll(pageable);
        return products.map(ProductMapper.INSTANCE::toProductRs);
    }

    @Override
    public ProductBase deleteProduct(UUID uuid) {
        ProductEntity productDeleted = productRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Product not found"));
        productRepository.delete(productDeleted);
        return ProductMapper.INSTANCE.toProductBase(productDeleted);
    }

    @Override
    public ProductRs updateProduct(UUID uuid, ProductUpdateRq request) {
        ProductEntity productEntity = productRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Product not found"));
        ProductMapper.INSTANCE.updateProductFromDto(request, productEntity);
        productRepository.save(productEntity);
        return ProductMapper.INSTANCE.toProductRs(productEntity);
    }
}
