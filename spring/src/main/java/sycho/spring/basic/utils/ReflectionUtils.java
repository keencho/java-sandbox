package sycho.spring.basic.utils;

import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionUtils {
    public static Set<Class<? extends Enum>> findEnumClasses(String basePackageName) {
        var reflections = new Reflections(basePackageName);

        var map = reflections
                .getSubTypesOf(Enum.class)
                .stream()
                .map(clazz -> {
                    return clazz;
                })
                .collect(Collectors.toList());

        return reflections.getSubTypesOf(Enum.class);
    }
}
