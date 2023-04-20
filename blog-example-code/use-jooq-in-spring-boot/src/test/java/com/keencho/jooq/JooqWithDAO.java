package com.keencho.jooq;

import com.keencho.jooq.model.Tables;
import com.keencho.jooq.model.tables.daos.AuthorBookDao;
import com.keencho.jooq.model.tables.daos.AuthorDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class JooqWithDAO extends JooqTestBase {

    @Autowired
    AuthorDao authorDao;

    @Autowired
    AuthorBookDao authorBookDao;

    @Test
    void select() {
        var list = authorDao.fetch(Tables.AUTHOR.FIRST_NAME, "홍");

        Assertions.assertTrue(list.stream().allMatch(item -> item.getFirstName().equals("홍")));
    }

    @Test
    void update() {
        authorDao.update(
                authorDao
                        .fetchRangeOfId(1, 2)
                        .stream()
                        .peek(item -> item.setFirstName("성"))
                        .collect(Collectors.toList())
        );

        Assertions.assertTrue(authorDao.fetchRangeOfId(1, 2).stream().allMatch(item -> item.getFirstName().equals("성")));
    }

    @Test
    void delete() {
        authorDao.deleteById(1);

        Assertions.assertFalse(authorDao.existsById(1));
        Assertions.assertTrue(authorBookDao.fetchByAuthorId(1).isEmpty());
    }

}
