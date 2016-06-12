package com.co.cmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author by Cyprian Omenuko on 6/9/2016.
 */
@Controller
@RequestMapping("/")
public class PageController {

    @RequestMapping(method = RequestMethod.GET)
    public String redirect() {
        return "index";
    }
}
