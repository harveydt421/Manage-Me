package com.manageme.app.repository;

import com.manageme.app.domain.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findAllByUserLogin(String login);

}
