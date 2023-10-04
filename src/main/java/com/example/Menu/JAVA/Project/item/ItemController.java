package com.example.Menu.JAVA.Project.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/menu/items") //this line ensures all endpoints share same resource path
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Item>> findAll(){
        List<Item> items = service.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/{id}") //line denotes GET /api/menu/items/{id} path
    public ResponseEntity<Item> find(@PathVariable("id") Long id){ //to get id value, using annotation
        Optional<Item> item = service.find(id); //if we find item with specific id, returning in a response body else 404 response
        return ResponseEntity.of(item);
    }

    @PostMapping
    public ResponseEntity<Item> create (@RequestBody Item item){
        Item created = service.create(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(
            @PathVariable("id") Long id,
            @RequestBody Item updatedItem) {

        Optional<Item> updated = service.update(id, updatedItem);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Item created = service.create(updatedItem);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
