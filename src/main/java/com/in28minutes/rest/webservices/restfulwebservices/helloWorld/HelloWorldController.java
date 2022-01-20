package com.in28minutes.rest.webservices.restfulwebservices.helloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

// Controller
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    // GET
    // URI - /hello-world
    // method - "Hello World"
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    // /hello-world-bean/path-variable/in28minutes
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
           // @RequestHeader(name = "Accept-Language", required = false) Locale locale
            ) {
                return messageSource
                        .getMessage("good.morning.message", null, "Default message" //, locale);
                                , LocaleContextHolder.getLocale());

                // return "Hello World";

                // en = Hello World
                // nl = Goedemorgen
                // fr = Bonjour
    }

}
