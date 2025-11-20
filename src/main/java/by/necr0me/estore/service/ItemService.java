package by.necr0me.estore.service;

import by.necr0me.estore.dto.item.ItemDto;
import by.necr0me.estore.entity.Item;
import org.springframework.data.domain.Slice;

public interface ItemService {
    Slice<Item> readAll(int page);

    ItemDto read(long id);

    ItemDto create(ItemDto item);

    ItemDto update(ItemDto item);

    void delete(long id);
}
