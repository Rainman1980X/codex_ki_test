package xmlframework;

import java.util.HashMap;
import java.util.Map;

public final class NullValues {
    private static final Map<Class<?>, Object> DEFAULTS = new HashMap<>();

    static {
        DEFAULTS.put(String.class, "");
        DEFAULTS.put(Integer.class, 0);
        DEFAULTS.put(int.class, 0);
        DEFAULTS.put(Long.class, 0L);
        DEFAULTS.put(long.class, 0L);
        DEFAULTS.put(Double.class, 0.0);
        DEFAULTS.put(double.class, 0.0);
        DEFAULTS.put(Boolean.class, false);
        DEFAULTS.put(boolean.class, false);
    }

    private NullValues() {}

    @SuppressWarnings("unchecked")
    public static <T> T of(Class<T> type) {
        return (T) DEFAULTS.get(type);
    }
}
