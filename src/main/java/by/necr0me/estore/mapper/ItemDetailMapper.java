package by.necr0me.estore.mapper;

import by.necr0me.estore.dto.item_detail.ItemDetailDto;
import by.necr0me.estore.entity.ItemDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemDetailMapper {
    ItemDetailDto toDto(ItemDetail itemDetail);
    ItemDetail fromDto(ItemDetailDto itemDetailDto);
    List<ItemDetailDto> toDto(List<ItemDetail> itemDetails);
    List<ItemDetail> fromDto(List<ItemDetailDto> itemDetailDtos);
}
