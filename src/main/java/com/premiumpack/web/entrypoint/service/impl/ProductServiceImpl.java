package com.premiumpack.web.entrypoint.service.impl;
import com.premiumpack.web.crosscutting.mapper.ProductMapper;
import com.premiumpack.web.dataprovider.jpa.entity.ProductEntity;
import com.premiumpack.web.dataprovider.jpa.entity.SupplierEntity;
import com.premiumpack.web.dataprovider.jpa.repository.ProductRepository;
import com.premiumpack.web.dataprovider.jpa.repository.SupplierRepository;
import com.premiumpack.web.domain.request.ProductRq;
import com.premiumpack.web.domain.response.ProductRs;
import com.premiumpack.web.entrypoint.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    @Override
    public ProductRs addProduct(ProductRq request) {
        SupplierEntity supplierEntity = supplierRepository.findByUuid(request.getIdSupplier()).orElse(null);
        ProductEntity productEntity = ProductMapper.INSTANCE.toProductEntity(request);
        productEntity.setSupplier(supplierEntity);
        productRepository.save(productEntity);
        return ProductMapper.INSTANCE.toProductRs(productEntity);
    }
    @Override
    public Page<ProductRs> getProducts(Pageable pageable) {
        return null;
    }
}
