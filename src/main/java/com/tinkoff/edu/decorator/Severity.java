package com.tinkoff.edu.decorator;

import java.util.Random;

public enum Severity {
    MINOR,
    REGULAR,
    MAJOR;


    private static final Random RANDOM = new Random();

    public static Severity randomSeverity() {
        return Severity.values()[RANDOM.nextInt(Severity.values().length)];
    }
}
