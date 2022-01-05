package br.com.marcia.starwars.api.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "redirect:/swagger-ui.html";
    }
}
