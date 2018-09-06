package com.manageme.app.repository;

import com.manageme.app.domain.SeparationApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SeparationApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeparationApplicationRepository extends JpaRepository<SeparationApplication, Long> {

    @Query(value = "select distinct separation_application from SeparationApplication separation_application left join fetch separation_application.functionalRepresentatives",
        countQuery = "select count(distinct separation_application) from SeparationApplication separation_application")
    Page<SeparationApplication> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct separation_application from SeparationApplication separation_application left join fetch separation_application.functionalRepresentatives")
    List<SeparationApplication> findAllWithEagerRelationships();

    @Query("select separation_application from SeparationApplication separation_application left join fetch separation_application.functionalRepresentatives where separation_application.id =:id")
    Optional<SeparationApplication> findOneWithEagerRelationships(@Param("id") Long id);

}
