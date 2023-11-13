# validation

Simple validation framework with annotation support.

## Rules
A `Rule` is responsible for applying a validation rule on a given input. Rules can be defined and reused by different validators.

**Example**
```java
Rule<String> fooRule = input -> input.startsWith("foo") ? new RuleResult.Ok() : new RuleResult.Error("Input must start with 'foo'");
Rule<String> barRule = input -> input.endsWith("bar")? new RuleResult.Ok() : new RuleResult.Error("Input must end with 'bar'");
```

## Validators
A `Validator` performs necessary preparation of the input and applies one or more rules on the input.

**Example**
```java
public class FooBarValidator implements Validator<String> {
    private final Set<Rule<String>> ruleSet = Set.of(fooRule, barRule);

    @Override
    public List<RuleResult> validate(String input) {
        return ruleSet.stream().map(rule -> rule.apply(input)).collect(Collectors.toList());
    }
}
```

## Annotation support
Classes can have their fields and methods annotated with the `@Validate` annotation, specifying which validator should be used to validate the data.

**Example**
```java
public class FooBar {
  public FooBar(String text) {
    this.text = text;
  }

  @Validate(using = FooBarValidator.class)
  private final String text;
}
```

Class validation is executed by using the `ValidationProcessor` class.

**Example**
```java
new ValidationProcessor().process(new Foobar("foo-123-bar"));
```
