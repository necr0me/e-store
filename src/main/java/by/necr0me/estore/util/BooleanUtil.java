package by.necr0me.estore.util;

public class BooleanUtil {
    public static boolean parseBoolean(String s) throws IllegalArgumentException {
        if(s.equalsIgnoreCase("true"))  {
            return true;
        } else if(s.equalsIgnoreCase("false")) {
            return false;
        } else {
            throw new IllegalArgumentException("Invalid value for boolean: " + s);
        }
    }
}
