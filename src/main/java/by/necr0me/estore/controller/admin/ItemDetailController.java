package by.necr0me.estore.controller.admin;

import by.necr0me.estore.dto.item_detail.ItemDetailDto;
import by.necr0me.estore.entity.ItemDetail;
import by.necr0me.estore.service.ItemDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/item-details")
public class ItemDetailController {
    private final ItemDetailService itemDetailService;

    public ItemDetailController(ItemDetailService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDetailDto> read(@PathVariable Long id) {
        return ResponseEntity.ok(itemDetailService.read(id));
    }

    @PostMapping
    public ResponseEntity<ItemDetailDto> read(@RequestBody @Validated ItemDetailDto itemDetailDto) {
        return ResponseEntity.ok(itemDetailService.create(itemDetailDto));
    }

    @PutMapping
    public ResponseEntity<ItemDetailDto> update(@RequestBody @Validated ItemDetailDto itemDetailDto) {
        return ResponseEntity.ok(itemDetailService.update(itemDetailDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        itemDetailService.delete(id);
        return ResponseEntity.ok("Item detail successfully deleted");
    }
}
