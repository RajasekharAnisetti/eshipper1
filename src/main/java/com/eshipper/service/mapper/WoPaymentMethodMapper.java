package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.WoPaymentMethodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoPaymentMethod} and its DTO {@link WoPaymentMethodDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoPaymentMethodMapper extends EntityMapper<WoPaymentMethodDTO, WoPaymentMethod> {


    @Mapping(target = "apPayables", ignore = true)
    @Mapping(target = "removeApPayable", ignore = true)
    WoPaymentMethod toEntity(WoPaymentMethodDTO woPaymentMethodDTO);

    default WoPaymentMethod fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoPaymentMethod woPaymentMethod = new WoPaymentMethod();
        woPaymentMethod.setId(id);
        return woPaymentMethod;
    }
}
