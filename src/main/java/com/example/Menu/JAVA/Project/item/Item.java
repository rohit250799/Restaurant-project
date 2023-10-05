package com.example.Menu.JAVA.Project.item;

import org.springframework.data.annotation.Id;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.*;

public class Item {
    private final Long id;
    @NotNull(message = "Name should not be null")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name should be a String")
    private final String name;
    @NotNull(message = "price is required")
    @Positive(message = "price should be a positive number")
    private final Long price;
    @NotNull(message = "Description is required")
    @Pattern(regexp = "[a-zA-Z] + $", message = "description should be a String")
    private final String description;
    @NotNull(message = "Image is required")
    @URL(message = "Image must be a URL")
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
