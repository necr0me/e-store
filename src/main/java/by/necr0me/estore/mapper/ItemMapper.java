package by.necr0me.estore.mapper;

import by.necr0me.estore.dto.item.ItemDto;
import by.necr0me.estore.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toDto(Item item);
    Item fromDto(ItemDto itemDto);
}
