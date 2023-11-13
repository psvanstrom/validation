package net.svanstrom.validation.rules;

public sealed class RuleResult permits RuleResult.Ok, RuleResult.Error {
    private final String errorMessage;
    public RuleResult() {
        errorMessage = null;
    }
    
    private RuleResult(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    static final public class Ok extends RuleResult {}
    static final public class Error extends RuleResult {
        public Error(final String errorMessage) {
            super(errorMessage);
        }
    }
}