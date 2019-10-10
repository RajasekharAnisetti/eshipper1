package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ApCategoryTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApCategoryType} and its DTO {@link ApCategoryTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApCategoryTypeMapper extends EntityMapper<ApCategoryTypeDTO, ApCategoryType> {


    @Mapping(target = "apPayables", ignore = true)
    @Mapping(target = "removeApPayable", ignore = true)
    ApCategoryType toEntity(ApCategoryTypeDTO apCategoryTypeDTO);

    default ApCategoryType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApCategoryType apCategoryType = new ApCategoryType();
        apCategoryType.setId(id);
        return apCategoryType;
    }
}
