package com.manageme.app.service.mapper;

import com.manageme.app.domain.*;
import com.manageme.app.service.dto.LineItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LineItem and its DTO LineItemDTO.
 */
@Mapper(componentModel = "spring", uses = {AssetMapper.class, SeparationApplicationMapper.class})
public interface LineItemMapper extends EntityMapper<LineItemDTO, LineItem> {

    @Mapping(source = "assetOwed.id", target = "assetOwedId")
    @Mapping(source = "assetOwed.name", target = "assetOwedName")
    @Mapping(source = "separationApplication.id", target = "separationApplicationId")
    LineItemDTO toDto(LineItem lineItem);

    @Mapping(source = "assetOwedId", target = "assetOwed")
    @Mapping(source = "separationApplicationId", target = "separationApplication")
    LineItem toEntity(LineItemDTO lineItemDTO);

    default LineItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        LineItem lineItem = new LineItem();
        lineItem.setId(id);
        return lineItem;
    }
}
