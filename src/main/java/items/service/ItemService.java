package items.service;

import items.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {

    private List<Item> items = new ArrayList<Item>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

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

    /**
     * Convert a {@code String} to a {@code UUID}.  Returns an Optional to faciliate impromperly formatted {@code String}s
     *
     * @param id String representing a UUID
     * @return an Optional of the UUID, in case the String is not formatted
     */
    private Optional<UUID> convertToUuid(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return Optional.of(uuid);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Invalid UUID provided for Item: " + id);
            return Optional.empty();
        }
    }

    private Optional<Item> retrieveItemById(UUID uuid) {
        for (Item item : this.items) {
            if (item.getId().equals(uuid)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    /**
     * Retrieve an {@code Item} by its ID. Returns null if not {@code Item} is found with the ID
     *
     * @param id the UUID of the Item to return
     * @return The Item found, or null if not Item found
     */
    public Item getItemById(String id) {
        Optional<UUID> optionalUuid = convertToUuid(id);
        if (!optionalUuid.isPresent()) {
            LOGGER.error("Unable to convert " + id + " to UUID");
            return null;
        }

        UUID uuid = optionalUuid.get();
        return retrieveItemById(uuid).orElse(null);
    }

    /**
     * Delete {@code Item} by its ID.  Returns a {@code Boolean} for success of deletion
     *
     * @param id ID for item to delete
     * @return true/false for success in deletion
     */
    public boolean deleteItemById(String id) {
        Optional<UUID> optionalUuid = convertToUuid(id);
        if (!optionalUuid.isPresent()) {
            LOGGER.error("Unable to convert " + id + " to UUID");
            return false;
        }

        Optional<Item> toRemove = retrieveItemById(optionalUuid.get());
        toRemove.ifPresent(i -> this.items.remove(i));
        return toRemove.isPresent();
    }

    /**
     * Update an {@code Item}'s name by its ID
     *
     * @param id ID for the Item to update
     * @param newName new name String to set on the Item
     * @return the updated Item
     */
    public Item updateItemNameById(String id, String newName) {
        Optional<UUID> optionalUuid = convertToUuid(id);
        if (!optionalUuid.isPresent()) {
            LOGGER.error("Unable to convert " + id + " to UUID");
            return null;
        }

        UUID uuid = optionalUuid.get();
        for (Item item : this.items) {
            if (!item.getId().equals(uuid)) {
                continue;
            }

            item.setName(newName);
            return item;
        }

        return null;
    }
}
