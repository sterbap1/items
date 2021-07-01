package items.service;

import items.model.Item;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {
    private List<Item> items = new ArrayList<Item>();

    /**
     * Returns the list of {@code Item}s
     *
     * @return List of Items
     */
    public List<Item> getAllItems() {
        return items;
    }

    /**
     * Adds all {@code Item}s to the saved List.
     * Will verify that the id is set on each {@code Item} before adding
     *
     * @param items List of Items to add to the saved List
     * @return the updated with UUID list of Items added
     */
    public List<Item> addAllItems(List<Item> items) {
        for (Item i : items) {
            if (Objects.isNull(i.getId())) {
                i.setId(UUID.randomUUID());
            }
        }
        this.items.addAll(items);
        return items;
    }

    /**
     * Completed reset the list of saved {@code Item}s to the input parameter
     * Will verify that the id is set on each {@code Item} before adding
     *
     * @param newItems the List of Items to overwrite
     * @return the new full list of Items
     */
    public List<Item> setItems(List<Item> newItems) {
        for (Item i : newItems) {
            if (Objects.isNull(i.getId())) {
                i.setId(UUID.randomUUID());
            }
        }
        this.items = newItems;
        return this.items;
    }

    /**
     * Clear out the List of saved {@code Item}s
     *
     * @return the number of Items deleted
     */
    public int deleteAllItems() {
        int numDeleted = this.items.size();
        this.items.clear();
        return numDeleted;
    }
}