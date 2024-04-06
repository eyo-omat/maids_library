package com.maids.maids_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MaidsLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaidsLibraryApplication.class, args);
    }

}
