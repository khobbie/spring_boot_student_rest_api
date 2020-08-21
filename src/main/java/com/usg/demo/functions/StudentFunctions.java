package com.usg.demo.functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class StudentFunctions {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

}
