package net.svanstrom.validation;

import net.svanstrom.validation.annotations.Validate;
import net.svanstrom.validation.validators.NameValidator;
import net.svanstrom.validation.validators.PersonalNumberValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationProcessorTest {

    private final ValidationProcessor processor = new ValidationProcessor();

    @Test
    public void processorShouldValidateFields() throws Exception {
        assertTrue(processor.process(new TestClass("400112-4587", "Åsa Älva Österberg")).isEmpty());
        assertFalse(processor.process(new TestClass("400112-4584", "Åsa Älva Österberg")).isEmpty());
    }

    @Test
    public void processorShouldValidateMethods() throws Exception {
        assertTrue(processor.process(new TestClass("400112-4587", "Åsa Älva Österberg")).isEmpty());
        assertFalse(processor.process(new TestClass("400112-4587", "Ása Ælva Østerberg")).isEmpty());
    }
    
    @Test
    public void wrongTypeShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> processor.process(new WrongValidatorAnnotatedClass(3333)));
    }
}

class TestClass {
    public TestClass(String personalNumber, String name) {
        this.personalNumber = personalNumber;
        this.name = name;
    }

    @Validate(using = PersonalNumberValidator.class)
    private final String personalNumber;

    private final String name;

    @Validate(using = NameValidator.class)
    private String getName() {
        return name;
    }
}

class WrongValidatorAnnotatedClass {
    @Validate(using = NameValidator.class)
    private final Integer i;
    
    public WrongValidatorAnnotatedClass(Integer i) {
        this.i = i;
    }
}
