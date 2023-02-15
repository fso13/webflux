package ru.fso13.webflux.configuration;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class R2DBCConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .inMemory("~/db/testdb")
                .password("")
                .username("sa")
                .option("DB_CLOSE_DELAY=-1")
                .option("INIT=create schema IF NOT EXISTS PUBLIC")
                .build());
    }
}
