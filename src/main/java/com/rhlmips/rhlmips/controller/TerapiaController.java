package com.rhlmips.rhlmips.controller;


import com.rhlmips.rhlmips.service.interfaces.ITerapiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terapia")
public class TerapiaController {

    private ITerapiaService terapiaService;


    @Autowired
    public void setTerapiaService(ITerapiaService terapiaService) {
        this.terapiaService = terapiaService;
    }
}
