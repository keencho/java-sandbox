package com.keencho.jooq;

import com.keencho.jooq.model.tables.pojos.Author;
import com.keencho.jooq.model.tables.records.AuthorRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.keencho.jooq.model.Sequences.AUTHOR_ID_SEQ;
import static com.keencho.jooq.model.Sequences.BOOK_ID_SEQ;
import static com.keencho.jooq.model.tables.Author.AUTHOR;
import static com.keencho.jooq.model.tables.AuthorBook.AUTHOR_BOOK;
import static com.keencho.jooq.model.tables.Book.BOOK;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class JooqTestBase {

    @Autowired
    DSLContext dslContext;

    @BeforeAll
    public void setUp() {
        // author
        dslContext.delete(AUTHOR).execute();
        dslContext.alterSequence(AUTHOR_ID_SEQ).restart().execute();
        dslContext
                .insertInto(
                        AUTHOR,
                        AUTHOR.FIRST_NAME,
                        AUTHOR.LAST_NAME
                )
                .valuesOfRows(
                        Stream.of("홍길동", "김철수", "박영희").map(name -> DSL.row(
                                name.substring(0, 1),
                                name.substring(1, 3)
                        )).collect(Collectors.toList())
                )
                .execute();

        // book
        dslContext.delete(BOOK).execute();
        dslContext.alterSequence(BOOK_ID_SEQ).restart().execute();
        dslContext
                .insertInto(
                        BOOK,
                        BOOK.TITLE
                )
                .valuesOfRows(
                        Stream.of(
                                "이펙티브 자바", "리팩토링", "DDD START!", "토비의 스프링3",
                                        "Do it! 점프 투 파이썬", "혼자 공부하는 머신러닝+딥러닝", "개발자를 위한 챗GPT 활용법",
                                        "C언어로 쉽게 풀어쓴 자료구조", "비전공자도 이해할 수 있는 AI 지식", "모던 자바스크립트 Deep Dive"
                                        )
                                .map(DSL::row)
                                .collect(Collectors.toList())
                )
                .execute();

        var rand = new Random();
        var authorList = dslContext.selectFrom(AUTHOR).fetch();
        var bookList = dslContext.selectFrom(BOOK).fetch();

        bookList.forEach(book -> dslContext
                .insertInto(
                        AUTHOR_BOOK,
                        AUTHOR_BOOK.AUTHOR_ID,
                        AUTHOR_BOOK.BOOK_ID
                )
                .valuesOfRows(
                        DSL.row(
                                authorList.get(rand.nextInt(authorList.size())).get(AUTHOR.ID),
                                book.get(BOOK.ID)
                        )
                )
                .execute()
        );
    }
}
