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
    @Mapping(target = "employeeName", expression = "java(fullName(asset.getEmployee()))")
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
    
    default String fullName(Employee employee) {
    	if (employee == null) {
    		return null;
    	}
		
    	User user = employee.getUser();
    	if (user == null) {
    		return null;
    	}
    	
    	return user.getName();
    }
}
