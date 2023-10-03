package com.example.Menu.JAVA.Project.item;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        List<Item> list = new ArrayList<>();
        Iterable<Item> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Item> find(long id){
        return repository.findById(id);
    }

    public Item create(Item item){
        Item copy = new Item(
                new Date().getTime(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getImage()
        );
        return repository.save(copy);
    }

    public Optional<Item> update(long id, Item newItem){
        return repository.findById(id)
                .map(oldItem -> {
                    Item updated = oldItem.updateWith(newItem);
                    return repository.save(updated);
                });
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}
