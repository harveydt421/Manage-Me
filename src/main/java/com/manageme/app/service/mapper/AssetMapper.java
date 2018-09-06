package com.manageme.app.service.mapper;

import com.manageme.app.domain.*;
import com.manageme.app.service.dto.AssetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Asset and its DTO AssetDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface AssetMapper extends EntityMapper<AssetDTO, Asset> {

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(target = "employeeName", expression = "java(asset.getEmployee().getUser().getName())")
    AssetDTO toDto(Asset asset);

    @Mapping(source = "employeeId", target = "employee")
    Asset toEntity(AssetDTO assetDTO);

    default Asset fromId(Long id) {
        if (id == null) {
            return null;
        }
        Asset asset = new Asset();
        asset.setId(id);
        return asset;
    }
}
