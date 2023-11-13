package net.svanstrom.validation;

import net.svanstrom.validation.annotations.Validate;
import net.svanstrom.validation.rules.RuleResult;
import net.svanstrom.validation.validators.Validator;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidationProcessor {

    public record ValidationError(String errorMessage, String name) {
    }

    public List<ValidationError> process(Object object) throws Exception {
        List<ValidationError> errors = new ArrayList<>();
        errors.addAll(processFields(object));
        errors.addAll(processMethods(object));
        return errors;
    }

    private List<ValidationError> processFields(Object object) throws Exception {
        List<ValidationError> errors = new ArrayList<>();
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Validate.class)) {
                Object value = field.get(object);
                Class<? extends Validator<?>> validatorClass = field.getAnnotation(Validate.class).using();
                errors.addAll(runValidation(field.getGenericType(), field.getName(), validatorClass, value));
            }
        }
        return errors;
    }

    private List<ValidationError> processMethods(Object object) throws Exception {
        List<ValidationError> errors = new ArrayList<>();
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(Validate.class)) {
                Object value = method.invoke(object);
                Class<? extends Validator<?>> validatorClass = method.getAnnotation(Validate.class).using();
                errors.addAll(runValidation(method.getReturnType(), method.getName(), validatorClass, value));
            }
        }
        return errors;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private List<ValidationError> runValidation(Type dataType, String name, Class<? extends Validator<?>> validatorClass, Object value) throws Exception {
        Validator validatorToUse = validatorClass.getDeclaredConstructor().newInstance();
        ParameterizedType t = (ParameterizedType) validatorClass.getGenericInterfaces()[0];
        Type validatorType = t.getActualTypeArguments()[0];
        if (!validatorType.equals(dataType)) {
            throw new IllegalArgumentException(
                    String.format(
                            "Cannot annotate %s with %s, wrong type (expected %s)!",
                            name,
                            validatorClass.getName(),
                            validatorType.getTypeName())
            );
        }
        List<RuleResult> results = validatorToUse.validate(value);
        return results.stream().filter(r -> !r.isValid()).map(r -> new ValidationError(r.getErrorMessage(), name)).collect(Collectors.toList());
    }
}
