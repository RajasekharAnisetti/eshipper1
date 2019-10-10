package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ApPayeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApPayee} and its DTO {@link ApPayeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApPayeeMapper extends EntityMapper<ApPayeeDTO, ApPayee> {


    @Mapping(target = "apPayables", ignore = true)
    @Mapping(target = "removeApPayable", ignore = true)
    @Mapping(target = "apPayableCreditNotesTrans", ignore = true)
    @Mapping(target = "removeApPayableCreditNotesTrans", ignore = true)
    ApPayee toEntity(ApPayeeDTO apPayeeDTO);

    default ApPayee fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApPayee apPayee = new ApPayee();
        apPayee.setId(id);
        return apPayee;
    }
}
