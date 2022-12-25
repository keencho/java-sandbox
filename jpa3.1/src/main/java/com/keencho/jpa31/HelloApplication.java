package com.keencho.jpa31;

import com.keencho.jpa31.model.Book;

public class HelloApplication {
    public static void main(String[] args) {
        var book = new Book("이펙티브 자바", 356);

        HibernateHelper.init();
        HibernateHelper.persist(book);

        var id = book.getId();

        System.out.println(id);

    }
}