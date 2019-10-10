package com.eshipper.repository;
import com.eshipper.domain.WoPaymentMethod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoPaymentMethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoPaymentMethodRepository extends JpaRepository<WoPaymentMethod, Long> {

}
