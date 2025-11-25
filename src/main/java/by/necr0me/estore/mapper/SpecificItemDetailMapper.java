package by.necr0me.estore.mapper;

import by.necr0me.estore.dto.specific_item_detail.SpecificItemDetailDto;
import by.necr0me.estore.entity.SpecificItemDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ItemDetailMapper.class)
public interface SpecificItemDetailMapper {
    @Mapping(target = "item.id", source = "specificItemDetailDto.itemId")
    SpecificItemDetail fromDto(SpecificItemDetailDto specificItemDetailDto);
    SpecificItemDetailDto toDto(SpecificItemDetail specificItemDetail);
}
