package by.necr0me.estore.service.impl;

import by.necr0me.estore.dto.item_detail.ItemDetailDto;
import by.necr0me.estore.entity.ItemDetail;
import by.necr0me.estore.exception.EntityNotFoundException;
import by.necr0me.estore.mapper.ItemDetailMapper;
import by.necr0me.estore.repository.ItemDetailRepository;
import by.necr0me.estore.service.ItemDetailService;
import org.springframework.stereotype.Service;

@Service
public class ItemDetailServiceImpl implements ItemDetailService {
    private final ItemDetailRepository itemDetailRepository;

    private final ItemDetailMapper itemDetailMapper;

    public ItemDetailServiceImpl(ItemDetailRepository itemDetailRepository, ItemDetailMapper itemDetailMapper) {
        this.itemDetailRepository = itemDetailRepository;
        this.itemDetailMapper = itemDetailMapper;
    }

    @Override
    public ItemDetailDto read(Long id) {
        ItemDetail itemDetail = itemDetailRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "ItemDetail", "id", id
        ));
        return itemDetailMapper.toDto(itemDetail);
    }

    @Override
    public ItemDetailDto create(ItemDetailDto itemDto) {
        ItemDetail itemDetail = itemDetailRepository.save(itemDetailMapper.fromDto(itemDto));
        return itemDetailMapper.toDto(itemDetail);
    }

    @Override
    public ItemDetailDto update(ItemDetailDto itemDetailDto) {
        if(!itemDetailRepository.existsById(itemDetailDto.getId())) {
            throw new EntityNotFoundException("ItemDetail", "id", itemDetailDto.getId());
        }
        ItemDetail savedItemDetail = itemDetailRepository.save(itemDetailMapper.fromDto(itemDetailDto));
        return itemDetailMapper.toDto(savedItemDetail);
    }

    @Override
    public void delete(Long id) {
        ItemDetail itemDetail = itemDetailRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "ItemDetail", "id", id
        ));
        itemDetailRepository.delete(itemDetail);
    }
}
