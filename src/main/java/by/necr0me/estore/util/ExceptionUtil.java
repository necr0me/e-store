package by.necr0me.estore.util;

public class ExceptionUtil {
    public static Throwable getRootException(Throwable throwable) {
        Throwable cause = throwable;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }

        return cause;
    }
}
