package com.zcx1218029121.yun_share;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="zcx")
public class ZcxProperties {
    private static final String NAME = "短狐";

    private static final int AGE = 12;

    private String name = NAME;

    private int age = AGE;

    public static int getAGE() {
        return AGE;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
