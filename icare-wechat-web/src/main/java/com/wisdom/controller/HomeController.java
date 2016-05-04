package com.wisdom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * home页面controller
 * Created by fusj on 16/5/4.
 */
@Controller
@RequestMapping("home")
public class HomeController {

    /**
     * home页面
     * @return
     */
    @RequestMapping
    public String home() {
        return "/index";
    }
}
