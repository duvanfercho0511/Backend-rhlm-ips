package com.rhlmips.rhlmips.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainHome")
public class MainController {

    @GetMapping("/despliegue")
    public String depliegue(){
        return "Agt los presos y los eternos pronta libertad, piraña vive toda la puta vida \n en la calle estamos los cuerdos mas locos \n las rejas no son nuestras mejores amigas dios bendice";
    }

    //https://real-hasta-la-muerte.up.railway.app/rhlm/api/

    //railway netlify

    /*<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>2.4.4</version>
    </dependency>*/

}
