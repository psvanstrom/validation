# validation

Simple validation framework with annotation support.

## Rules
A `Rule` is responsible for applying validation logic on a given input. Rules can be defined and reused by different validators.

**Example**
```java
Rule<String> notNullRule = input -> RuleResult.from(input != null, "Input must not be null");
Rule<String> fooRule = input -> RuleResult.from(input.startsWith("foo", "Input must start with 'foo'");
Rule<String> barRule = input -> RuleResult.from(input.endsWith("bar"), "Input must end with 'bar'");
```

## Validators
A `Validator` performs necessary data manipulation and applies a rule or a set of rules on the given input.

**Example**
```java
public class FooBarValidator implements Validator<String> {
    private final Set<Rule<String>> ruleSet = Set.of(notNullRule, fooRule, barRule);

    @Override
    public List<RuleResult> validate(String input) {
        return ruleSet.stream().map(rule -> rule.apply(name)).collect(Collectors.toList());
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
public class Foobar {
  new ValidationProcessor().process(new Foobar("Foo-123-Bar"));
}
```
