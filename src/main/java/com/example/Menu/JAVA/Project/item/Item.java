package com.example.Menu.JAVA.Project.item;

import org.springframework.data.annotation.Id;

public class Item {
    private final Long id;
    private final String name;
    private final Long price;
    private final String description;
    private final String image;

    public Item(
            Long id,
            String name,
            Long price,
            String description,
            String image
    ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }
    @Id
    public Long getId(){ return id;}
    public String getName(){ return name;}
    public Long getPrice(){ return price;}
    public String getDescription(){ return description;}
    public String getImage(){ return image;}
    /* following function:
    takes item as input & creates new item by updating some properties
    but keeping the id same. For example, if you have an item with ID 123 and you
    call updateWith with a new item that has a different name, price, description,
    and image, it will create a new item with the same ID (123) but with the updated
    details. The original item remains unchanged. */
    public Item updateWith(Item item){ //
        return new Item(
                this.id,  //statement responsible to keep id the same
                item.name,
                item.price,
                item.description,
                item.image
        );
    }
}
