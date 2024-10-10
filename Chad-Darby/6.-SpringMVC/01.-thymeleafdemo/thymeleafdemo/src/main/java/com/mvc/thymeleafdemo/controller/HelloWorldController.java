package com.mvc.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    // @RequestMapping will support any HTTP requests (GET, POST, etc.)
    // we can use @GetMapping and @PostMapping instead of @RequestMapping.
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    // Need a controller method to read form data and add data to the model (MVC)
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(@RequestParam("studentName") String theName, Model model) {        
        // Read the request parameter from the HTML form (query parameter) using request
        // HttpServletRequest request
        // String theName = request.getParameter("studentName");

        // Convert the data to uppercase
        theName = theName.toUpperCase();

        // Create the message
        String result = "Yo! " + theName;

        // Add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

}
