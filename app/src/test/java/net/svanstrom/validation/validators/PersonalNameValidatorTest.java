package net.svanstrom.validation.validators;

import net.svanstrom.validation.rules.RuleResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalNameValidatorTest {

    private final NameValidator validator = new NameValidator();

    @ParameterizedTest
    @ValueSource(strings = {"John Doe", "Å Ö", "Åsa Älva Österberg"})
    void validNameShouldPass(String name) {
        assertTrue(validator.validate(name).stream().allMatch(RuleResult::isValid));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ása Ælva Østerberg", "       ", "John 3 Smith"})
    void invalidNameShouldFail(String name) {
        assertFalse(validator.validate(name).stream().allMatch(RuleResult::isValid));
    }
}
