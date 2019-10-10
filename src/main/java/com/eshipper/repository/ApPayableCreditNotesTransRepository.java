package com.eshipper.repository;
import com.eshipper.domain.ApPayableCreditNotesTrans;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApPayableCreditNotesTrans entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApPayableCreditNotesTransRepository extends JpaRepository<ApPayableCreditNotesTrans, Long> {

}
