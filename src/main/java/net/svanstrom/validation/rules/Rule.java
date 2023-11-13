package net.svanstrom.validation.rules;

@FunctionalInterface
public interface Rule<T> {
    RuleResult apply(T input);
}
