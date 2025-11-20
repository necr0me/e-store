package by.necr0me.estore.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, String fieldName, Object fieldValue) {
        super(entityName + " with " + fieldName + " = " + fieldValue + " not found");
    }
}
