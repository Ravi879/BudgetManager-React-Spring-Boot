package com.budget.manager.service;

import com.budget.manager.shared.dto.ItemDto;
import com.budget.manager.shared.type.ItemCategory;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAllIncomesByUserId();

    List<ItemDto> getAllExpensesByUserId();

    ItemDto createItem(ItemCategory itemCategory, ItemDto itemDto);

    ItemDto updateItem(ItemCategory itemCategory, ItemDto itemDto);

    void deleteItem(ItemCategory itemCategory, String itemId);
}
