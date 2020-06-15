package com.freenow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author Simarpreet Singh, created on 10 April 2020.
 * 
 * Default Controller, used to handle default redirection of application to Swagger-ui.html page
 *
 */
@Controller
@ApiIgnore
public class HomeController
{

    @RequestMapping("/")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }

}
