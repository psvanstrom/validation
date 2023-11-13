package net.svanstrom.validation.example;

import net.svanstrom.validation.annotations.Validate;

public class FooBar {
    public FooBar(String text) {
        this.text = text;
    }

    @Validate(using = FooBarValidator.class)
    private final String text;
}