package by.necr0me.estore.dto.specific_item_detail;

import by.necr0me.estore.annotation.ValidateValueType;
import by.necr0me.estore.dto.item_detail.ItemDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ValidateValueType
public class SpecificItemDetailDto {
    private Long id;

    private String value;

    private Long itemId;

    private ItemDetailDto itemDetail;
}
