package items.controller;

import items.model.Item;
import items.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "items",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> addItems(@RequestBody List<Item> newItems) {
        List<Item> itemsWithId = itemService.addAllItems(newItems);
        return itemsWithId;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> setItems(@RequestBody List<Item> newItems) {
        List<Item> itemsWithId = itemService.setItems(newItems);
        return itemsWithId;
    }

    @DeleteMapping()
    public int deleteAllItems() {
        return itemService.deleteAllItems();
    }
}