package by.necr0me.estore.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class ItemDto {
    private long id;

    @NotNull
    @Length(min = 4, max = 64)
    private String name;

    private String description;
}
