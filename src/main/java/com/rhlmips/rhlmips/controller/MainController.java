package com.rhlmips.rhlmips.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainHome")
public class MainController {

    @GetMapping("/despliegue")
    public String depliegue(){
        return "Depliegue correcto";
    }
}
