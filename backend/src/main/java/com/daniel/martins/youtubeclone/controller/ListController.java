package com.daniel.martins.youtubeclone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ListController {

    @GetMapping("/list")
    public List<String> getListItems() {
        return List.of("1","2", "3");
    }
}
