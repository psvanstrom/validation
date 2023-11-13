package net.svanstrom.validation.rules;

public class RuleResult {
    private final boolean valid;
    private final String errorMessage;
    
    private RuleResult(final boolean valid, final String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }
    
    public static RuleResult from(final boolean valid, final String errorMessage) {
        return new RuleResult(valid, errorMessage);
    }

    public boolean isValid() {
        return valid;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}
