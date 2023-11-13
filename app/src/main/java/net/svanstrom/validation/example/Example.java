package net.svanstrom.validation.example;

import net.svanstrom.validation.ValidationProcessor;
import net.svanstrom.validation.ValidationProcessor.ValidationError;

import java.util.List;

public class Example {
    public static void main(String[] args) throws Exception {
        ValidationProcessor processor = new ValidationProcessor();
        
        FooBar fooBarIncorrect = new FooBar("Hejsan");
        FooBar fooBarCorrect = new FooBar("foo-xyz-bar");
        
        List<ValidationError> fooBarIncorrectErrors = processor.process(fooBarIncorrect);
        List<ValidationError> fooBarCorrectErrors = processor.process(fooBarCorrect);
        
        System.out.printf("fooBarIncorrect errors = %s%n", fooBarIncorrectErrors);   
        System.out.printf("fooBarCorrect errors = %s%n", fooBarCorrectErrors);   
    }
}