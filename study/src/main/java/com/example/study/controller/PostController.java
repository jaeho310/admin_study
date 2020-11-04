package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    // HTML <form>
    // ajax의 검색
    // http post body 안에 data가 들어있다
    // json, xml, multipart-form / text - plain

    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {

        System.out.println(123);
        return searchParam;
    }

    @PutMapping
    public void put() {

    }

    @PatchMapping
    public void patch() {
        
    }
}
