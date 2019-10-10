package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ApPayeeTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApPayeeType} and its DTO {@link ApPayeeTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApPayeeTypeMapper extends EntityMapper<ApPayeeTypeDTO, ApPayeeType> {


    @Mapping(target = "apPayables", ignore = true)
    @Mapping(target = "removeApPayable", ignore = true)
    ApPayeeType toEntity(ApPayeeTypeDTO apPayeeTypeDTO);

    default ApPayeeType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApPayeeType apPayeeType = new ApPayeeType();
        apPayeeType.setId(id);
        return apPayeeType;
    }
}
