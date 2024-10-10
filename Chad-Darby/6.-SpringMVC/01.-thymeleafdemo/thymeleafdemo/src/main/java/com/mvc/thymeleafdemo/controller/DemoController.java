package com.mvc.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    // Create a mapping for /hello
    @GetMapping("/hello")
    public String sayHello(Model theModel) {
        theModel.addAttribute("theDate", java.time.LocalDateTime.now());
        /* We have Thymeleaf dependency, Spring Boot will auto-configure to use Thymeleaf.
        So it will look for src/main/resource/templates/helloworld.html */
        return "helloworld";        
    }

}
