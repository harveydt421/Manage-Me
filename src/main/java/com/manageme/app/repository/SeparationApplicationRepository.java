package com.manageme.app.repository;

import com.manageme.app.domain.SeparationApplication;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SeparationApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeparationApplicationRepository extends JpaRepository<SeparationApplication, Long> {

	List<SeparationApplication> findAllByEmployeeUserLogin(String login);

	Optional<SeparationApplication> findOneByIdAndEmployeeUserLogin(Long id, String login);

}
