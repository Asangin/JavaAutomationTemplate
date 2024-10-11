package com.skryl.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
public class User {
    private String name;
    private Integer age;
}
