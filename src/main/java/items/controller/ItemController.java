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
        return itemService.addAllItems(newItems);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> setItems(@RequestBody List<Item> newItems) {
        return itemService.setItems(newItems);
    }

    @DeleteMapping()
    public int deleteAllItems() {
        return itemService.deleteAllItems();
    }

    @GetMapping(path = "/{id}")
    public Item getItemById(@PathVariable("id") String id) {
        return itemService.getItemById(id);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteItemById(@PathVariable("id") String id) {
        return itemService.deleteItemById(id);
    }

    @PutMapping(path = "/{id}/{name}")
    public Item updateItemNameById(@PathVariable("id") String id, @PathVariable("name") String newName) {
        return itemService.updateItemNameById(id, newName);
    }
}