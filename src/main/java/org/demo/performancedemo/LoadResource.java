package org.demo.performancedemo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoadResource {
    private static int counter = 0;

    @SneakyThrows
    @GetMapping
    public String generateLoad() {
        var start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) < 250) {
            Math.sin(Math.random());
        }
        log.info("Complex calculations done - phew - {}", ++counter);
        return "OK!";
    }
}
