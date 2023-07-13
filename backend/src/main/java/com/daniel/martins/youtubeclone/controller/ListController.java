package com.daniel.martins.youtubeclone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ListController {

    @GetMapping("/list")
    public List<String> getListItems() {
        return List.of("1","2", "3");
    }
}
