/*
 * ©  2019 Optum, Inc. - All Rights Reserved. Your use of this product is
 *  governed by the terms of your company's agreement. You may not use or
 *  disclose this product, or allow others to use it or disclose it, except as
 *  permitted by your agreement with Optum.
 */
package com.odx.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 **/
@ComponentScan(basePackages = {"com.odx.test"})
@EntityScan("com.odx.test")
@EnableJpaRepositories(basePackages="com.odx.test")
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }
}
