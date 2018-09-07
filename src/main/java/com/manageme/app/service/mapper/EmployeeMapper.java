package com.manageme.app.service.mapper;

import com.manageme.app.domain.*;
import com.manageme.app.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Employee and its DTO EmployeeDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, DepartmentMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(target = "name", expression = "java(employee.getUser().getName())")
    @Mapping(target = "authorities", expression = "java(employee.getUser().getAuthorities().stream().map(com.manageme.app.domain.Authority::getName).collect(java.util.stream.Collectors.toSet()))")
    EmployeeDTO toDto(Employee employee);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "assets", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "separationApplication", ignore = true)
    @Mapping(source = "departmentId", target = "department")
    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
