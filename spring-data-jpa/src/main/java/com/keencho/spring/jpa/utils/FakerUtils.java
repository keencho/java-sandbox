package com.keencho.spring.jpa.utils;

import net.datafaker.Faker;

public class FakerUtils {
    private static final Faker faker = new Faker();

    public static String name() {
        return faker.name().fullName();
    }

    public static String address() {
        return faker.address().fullAddress();
    }

    public static String phoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    public static String teamName() {
        return faker.team().name();
    }

}
