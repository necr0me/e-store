package by.necr0me.estore.service.impl;

import by.necr0me.estore.dto.specific_item_detail.SpecificItemDetailDto;
import by.necr0me.estore.entity.SpecificItemDetail;
import by.necr0me.estore.mapper.SpecificItemDetailMapper;
import by.necr0me.estore.repository.SpecificItemDetailRepository;
import by.necr0me.estore.service.SpecificItemDetailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificItemDetailServiceImpl implements SpecificItemDetailService {
    private final SpecificItemDetailRepository specificItemDetailRepository;

    private final SpecificItemDetailMapper specificItemDetailMapper;

    public SpecificItemDetailServiceImpl(SpecificItemDetailRepository specificItemDetailRepository,
                                         SpecificItemDetailMapper specificItemDetailMapper) {
        this.specificItemDetailRepository = specificItemDetailRepository;
        this.specificItemDetailMapper = specificItemDetailMapper;
    }

    @Override
    public List<SpecificItemDetailDto> createMultiple(List<SpecificItemDetailDto> details) {
        List<SpecificItemDetail> toCreate = fromDtoList(details);
        specificItemDetailRepository.saveAll(toCreate);
        return toDtoList(toCreate);
    }

    private List<SpecificItemDetailDto> toDtoList(List<SpecificItemDetail> specificItemDetails) {
        List<SpecificItemDetailDto> dtoList = new ArrayList<>();
        for (SpecificItemDetail entity : specificItemDetails) {
            dtoList.add(specificItemDetailMapper.toDto(entity));
        }
        return dtoList;
    }

    private List<SpecificItemDetail> fromDtoList(List<SpecificItemDetailDto> specificItemDetailDtos) {
        List<SpecificItemDetail> entityList = new ArrayList<>();
        for (SpecificItemDetailDto dto : specificItemDetailDtos) {
            entityList.add(specificItemDetailMapper.fromDto(dto));
        }
        return entityList;
    }
}
