package com.crud.tasks.service;

import org.springframework.data.jdbc.repository.config.DialectResolver.JdbcDialectProvider;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.MySqlDialect;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Optional;

public class MariaDbDialectResolver implements JdbcDialectProvider {
    @Override
    public Optional<Dialect> getDialect(JdbcOperations jdbcOperations) {
        return Optional.of(MySqlDialect.INSTANCE);
    }
}
