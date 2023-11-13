package net.svanstrom.validation.validators;

import net.svanstrom.validation.rules.RuleResult;

import java.util.List;

@FunctionalInterface
public interface Validator<T> {
    List<RuleResult> validate(T input);
}
