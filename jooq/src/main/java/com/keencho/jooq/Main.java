package com.keencho.jooq;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var helper = new JooqHelper();

        helper.run((dslContext) -> {
            var result = dslContext
                    .selectFrom(com.keencho.jooq.model.Tables.AUTHOR)
                    .fetchInto(com.keencho.jooq.model.tables.pojos.Author.class);
            System.out.println(result);
        });
    }
}
