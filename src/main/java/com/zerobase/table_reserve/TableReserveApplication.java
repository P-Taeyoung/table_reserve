package com.zerobase.table_reserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TableReserveApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableReserveApplication.class, args);
    }

}
