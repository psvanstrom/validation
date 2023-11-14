package net.svanstrom.validation.rules;

public sealed interface RuleResult permits RuleResult.Ok, RuleResult.Error {
    static final public class Ok implements RuleResult {}
    public record Error(String errorMessage) implements RuleResult {}
}