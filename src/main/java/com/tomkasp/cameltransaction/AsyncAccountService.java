package com.tomkasp.cameltransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class AsyncAccountService {

    private static final Logger log = LoggerFactory.getLogger(AsyncAccountService.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Async
    public void asynTest() {
        jdbcTemplate.query("SELECT * FROM ACCOUNT ",
                (resultSet, rowNum) -> resultSet.getString("number"))
                .forEach(s -> log.info("!!!!! {} ", s));
    }

}
