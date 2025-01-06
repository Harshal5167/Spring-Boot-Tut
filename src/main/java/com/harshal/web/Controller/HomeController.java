package com.harshal.web.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//if there are more than one controller classes in the application, then spring resolves which controller class to use by the front controller (DispatcherServlet) which comes inbuilt with spring.
@RestController // This annotation tells Spring that this class will be used to handle web requests, and it is called a controller class.
//if I removed @restController annotation then it will and used an annotation of @Controller then it will send a file as response and not text. the rest controller tells it to return text.
@RequestMapping("/api")
public class HomeController {

    @RequestMapping("/") // This annotation tells Spring that this method will handle requests to the root URL of the application.
    public String home() {
        return "This is an E-commerce Api";
    }
}
