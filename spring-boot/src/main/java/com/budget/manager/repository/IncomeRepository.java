package com.budget.manager.repository;

import com.budget.manager.repository.entity.IncomeEntity;
import com.budget.manager.repository.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends CrudRepository<IncomeEntity, Long> {

    List<IncomeEntity> findAllByUserDetailsOrderByDateDesc(UserEntity userEntity);

    Optional<IncomeEntity> findByItemId(String itemId);

}
