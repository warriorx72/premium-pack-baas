package com.premiumpack.web.entrypoint.service;
import com.premiumpack.web.domain.request.ProductRq;
import com.premiumpack.web.domain.response.ProductRs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductRs addProduct(ProductRq request);

    Page<ProductRs> getProducts(Pageable pageable);
}
