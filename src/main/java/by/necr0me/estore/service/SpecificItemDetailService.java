package by.necr0me.estore.service;

import by.necr0me.estore.dto.specific_item_detail.SpecificItemDetailDto;

import java.util.List;

public interface SpecificItemDetailService {
    List<SpecificItemDetailDto> createMultiple(List<SpecificItemDetailDto> details);
}
