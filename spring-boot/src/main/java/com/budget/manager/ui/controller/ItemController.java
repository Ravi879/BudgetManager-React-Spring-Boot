package com.budget.manager.ui.controller;

import com.budget.manager.service.ItemService;
import com.budget.manager.shared.dto.ItemDto;
import com.budget.manager.shared.type.ItemCategory;
import com.budget.manager.ui.modal.request.ItemRequest;
import com.budget.manager.ui.modal.response.AllItemsResponse;
import com.budget.manager.ui.modal.response.ItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@Validated
@Slf4j
public class ItemController {

    private final ModelMapper modelMapper;
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
        modelMapper = new ModelMapper();
    }

    @GetMapping
    public ResponseEntity<AllItemsResponse> getAllItems() {
        // get all income and expenses
        List<ItemDto> allIncomesDto = itemService.getAllIncomesByUserId();
        List<ItemDto> allExpensesDto = itemService.getAllExpensesByUserId();

        log.info("GetAllItems -- all.items -- userId={}", getUserId());

        // type - for mapping list of DTOs to list of item response
        Type type = new TypeToken<List<ItemResponse>>() {
        }.getType();
        // set incomes and expenses, and return it.
        AllItemsResponse response = new AllItemsResponse();
        response.setIncomes(modelMapper.map(allIncomesDto, type));
        response.setExpenses(modelMapper.map(allExpensesDto, type));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{category}")
    public ResponseEntity<ItemResponse> createItem(@PathVariable("category") ItemCategory itemCategory, @RequestBody @Valid ItemRequest itemRequest) {
        // map to dto
        ItemDto itemDto = modelMapper.map(itemRequest, ItemDto.class);
        // insert to item database
        ItemDto storedItemDto = itemService.createItem(itemCategory, itemDto);

        log.info("CreateItem -- create.{} -- itemId={} userId={}", itemCategory.getType(), storedItemDto.getItemId(), getUserId());

        // return inserted item
        ItemResponse response = modelMapper.map(storedItemDto, ItemResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{category}/{itemId}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable("category") ItemCategory itemCategory,
                                                   @PathVariable String itemId,
                                                   @RequestBody @Valid ItemRequest itemRequest) {
        // map to dto
        ItemDto itemDto = modelMapper.map(itemRequest, ItemDto.class);
        itemDto.setItemId(itemId);
        // insert to item database
        ItemDto storedItemDto = itemService.updateItem(itemCategory, itemDto);

        log.info("UpdateItem -- update.{} -- itemId={} userId={}", itemCategory.getType(), storedItemDto.getItemId(), getUserId());

        // return inserted item
        ItemResponse response = modelMapper.map(storedItemDto, ItemResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{category}/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("category") ItemCategory itemCategory,
                                           @PathVariable String itemId) {
        // delete item from database
        itemService.deleteItem(itemCategory, itemId);

        log.info("DeleteItem -- delete.{} -- itemId={} userId={}", itemCategory.getType(), itemId, getUserId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String getUserId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
