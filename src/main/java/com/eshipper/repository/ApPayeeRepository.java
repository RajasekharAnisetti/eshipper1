package com.eshipper.repository;
import com.eshipper.domain.ApPayee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApPayee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApPayeeRepository extends JpaRepository<ApPayee, Long> {

}
