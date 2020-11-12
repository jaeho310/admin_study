package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private  CategoryRepository categoryRepository;

    @Test
    public void create() {
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createBy);

        Category newCategory = categoryRepository.save(category);
        assertEquals(newCategory.getTitle(),"컴퓨터");
    }

    @Test
    public void read() {
        Optional<Category> optionalCategory = categoryRepository.findByType("COMPUTER");

        // select * from category where type = 'COMPUTER'


        optionalCategory.ifPresent( c -> {
            System.out.println(c.getId());
            System.out.println(c.getTitle());
            System.out.println(c.getType());

        });

    }

}