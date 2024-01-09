package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partTime")
public class PartTimeController {

    @GetMapping
    public String time(@RequestParam("time") String time){

        String[] split = time.split("\\.");

        System.out.println(split[0]);
        System.out.println(split[1]);




        return "ok";
    }

}
