package com.keencho.jooq;

import com.keencho.jooq.model.tables.pojos.Author;
import com.keencho.jooq.model.tables.pojos.AuthorBook;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import static com.keencho.jooq.model.Tables.AUTHOR;
import static com.keencho.jooq.model.Tables.BOOK;
import static com.keencho.jooq.model.tables.AuthorBook.AUTHOR_BOOK;

class JooqTestWithoutDAO extends JooqTestBase {

    @Autowired
    DSLContext dslContext;

    static class Projection {
        String author;
        String title;
    }

    @Test
    void fetchIntoCustomClass() {
        var list = dslContext
                .select(
                        BOOK.TITLE,
                        AUTHOR.FIRST_NAME.concat(AUTHOR.LAST_NAME).as("author")
                )
                .from(AUTHOR_BOOK)
                .leftJoin(AUTHOR).on(AUTHOR.ID.eq(AUTHOR_BOOK.AUTHOR_ID))
                .leftJoin(BOOK).on(BOOK.ID.eq(AUTHOR_BOOK.BOOK_ID))
                .where(AUTHOR.FIRST_NAME.eq("홍"))
                .fetchInto(Projection.class);

        Assertions.assertTrue(list.stream().allMatch(item -> StringUtils.hasText(item.title) && item.author.charAt(0) == '홍'));
    }

    @Test
    void update() {
        var condition = AUTHOR.ID.eq(1).or(AUTHOR.ID.eq(2));
        dslContext
                .update(AUTHOR)
                .set(AUTHOR.FIRST_NAME, "성")
                .where(condition)
                .execute();

        var list = dslContext.selectFrom(AUTHOR).where(condition).fetchInto(Author.class);

        Assertions.assertTrue(list.stream().allMatch(item -> item.getFirstName().equals("성")));
    }

    @Test
    void delete() {
        dslContext.delete(AUTHOR).where(AUTHOR.ID.eq(1)).execute();

        var authorList = dslContext.selectFrom(AUTHOR).fetchInto(Author.class);
        var authorBookList = dslContext.selectFrom(AUTHOR_BOOK).fetchInto(AuthorBook.class);

        Assertions.assertTrue(authorList.stream().noneMatch(item -> item.getId() == 1));
        Assertions.assertTrue(authorBookList.stream().noneMatch(item -> item.getAuthorId() == 1));
    }

}
