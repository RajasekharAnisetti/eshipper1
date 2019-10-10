package com.eshipper.repository;
import com.eshipper.domain.ApPayeeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApPayeeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApPayeeTypeRepository extends JpaRepository<ApPayeeType, Long> {

}
