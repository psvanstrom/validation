package net.svanstrom.validation.validators;

import net.svanstrom.validation.rules.Rule;
import net.svanstrom.validation.rules.RuleResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static net.svanstrom.validation.rules.CommonRules.notBlank;
import static net.svanstrom.validation.rules.CommonRules.onlySwedishAlphabetical;

public class NameValidator implements Validator<String> {
    private final Set<Rule<String>> ruleSet = Set.of(notBlank, onlySwedishAlphabetical);

    @Override
    public List<RuleResult> validate(String name) {
        return ruleSet.stream().map(rule -> rule.apply(name)).collect(Collectors.toList());
    }
}
