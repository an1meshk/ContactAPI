package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.mock;

/**
 * Created by Animesh Kumar on 23-07-2018.
 */
@Configuration
public class MockDataSourceConfig {

    @Bean
    public JdbcTemplate mockJdbcTemplate() {
        return mock(JdbcTemplate.class);
    }

}
