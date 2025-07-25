package xmlframework;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class TypeConverter {
    private static final Map<Class<?>, Function<String, ?>> STRATEGIES = new HashMap<>();

    static {
        STRATEGIES.put(String.class, s -> s);
        STRATEGIES.put(Integer.class, Integer::valueOf);
        STRATEGIES.put(int.class, Integer::valueOf);
        STRATEGIES.put(Long.class, Long::valueOf);
        STRATEGIES.put(long.class, Long::valueOf);
        STRATEGIES.put(Double.class, Double::valueOf);
        STRATEGIES.put(double.class, Double::valueOf);
        STRATEGIES.put(Boolean.class, Boolean::valueOf);
        STRATEGIES.put(boolean.class, Boolean::valueOf);
    }

    private TypeConverter() {}

    public static <T> String toString(T value) {
        return value == null ? "" : value.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromString(String value, Class<T> type) {
        if (value == null) {
            return NullValues.of(type);
        }
        Function<String, ?> func = STRATEGIES.get(type);
        if (func != null) {
            return (T) func.apply(value);
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }

    public static <T> void registerConverter(Class<T> type, Function<String, T> converter) {
        STRATEGIES.put(type, converter);
    }
}
