package com.premiumpack.web.entrypoint.service;
import com.premiumpack.web.domain.ProductBase;
import com.premiumpack.web.domain.request.ProductRq;
import com.premiumpack.web.domain.request.ProductUpdateRq;
import com.premiumpack.web.domain.response.ProductRs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    ProductBase addProduct(ProductRq request);

    Page<ProductRs> getProducts(Pageable pageable);

    ProductBase deleteProduct(UUID uuid);

    ProductRs updateProduct(UUID uuid, ProductUpdateRq request);

}
