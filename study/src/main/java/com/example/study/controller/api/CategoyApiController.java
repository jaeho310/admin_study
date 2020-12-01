package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.CategoryApiRequest;
import com.example.study.model.network.response.CategoryApiResponse;
import com.example.study.service.CategoryApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.net.CacheResponse;

@RestController
@RequestMapping("/api/category")
public class CategoyApiController extends CrudController<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryApiLogicService categoryApiLogicService;

    @PostConstruct
    public void init() {
        this.baseService = categoryApiLogicService;
    }

}
