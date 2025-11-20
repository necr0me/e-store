package by.necr0me.estore.service;

import by.necr0me.estore.dto.item_detail.ItemDetailDto;

public interface ItemDetailService {
    ItemDetailDto read(Long id);
    ItemDetailDto create(ItemDetailDto itemDetailDto);
    ItemDetailDto update(ItemDetailDto itemDetailDto);
    void delete(Long id);
}
