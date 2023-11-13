package net.svanstrom.validation.example;

import net.svanstrom.validation.rules.Rule;
import net.svanstrom.validation.rules.RuleResult;
import net.svanstrom.validation.validators.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FooBarValidator implements Validator<String> {
    Rule<String> fooRule = input -> input.startsWith("foo") ? new RuleResult.Ok() : new RuleResult.Error("Input must start with 'foo'");
    Rule<String> barRule = input -> input.endsWith("bar")? new RuleResult.Ok() : new RuleResult.Error("Input must end with 'bar'");
    private final Set<Rule<String>> ruleSet = Set.of(fooRule, barRule);

    @Override
    public List<RuleResult> validate(String input) {
        return ruleSet.stream().map(rule -> rule.apply(input)).collect(Collectors.toList());
    }
}