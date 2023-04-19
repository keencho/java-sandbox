package com.keencho.jooq;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.keencho.jooq.model.Tables.AUTHOR;
import static com.keencho.jooq.model.Tables.BOOK;
import static com.keencho.jooq.model.tables.AuthorBook.AUTHOR_BOOK;

class JooqTestWithoutDAO extends JooqTestBase {

    @Autowired
    DSLContext dslContext;

    static class AuthorBook {
        String author;
        String title;
    }

    @Test
    void fetchAuthorBookWithCustomProjection() {
        var list = dslContext
                .select(
                        BOOK.TITLE,
                        AUTHOR.FIRST_NAME.concat(AUTHOR.LAST_NAME).as("author")
                )
                .from(AUTHOR_BOOK)
                .leftJoin(AUTHOR).on(AUTHOR.ID.eq(AUTHOR_BOOK.AUTHOR_ID))
                .leftJoin(BOOK).on(BOOK.ID.eq(AUTHOR_BOOK.BOOK_ID))
                .where(AUTHOR.FIRST_NAME.eq("홍"))
                .fetchInto(AuthorBook.class);

        Assertions.assertTrue(list.stream().allMatch(item -> item.author.charAt(0) == '홍'));
    }

    @Test
    void updateTest() {
        dslContext
                .update(AUTHOR)
                .set(AUTHOR.FIRST_NAME, "성")
                .where(AUTHOR.ID.eq(2).or(AUTHOR.ID.eq(1)))
                .execute();
    }

    @Test
    void deleteTest() {
        dslContext.delete(AUTHOR).where(AUTHOR.ID.eq(1)).execute();

        var list = dslContext.selectFrom(AUTHOR_BOOK).fetch();

        Assertions.assertTrue(list.stream().allMatch(item -> item.get(AUTHOR_BOOK.AUTHOR_ID) != 1));

    }

}
