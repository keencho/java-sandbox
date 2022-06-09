package com.sycho.spring.querydsl.util;

import com.github.javafaker.Faker;

public class JavaFakerGenerator {
    private final static Faker faker = new Faker();

    public static String getName() {
        return faker.name().name();
    }

    public static String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String getAddress() {
        return faker.address().fullAddress();
    }

}
