package by.necr0me.estore.util;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertToErrors {
    public static List<Map<String, String>> fromErrorsList(List<ObjectError> errors) {
        List<Map<String, String>> result = new ArrayList<>();
        errors.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            result.add(getError(fieldName, errorMessage));
        });

        return result;
    }

    public static List<Map<String, String>> fromPSQLExceptionMessage(String message) {
        List<Map<String, String>> result = new ArrayList<>();
        Matcher expresionMatcher = Pattern.compile("\\([^\\[\\]()]+\\)=\\([^\\[\\]()]+\\)").matcher(message);

        if (expresionMatcher.find()) {
            Matcher fieldMatcher = Pattern.compile("[^\\[\\]()]+").matcher(expresionMatcher.group());
            if (fieldMatcher.find()) {
                result.add(getError(fieldMatcher.group(), "already exists")); // todo fix hardcode later
            }
        }

        return result;
    }

    private static Map<String, String> getError(String fieldName, String errorMessage) {
        return new HashMap<>() {
            {
                put("pointer", "#/" + fieldName);
                put("detail", errorMessage);
            }
        };
    }
}
