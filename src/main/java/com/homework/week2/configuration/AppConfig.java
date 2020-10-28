package com.homework.week2.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="app")
public class AppConfig {
    private int tax;
    private int discount;
}
