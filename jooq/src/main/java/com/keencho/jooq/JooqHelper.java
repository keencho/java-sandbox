package com.keencho.jooq;

import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

public class JooqHelper {
    private final String url;
    private final String userName;
    private final String password;

    public JooqHelper() throws IOException {
        var prop = new Properties();
        var loader = Thread.currentThread().getContextClassLoader();
        var stream = loader.getResourceAsStream("datasource.properties");
        prop.load(stream);

        this.url = prop.getProperty("url");
        this.userName = prop.getProperty("username");
        this.password = prop.getProperty("password");
    }

    public void run(JooqRunner runner) {
        try (var conn = DriverManager.getConnection(this.url, this.userName, this.password)) {
            runner.run(DSL.using(conn, SQLDialect.POSTGRES));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
