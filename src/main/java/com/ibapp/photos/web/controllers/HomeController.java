package com.ibapp.photos.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

/**
 * Created by imranbordiwala on 02/06/2015.
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage() {

        return "homepage";
    }

}
