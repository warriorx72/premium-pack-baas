package com.premiumpack.web.entrypoint.service.impl;

import com.premiumpack.web.crosscutting.mapper.SupplierMapper;
import com.premiumpack.web.dataprovider.jpa.entity.SupplierEntity;
import com.premiumpack.web.dataprovider.jpa.repository.SupplierRepository;
import com.premiumpack.web.domain.SupplierBased;
import com.premiumpack.web.domain.request.SupplierRq;
import com.premiumpack.web.domain.request.SupplierUpdateRq;
import com.premiumpack.web.domain.response.SupplierRs;
import com.premiumpack.web.entrypoint.exception.NotFoundException;
import com.premiumpack.web.entrypoint.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    @Override
    public SupplierBased addSupplier(SupplierRq request) {
        SupplierEntity supplierEntity = SupplierMapper.INSTANCE.toSupplierEntity(request);
        supplierRepository.save(supplierEntity);
        return SupplierMapper.INSTANCE.toSupplierSavedRs(supplierEntity);
    }

    @Override
    public Page<SupplierRs> getSuppliers(Pageable pageable) {
        Page<SupplierEntity> suppliers = supplierRepository.findAll(pageable);
        return suppliers.map(SupplierMapper.INSTANCE::toSupplierRs);
    }

    @Override
    public SupplierBased deleteSupplier(UUID uuid) {
        SupplierEntity supplierDeleted = supplierRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Supplier not found"));
        supplierRepository.delete(supplierDeleted);
        return SupplierMapper.INSTANCE.toSupplierSavedRs(supplierDeleted);
    }

    @Override
    public SupplierRs updateSupplier(SupplierUpdateRq request, UUID uuid) {
        return null;
    }

    @Override
    public List<SupplierRs> getSuppliersByName(String name) {
        return List.of();
    }
}
