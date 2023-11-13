package net.svanstrom.validation.validators;

import net.svanstrom.validation.rules.Rule;
import net.svanstrom.validation.rules.RuleResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static net.svanstrom.validation.rules.CommonRules.*;

public class PersonalNumberValidator implements Validator<String> {

    private final Rule<String> validLength =
            input -> RuleResult.from(input.length() == 10 || input.length() == 12, "Wrong length.");

    private final Set<Rule<String>> ruleSet = Set.of(onlyDigits, validLength, validLuhnCheck);

    @Override
    public List<RuleResult> validate(final String pnr) {
        if (pnr != null) {
            return ruleSet.stream().map(rule -> rule.apply(prunePnr(pnr))).collect(Collectors.toList());    
        } else {
            return List.of(RuleResult.from(false, "Personal number cannot be null"));
        }
    }

    private String prunePnr(final String pnr) {
        String pruned = pnr.replace("-", "").replace("+", "");
        return pruned.length() == 12 ? pruned.substring(2) : pruned;
    }
}
