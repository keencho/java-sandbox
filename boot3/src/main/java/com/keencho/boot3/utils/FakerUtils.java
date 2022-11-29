package com.keencho.boot3.utils;

import net.datafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class FakerUtils {
    private static final Faker faker;

    static {
        faker = new Faker();
    }

    public static Map<String, String> generateBook() {
        var map = new HashMap<String, String>();
        var book = faker.book();

        map.put("title", book.title());
        map.put("author", book.author());
        map.put("genre", book.genre());
        map.put("publisher", book.publisher());

        return map;
    }
}
