package com.manageme.app.service.mapper;

import com.manageme.app.domain.*;
import com.manageme.app.service.dto.DepartmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Department and its DTO DepartmentDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, LocationMapper.class})
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {

    @Mapping(source = "head.id", target = "headId")
    @Mapping(source = "representative.id", target = "representativeId")
    @Mapping(target = "representativeName", expression = "java(department.getRepresentative().getUser().getName())")
    @Mapping(source = "location.id", target = "locationId")
    DepartmentDTO toDto(Department department);

    @Mapping(source = "headId", target = "head")
    @Mapping(source = "representativeId", target = "representative")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "employees", ignore = true)
    Department toEntity(DepartmentDTO departmentDTO);

    default Department fromId(Long id) {
        if (id == null) {
            return null;
        }
        Department department = new Department();
        department.setId(id);
        return department;
    }
}
