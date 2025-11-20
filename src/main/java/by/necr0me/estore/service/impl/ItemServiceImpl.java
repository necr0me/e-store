package by.necr0me.estore.service.impl;

import by.necr0me.estore.dto.item.ItemDto;
import by.necr0me.estore.entity.Item;
import by.necr0me.estore.exception.EntityNotFoundException;
import by.necr0me.estore.mapper.ItemMapper;
import by.necr0me.estore.repository.ItemRepository;
import by.necr0me.estore.service.ItemService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public Slice<Item> readAll(int page) {
        return itemRepository.findAll(PageRequest.of(page, 10));
    }

    @Override
    public ItemDto read(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            "Item", "id", id // todo: i18n for such errors
        ));
        return itemMapper.toDto(item);
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        Item item = itemRepository.save(itemMapper.fromDto(itemDto));
        return itemMapper.toDto(item);
    }

    @Override
    public ItemDto update(ItemDto itemDto) {
        if(!itemRepository.existsById(itemDto.getId())) {
            throw new EntityNotFoundException("Item", "id", itemDto.getId());
        }
        Item savedItem = itemRepository.save(itemMapper.fromDto(itemDto));

        return itemMapper.toDto(savedItem);
    }

    @Override
    public void delete(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item", "id", String.valueOf(id)));
        itemRepository.delete(item);
    }
}
