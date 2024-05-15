package com.eduardo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class GreetingsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @RequestMapping("/greeting")    
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "Word") String name) {
        return new Greeting(counter.incrementAndGet(),String.format(template, name));
    }

}
