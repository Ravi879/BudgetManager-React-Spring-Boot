package com.budget.manager.service.impl;

import com.budget.manager.exception.ResourceNotFoundException;
import com.budget.manager.repository.ExpenseRepository;
import com.budget.manager.repository.IncomeRepository;
import com.budget.manager.repository.UserRepository;
import com.budget.manager.repository.entity.ExpenseEntity;
import com.budget.manager.repository.entity.IncomeEntity;
import com.budget.manager.repository.entity.ItemEntity;
import com.budget.manager.repository.entity.UserEntity;
import com.budget.manager.service.ItemService;
import com.budget.manager.shared.dto.ItemDto;
import com.budget.manager.shared.type.ItemCategory;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.List;
import java.util.function.Supplier;

@Service
public class ItemServiceImpl implements ItemService {

    private final ModelMapper mapper;
    private final IncomeRepository incomeRepo;
    private final ExpenseRepository expenseRepo;
    private final UserRepository userRepo;

    public ItemServiceImpl(IncomeRepository incomeRepo, ExpenseRepository expenseRepo, UserRepository userRepo) {
        this.incomeRepo = incomeRepo;
        this.expenseRepo = expenseRepo;
        this.userRepo = userRepo;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<ItemDto> getAllIncomesByUserId() {
        UserEntity userEntity = getUserEntity();
        //Iterable<IncomeEntity> incomeEntities = incomeRepo.findAllByUserDetails(userEntity);
        List<IncomeEntity> incomeEntities = incomeRepo.findAllByUserDetailsOrderByDateDesc(userEntity);
        Type type = new TypeToken<List<ItemDto>>() {
        }.getType();
        return mapper.map(incomeEntities, type);
    }

    @Override
    public List<ItemDto> getAllExpensesByUserId() {
        UserEntity userEntity = getUserEntity();
        //Iterable<IncomeEntity> incomeEntities = incomeRepo.findAllByUserDetails(userEntity);
        List<ExpenseEntity> incomeEntities = expenseRepo.findAllByUserDetailsOrderByDateDesc(userEntity);
        Type type = new TypeToken<List<ItemDto>>() {
        }.getType();
        return mapper.map(incomeEntities, type);
    }

    @Override
    public ItemDto createItem(ItemCategory itemCategory, ItemDto itemDto) {
        itemDto.setItemId(RandomStringUtils.random(10, true, true));
        ItemEntity storedItemEntity;
        if (itemCategory == ItemCategory.INCOME) {
            IncomeEntity incomeEntity = mapper.map(itemDto, IncomeEntity.class);
            incomeEntity.setUserDetails(getUserEntity());
            storedItemEntity = incomeRepo.save(incomeEntity);
        } else {
            ExpenseEntity expenseEntity = mapper.map(itemDto, ExpenseEntity.class);
            expenseEntity.setUserDetails(getUserEntity());
            storedItemEntity = expenseRepo.save(expenseEntity);
        }
        return mapper.map(storedItemEntity, ItemDto.class);
    }

    @Override
    public ItemDto updateItem(ItemCategory itemCategory, ItemDto itemDto) {
        ItemEntity updatedItemEntity;
        // get item by user id -> update item -> save back to database
        if (itemCategory == ItemCategory.INCOME) {
            IncomeEntity incomeEntity = incomeRepo.findByItemId(itemDto.getItemId())
                    .orElseThrow(getInvalidIdException(itemDto.getItemId()));
            updateItem(incomeEntity, itemDto);
            updatedItemEntity = incomeRepo.save(incomeEntity);
        } else {
            ExpenseEntity expenseEntity = expenseRepo.findByItemId(itemDto.getItemId())
                    .orElseThrow(getInvalidIdException(itemDto.getItemId()));
            updateItem(expenseEntity, itemDto);
            updatedItemEntity = expenseRepo.save(expenseEntity);
        }
        // map updated values to dto
        return mapper.map(updatedItemEntity, ItemDto.class);
    }

    @Override
    public void deleteItem(ItemCategory itemCategory, String itemId) {
        // get item by user id -> delete item if found Or else throw exception
        if (itemCategory == ItemCategory.INCOME) {
            IncomeEntity incomeEntity = incomeRepo.findByItemId(itemId)
                    .orElseThrow(getInvalidIdException(itemId));
            incomeRepo.delete(incomeEntity);
        } else {
            ExpenseEntity expenseEntity = expenseRepo.findByItemId(itemId)
                    .orElseThrow(getInvalidIdException(itemId));
            expenseRepo.delete(expenseEntity);
        }
    }

    private void updateItem(ItemEntity storedEntity, ItemDto itemDto) {
        storedEntity.setDescription(itemDto.getDescription());
        storedEntity.setAmount(itemDto.getAmount());
        storedEntity.setDate(itemDto.getDate());
    }

    private UserEntity getUserEntity() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByUserId(userId)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("invalid.userId", "userId=" + userId);
                });
    }

    private Supplier<RuntimeException> getInvalidIdException(String itemId) {
        return () -> {
            String errorMsg = MessageFormat.format("itemId={0} userId={1}", itemId, getUserEntity().getUserId());
            throw new ResourceNotFoundException("invalid.itemId", errorMsg);
        };
    }

}
