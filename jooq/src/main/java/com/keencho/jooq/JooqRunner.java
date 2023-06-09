package com.keencho.jooq;

import org.jooq.DSLContext;

@FunctionalInterface
public interface JooqRunner {
    void run(DSLContext dslContext);
}
