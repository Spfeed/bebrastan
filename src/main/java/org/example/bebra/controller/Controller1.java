package org.example.bebra.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class Controller1 {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable String id) {
        return "Hello " + id;
    }

    @GetMapping("/about")
    public String about(){
        return "About us";
    }

    @GetMapping("/options")
    public String options(){
        return "options";
    }

    @GetMapping("/options/{any}")
    public String options(@PathVariable String any){
        return "not an option";
    }

}
