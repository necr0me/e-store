package by.necr0me.estore.controller.admin;

import by.necr0me.estore.dto.specific_item_detail.SpecificItemDetailDto;
import by.necr0me.estore.service.SpecificItemDetailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/admin/specific-item-details")
public class SpecificItemDetailController {
    private final SpecificItemDetailService specificItemDetailService;

    public SpecificItemDetailController(SpecificItemDetailService specificItemDetailService) {
        this.specificItemDetailService = specificItemDetailService;
    }

    @PostMapping
    public ResponseEntity<List<SpecificItemDetailDto>> createMultiple(@RequestBody @Valid List<SpecificItemDetailDto> specificItemDetailDtos) {
        return ResponseEntity.ok(specificItemDetailService.createMultiple(specificItemDetailDtos));
    }
}
