package net.svanstrom.validation.rules;

public class CommonRules {
    public static final Rule<String> notBlank =
            input -> input != null && !input.trim().isEmpty() ? new RuleResult.Ok() : new RuleResult.Error("Input cannot be blank.");
    public static final Rule<String> onlyDigits =
            input -> input.matches("\\d+") ? new RuleResult.Ok() : new RuleResult.Error("Input must be digits only.");
    public static final Rule<String> onlySwedishAlphabetical =
            input -> input.matches("[a-zäåöA-ZÄÅÖ ]*") ? new RuleResult.Ok() : new RuleResult.Error("Only swedish alphabetical characters allowed.");
    public static final Rule<String> validLuhnCheck = input -> {
        int[] nums = input.chars().map(Character::getNumericValue).toArray();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int a = nums[nums.length - 1 - i] * (i % 2 + 1); // multiply ever second digit
            sum += a < 10 ? a : 1 + a - 10; // add to sum, split double-digit numbers into two separate numbers
        }
        return sum % 10 == 0 ? new RuleResult.Ok() : new RuleResult.Error("Invalid Luhn check digit.");
    };
}
