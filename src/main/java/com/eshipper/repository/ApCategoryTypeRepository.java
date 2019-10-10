package com.eshipper.repository;
import com.eshipper.domain.ApCategoryType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApCategoryType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApCategoryTypeRepository extends JpaRepository<ApCategoryType, Long> {

}
