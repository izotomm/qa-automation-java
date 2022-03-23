package com.tcs.edu;

import com.tcs.edu.decorator.TimestampMessageDecorator;

class Application {
    public static void main(String[] args) {
        TimestampMessageDecorator.decorate("Hello world!");
    }

}

