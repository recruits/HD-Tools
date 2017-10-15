package com.chilicool.hdtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chilicool on 2017/9/6.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home/index";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        return "home/index";
    }

    @RequestMapping(value = "/home_page.html", method = RequestMethod.GET)
    public String mainPage() {
        return "home/home_page";
    }

}
