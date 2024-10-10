package com.springdemo.mvc.validationdemo.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springdemo.mvc.validationdemo.model.Customer;

import jakarta.validation.Valid;

@Controller
public class CustomerController {

    // @InitBinder convert trim input strings. Pre-process every String form data. This method
    // will be called for every web request coming into our controller
    // Remove leading and trailing whitespac. Resolve issue our valitation.
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Removes whitespace - leading and trailing -> true means trim empty string to null
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // Model allows us to share information between Controllers and view pages (Thymeleaf)
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());

        return "customer-form";
    }

    // @Valid tells Spring MVC to perform validation for this given form data
    // @ModelAttribute read the form data from that model attribute customer, that's the 
    // customer that was submited by the HTML form.
    // BindingResult holds the result of the validation, so Spring MVC will go through and 
    // perform all the validation, get the results, if there were any errors, what the error 
    // message were. If everything was successful, then it'll have that data in this given object
    // here, the binding result.
    @PostMapping("/processForm")
    public String processForm(
        @Valid @ModelAttribute("customer") Customer theCustomer, 
        BindingResult theBindingResult) {
        
        if (theBindingResult.hasErrors()) {
            return "customer-form";
        } else { 
            return "customer-confirmation";
        }
    }

}
