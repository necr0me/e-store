package by.necr0me.estore.dto.item_detail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class ItemDetailDto {
    private Long id;

    @NotNull
    @Length(min = 3, max = 255)
    private String name;

    @Length(max = 5)
    private String unit;

    @NotNull
    private String unitType;

    @NotNull
    private String comparativeRule;
}
