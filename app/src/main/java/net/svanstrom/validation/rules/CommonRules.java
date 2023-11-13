package net.svanstrom.validation.rules;

public class CommonRules {
    public static Rule<String> notBlank =
            input -> RuleResult.from(input != null && !input.trim().isEmpty(), "Input cannot be blank.");
    public static final Rule<String> onlyDigits =
            input -> RuleResult.from(input.matches("\\d+"), "Input must be digits only.");
    public static final Rule<String> onlySwedishAlphabetical =
            input -> RuleResult.from(input.matches("[a-zäåöA-ZÄÅÖ ]*"), "Only swedish alphabetical characters allowed.");
    public static final Rule<String> validLuhnCheck = input -> {
        int[] nums = input.chars().map(Character::getNumericValue).toArray();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int a = nums[nums.length - 1 - i] * (i % 2 + 1); // multiply ever second digit
            sum += a < 10 ? a : 1 + a - 10; // add to sum, split double-digit numbers into two separate numbers
        }
        return RuleResult.from(sum % 10 == 0, "Invalid Luhn check digit.");
    };
}
