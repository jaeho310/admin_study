package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("삼성 노트북");

        Item newItem = itemRepository.save(item);
        assertEquals(newItem.getName(),"노트북");

    }

    @Test
    public void read() {
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(1L);

        assertEquals(true,item.isPresent());
//        item.ifPresent(selectedItem -> {
//           System.out.println(selectedItem);
//        });
    }

}