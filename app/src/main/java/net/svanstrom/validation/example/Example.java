package net.svanstrom.validation.example;

import net.svanstrom.validation.ValidationProcessor;

public class Example {
    public static void main(String[] args) throws Exception {
        ValidationProcessor processor = new ValidationProcessor();
        
        FooBar fooBarIncorrect = new FooBar("Hejsan");
        FooBar fooBarCorrect = new FooBar("foo-xyz-bar");
        
        System.out.printf("fooBarIncorrect errors = %s", processor.process(fooBarIncorrect));
        System.out.printf("fooBarCorrect errors = %s", processor.process(fooBarCorrect));
    }
}