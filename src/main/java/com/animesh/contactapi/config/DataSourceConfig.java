package com.animesh.contactapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by Animesh Kumar on 14-04-2018.
 *
 * Data Source configuration class to setup in-memory H2 database
 */
@Configuration
public class DataSourceConfig {

    /**
     * Creates datasource bean required to create
     * JDBCTemplate bean. On application startup it reads the
     * "schema.sql" and creates CONTACT_INFO table.
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/schema.sql")
                .build();
    }

    /**
     * Create contactJdbcTemplate of type JdbcTemplate.
     * @return JdbcTemplate
     */
    @Bean
    public JdbcTemplate contactJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
