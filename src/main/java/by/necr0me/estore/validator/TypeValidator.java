package by.necr0me.estore.validator;

import by.necr0me.estore.annotation.ValidateValueType;
import by.necr0me.estore.dto.specific_item_detail.SpecificItemDetailDto;
import by.necr0me.estore.entity.enums.item_detail.UnitType;
import by.necr0me.estore.util.BooleanUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TypeValidator implements ConstraintValidator<ValidateValueType, SpecificItemDetailDto> {
    @Override
    public boolean isValid(SpecificItemDetailDto specificItemDetailDto, ConstraintValidatorContext context) {
        String unitType = specificItemDetailDto.getItemDetail().getUnitType();
        String value = specificItemDetailDto.getValue();

        try {
            if(unitType.equals(UnitType.INT.toString())) {
                Integer.parseInt(value);
            } else if(unitType.equals(UnitType.DECIMAL.toString())) {
                Float.parseFloat(value);
            } else if(unitType.equals(UnitType.BOOLEAN.toString())) {
                BooleanUtil.parseBoolean(value);
            }
        } catch (IllegalArgumentException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("type of value is not equal to " + unitType)
                    .addPropertyNode("value")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}