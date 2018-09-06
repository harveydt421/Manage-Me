package com.manageme.app.service.mapper;

import com.manageme.app.domain.*;
import com.manageme.app.service.dto.SeparationApplicationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SeparationApplication and its DTO SeparationApplicationDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface SeparationApplicationMapper extends EntityMapper<SeparationApplicationDTO, SeparationApplication> {

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(target = "employeeName", expression = "java(separationApplication.getEmployee().getUser().getName())")
    SeparationApplicationDTO toDto(SeparationApplication separationApplication);

    @Mapping(source = "employeeId", target = "employee")
    @Mapping(target = "lineItems", ignore = true)
    SeparationApplication toEntity(SeparationApplicationDTO separationApplicationDTO);

    default SeparationApplication fromId(Long id) {
        if (id == null) {
            return null;
        }
        SeparationApplication separationApplication = new SeparationApplication();
        separationApplication.setId(id);
        return separationApplication;
    }
}
