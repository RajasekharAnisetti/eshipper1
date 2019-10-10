package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ApPayableCreditNotesTransDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApPayableCreditNotesTrans} and its DTO {@link ApPayableCreditNotesTransDTO}.
 */
@Mapper(componentModel = "spring", uses = {ApPayableMapper.class, ApPayeeMapper.class})
public interface ApPayableCreditNotesTransMapper extends EntityMapper<ApPayableCreditNotesTransDTO, ApPayableCreditNotesTrans> {

    @Mapping(source = "apPayable.id", target = "apPayableId")
    @Mapping(source = "apPayee.id", target = "apPayeeId")
    @Mapping(source = "apPayable.id", target = "apPayableId")
    ApPayableCreditNotesTransDTO toDto(ApPayableCreditNotesTrans apPayableCreditNotesTrans);

    @Mapping(source = "apPayableId", target = "apPayable")
    @Mapping(source = "apPayeeId", target = "apPayee")
    @Mapping(source = "apPayableId", target = "apPayable")
    ApPayableCreditNotesTrans toEntity(ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO);

    default ApPayableCreditNotesTrans fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApPayableCreditNotesTrans apPayableCreditNotesTrans = new ApPayableCreditNotesTrans();
        apPayableCreditNotesTrans.setId(id);
        return apPayableCreditNotesTrans;
    }
}
