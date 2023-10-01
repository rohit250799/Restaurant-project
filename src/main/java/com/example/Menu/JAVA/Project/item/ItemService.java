package com.example.Menu.JAVA.Project.item;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@EnableMapRepositories
public class ItemService {
    private final CrudRepository<Item, Long> repository;
    public ItemService(CrudRepository<Item, Long> repository){
        this.repository = repository;
        this.repository.saveAll(defaultItems()); //to save multiple entities to DB
    }

    public static List<Item> defaultItems(){
        return List.of(
                new Item(1L, "Burger", 499L, "Tasty", "https://cdn.auth0.com/blog/whatabyte/burger-sm.png"),
                new Item(2L, "Pizza", 288L, "Cheesy", "https://cdn.auth0.com/blog/whatabyte/pizza-sm.png"),
                new Item(3L, "Tea", 199L, "Refreshing", "https://cdn.auth0.com/blog/whatabyte/tea-sm.png")
        );
    }

    public List<Item> findAll(){

    }

}
