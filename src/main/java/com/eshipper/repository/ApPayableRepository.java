package com.eshipper.repository;
import com.eshipper.domain.ApPayable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApPayable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApPayableRepository extends JpaRepository<ApPayable, Long> {

}
