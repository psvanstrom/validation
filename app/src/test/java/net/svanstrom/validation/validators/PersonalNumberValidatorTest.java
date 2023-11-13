package net.svanstrom.validation.validators;

import net.svanstrom.validation.rules.RuleResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalNumberValidatorTest {

    private final PersonalNumberValidator validator = new PersonalNumberValidator();

    @ParameterizedTest
    @ValueSource(strings = {"19400112-4587", "400112-4587", "19230102+2220", "230102+2220", "194001124587", "4001124587"})
    void validPersonalNumberShouldPass(String pnr) {
        assertTrue(validator.validate(pnr).stream().allMatch(RuleResult::isValid));
    }

    @ParameterizedTest
    @ValueSource(strings = {"19400112-4287", "2232342342342342342", "24001124585"})
    void invalidPersonalNumberShouldFail(String pnr) {
        assertFalse(validator.validate(pnr).stream().allMatch(RuleResult::isValid));
    }
    
    @Test
    void nullPersonalNumberShouldFail() {
        assertFalse(validator.validate(null).stream().allMatch(RuleResult::isValid));
    }
}
