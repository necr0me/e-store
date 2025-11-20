package by.necr0me.estore.controller.admin;

import by.necr0me.estore.dto.item.ItemDto;
import by.necr0me.estore.entity.Item;
import by.necr0me.estore.service.ItemService;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public Slice<Item> get(@RequestParam int page) {
        return itemService.readAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.read(id));
    }

    @PostMapping
    public ResponseEntity<ItemDto> create(@RequestBody @Validated ItemDto itemDto) {
        return ResponseEntity.ok(itemService.create(itemDto));
    }

    @PutMapping
    public ResponseEntity<ItemDto> update(@RequestBody @Validated ItemDto itemDto) {
        return ResponseEntity.ok(itemService.update(itemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.ok("Item deleted");
    }
}
